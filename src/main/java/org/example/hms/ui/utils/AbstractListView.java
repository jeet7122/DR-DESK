package org.example.hms.ui.utils;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.hms.dao.GenericDAO;

import java.util.List;

public abstract class AbstractListView<T, D extends GenericDAO<T>>{
    protected final BorderPane root;
    protected final TableView<T> table;
    protected final D dao;
    protected int currentPage = 1;
    protected int totalPages = 1;
    protected final int rowsPerPage = 10;
    protected String currentSearchQuery = "";
    protected final Label pageInfo = new Label();

    protected abstract D createDAO();
    protected abstract void setupColumns();
    protected abstract String getSearchPrompt();
    protected abstract List<T> getPaginatedData(String query, int limit, int offset);
    protected abstract int getTotalDataCount(String query);

    public AbstractListView(){
        this.dao = createDAO();
        this.table = new TableView<>();
        this.root = new BorderPane();

        //Implement this method in sub-class according to your need
        setupColumns();

        //Common UI features
        HBox searchBox = setupSearch();
        HBox pagination = setupPagination();

        root.setTop(searchBox);
        root.setCenter(table);
        root.setBottom(pagination);

        loadPage(currentPage, currentSearchQuery);
    }

    //Common Search Box for all pages, can be over-ridden by child classes
    private HBox setupSearch() {
        TextField searchField = new TextField();
        searchField.setPromptText(getSearchPrompt());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            currentSearchQuery = newValue.toLowerCase().trim();
            currentPage = 1;
            loadPage(currentPage, currentSearchQuery);
        });
        Button searchButton = new Button("Search");
        searchButton.setOnAction((event) -> {
            currentSearchQuery = searchField.getText().toLowerCase().trim();
            currentPage = 1;
            loadPage(currentPage, currentSearchQuery);
        });
        HBox searchBox = new HBox(10);
        searchBox.setPadding(new Insets(10));
        searchBox.getChildren().addAll(searchField, searchButton);
        return searchBox;
    }

    //Common Pagination setup
    private HBox setupPagination() {
        Button prevButton = new Button("Previous");
        Button nexButton = new Button("Next");
        HBox pagination = new HBox(10);
        pagination.getChildren().addAll(prevButton, nexButton);
        pagination.setPadding(new Insets(10));
        pagination.setStyle("-fx-alignment: center;");

        prevButton.setOnAction(e -> {
            if(currentPage > 1){
                currentPage--;
                loadPage(currentPage, currentSearchQuery);
            }
        });

        nexButton.setOnAction(e -> {
            if(currentPage < totalPages){
                currentPage++;
                loadPage(currentPage, currentSearchQuery);
            }
        });

        return pagination;

    }

    //Common page loading setup
    protected  void loadPage(int pageNumber, String currentSearchQuery){

        List<T> pageData = getPaginatedData(currentSearchQuery, rowsPerPage, pageNumber);
        int totalRecords = getTotalDataCount(currentSearchQuery);

        System.out.println("Total Records: " + totalRecords);

        totalPages = (int) Math.ceil((double)(totalRecords/rowsPerPage));
        totalPages = Math.max(1, totalPages);
        if (totalPages == 0) totalPages = 1;
        table.setItems(FXCollections.observableList(pageData));
        pageInfo.setText("Page " + currentPage + " of " + totalPages);
    }
    public Parent getView(){
        return root;
    }
}
