package org.example.hms.ui.doctors;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;

import java.time.LocalDate;
import java.util.List;

public class DoctorListView {
    private final BorderPane root;
    private final TableView<Doctor> table;
    private final DoctorDAO doctorDAO;
    private final int rowsPerPage = 10; // Records per page
    private int currentPage = 1;
    private int totalPages = 1;
    private String currentSearchQuery = "";
    private final Label pageInfoLabel = new Label();

    public DoctorListView() {
        root = new BorderPane();
        table = new TableView<>();
        doctorDAO = new DoctorDAO();

        setupColumns();
        HBox searchBox = setupSearch();
        HBox paginationBox = setupPagination();

        root.setTop(searchBox);
        root.setCenter(table);
        root.setBottom(paginationBox);

        loadPage(currentPage, currentSearchQuery);
    }

    // -------------------- TABLE SETUP --------------------
    private void setupColumns() {
        TableColumn<Doctor, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        TableColumn<Doctor, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));

        TableColumn<Doctor, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));

        TableColumn<Doctor, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));

        TableColumn<Doctor, String> specCol = new TableColumn<>("Specialization");
        specCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSpecialization()));

        TableColumn<Doctor, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getContactNumber()));

        TableColumn<Doctor, LocalDate> joinDateCol = new TableColumn<>("Join Date");
        joinDateCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getJoiningDate()));

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol, specCol, contactCol, joinDateCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No doctors found"));
        table.setRowFactory(tv -> {
            TableRow<Doctor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Doctor doctor = row.getItem();
                    Node view = new DoctorCardView(doctor).getView();
                    root.setCenter(view);
                    root.setTop(null);
                }
            });
            return row;
        });
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

    // -------------------- SEARCH --------------------
    public HBox setupSearch() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search by first or last name...");
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

    // -------------------- DATA LOADING --------------------
    private void loadPage(int pageNumber, String searchQuery) {
        List<Doctor> pageData = doctorDAO.getDoctorsPaginated(searchQuery, rowsPerPage, pageNumber);
        int totalRecords = doctorDAO.getTotalCount(searchQuery);
        totalPages = (int) Math.ceil((double) totalRecords / rowsPerPage);

        table.setItems(FXCollections.observableArrayList(pageData));

        if (totalPages == 0) totalPages = 1; // Avoid divide-by-zero
        pageInfoLabel.setText("Page " + currentPage + " of " + totalPages);
    }

    public Parent getView() {
        return root;
    }
}
