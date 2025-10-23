package org.example.hms.ui.department;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.hms.dao.DepartmentDAO;
import org.example.hms.models.Department;

import java.util.List;

public class DepartmentListView {
    private final BorderPane root;
    private final TableView<Department> departmentTable;
    private final DepartmentDAO departmentDAO;
    private final int rowsPerPage = 10; // Records per page
    private int currentPage = 1;
    private int totalPages = 1;
    private String currentSearchQuery = "";
    private final Label pageInfoLabel = new Label();

    public  DepartmentListView() {
        root = new BorderPane();
        departmentTable = new TableView<>();
        departmentDAO = new DepartmentDAO();

        setupColumns();

        HBox searchBox = setupSearch();
        root.setTop(searchBox);

        HBox paginationBox = setupPagination();
        root.setCenter(departmentTable);

        root.setBottom(paginationBox);

        loadPage(currentPage, currentSearchQuery);
    }

    // -------------------- SEARCH --------------------
    public HBox setupSearch() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Department name...");
        Button searchButton = new Button("Search");

        // --- Dynamic live search ---
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            currentSearchQuery = newText.trim();
            currentPage = 1; // Reset page when new search
            loadPage(currentPage, currentSearchQuery);
        });

        // --- Optional button (for explicit search) ---
        searchButton.setOnAction(e -> {
            currentSearchQuery = searchField.getText().trim();
            currentPage = 1;
            loadPage(currentPage, currentSearchQuery);
        });

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        return searchBox;
    }



    private void setupColumns() {
        TableColumn<Department, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(depart -> new SimpleIntegerProperty(depart.getValue().getDepartmentId()).asObject());

        TableColumn<Department, String> nameCol = new TableColumn<>("Department Name");
        nameCol.setCellValueFactory(depart -> new SimpleStringProperty(depart.getValue().getDepartmentName()));

        TableColumn<Department, String> descriptionCol = new TableColumn<>("Department Description");
        descriptionCol.setCellValueFactory(depart -> new SimpleStringProperty(depart.getValue().getDepartmentDescription()));

        TableColumn<Department, String> location = new TableColumn<>("Department Location");
        location.setCellValueFactory(depart -> new SimpleStringProperty(depart.getValue().getLocation()));

        departmentTable.getColumns().addAll(idCol, nameCol, descriptionCol, location);
        departmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        departmentTable.setPlaceholder(new Label("No Departments found"));

    }

    // -------------------- PAGINATION --------------------
    public HBox setupPagination() {
        Button prevButton = new Button("Previous");
        Button nextButton = new Button("Next");

        prevButton.setOnAction(e -> {
            if (currentPage > 1) {
                currentPage--;
                loadPage(currentPage, currentSearchQuery);
            }
        });

        nextButton.setOnAction(e -> {
            if (currentPage < totalPages) {
                currentPage++;
                loadPage(currentPage, currentSearchQuery);
            }
        });

        HBox paginationBox = new HBox(10, prevButton, pageInfoLabel, nextButton);
        paginationBox.setPadding(new Insets(10));
        paginationBox.setStyle("-fx-alignment: center;");
        return paginationBox;
    }


    /**
     *
     * @param pageNumber
     * @param searchQuery
     *
     * LOAD_PAGE()
     */
    private void loadPage(int pageNumber, String searchQuery) {
        List<Department> pageData = departmentDAO.getDepartmentsPaginated(searchQuery, rowsPerPage, pageNumber);
        int totalRecords = departmentDAO.getTotalCount(searchQuery);
        System.out.println("Page " + pageNumber + " loaded " + pageData.size() + " records.");
        totalPages = (int) Math.ceil((double) totalRecords / rowsPerPage);
        departmentTable.setItems(FXCollections.observableArrayList(pageData));
        if (totalPages == 0) totalPages = 1; // Avoid divide-by-zero
        pageInfoLabel.setText("Page " + currentPage + " of " + totalPages);
    }


    public Node getView() {
        return root;
    }
}
