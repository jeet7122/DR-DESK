package org.example.hms.ui.patients;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import org.example.hms.dao.PatientDAO;

import org.example.hms.models.Patient;

import org.example.hms.ui.doctors.DoctorListView;

import java.time.LocalDateTime;
import java.util.List;

public class ListPatientsView {
    private final BorderPane root;
    private final TableView<Patient> table;
    private final PatientDAO patientDAO;
    private final int rowsPerPage = 10; // Records per page
    private int currentPage = 1;
    private int totalPages = 1;
    private String currentSearchQuery = "";
    private final Label pageInfoLabel = new Label();
    private final DoctorListView  doctorListView;

    public ListPatientsView() {
        root = new BorderPane();
        table = new TableView<>();
        patientDAO = new PatientDAO();
        doctorListView = new DoctorListView();

        setupColumns();
        HBox searchBox = doctorListView.setupSearch();
        HBox paginationBox = doctorListView.setupPagination();

        root.setTop(searchBox);
        root.setCenter(table);
        root.setBottom(paginationBox);

        loadPage(currentPage, currentSearchQuery);
    }

    // -------------------- TABLE SETUP --------------------
    private void setupColumns() {
        TableColumn<Patient, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        TableColumn<Patient, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));

        TableColumn<Patient, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));

        TableColumn<Patient, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));

        TableColumn<Patient, String> bloodGroup = new TableColumn<>("Blood Group");
        bloodGroup.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBloodGroup()));

        TableColumn<Patient, String> gender = new TableColumn<>("Gender");
        gender.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getGender()));

        TableColumn<Patient, LocalDateTime> registration_date = new TableColumn<>("Registration Date");
        registration_date.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getDateOfRegistration()));

        TableColumn<Patient, Boolean> has_chronic_disease = new TableColumn<>("Has_Chronic_Disease");
        has_chronic_disease.setCellValueFactory(d -> new SimpleBooleanProperty(d.getValue().isHasChronicDisease()));

        TableColumn<Patient, Integer> age = new TableColumn<>("Age");
        age.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAge()).asObject());

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol,  bloodGroup, gender, registration_date, has_chronic_disease, age);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No patients found"));
        table.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Patient patient = row.getItem();
                    Node view = new PatientCardView(patient).getView();
                    root.setCenter(view);
                    root.setTop(null);
                }
            });
            return row;
        });
    }


    // -------------------- SEARCH --------------------

    // -------------------- DATA LOADING --------------------
    private void loadPage(int pageNumber, String searchQuery) {
        List<Patient> pageData = patientDAO.getPatientsPaginated(searchQuery, rowsPerPage, pageNumber);
        int totalRecords = patientDAO.getTotalCount(searchQuery);
        totalPages = (int) Math.ceil((double) totalRecords / rowsPerPage);

        table.setItems(FXCollections.observableArrayList(pageData));

        if (totalPages == 0) totalPages = 1; // Avoid divide-by-zero
        pageInfoLabel.setText("Page " + currentPage + " of " + totalPages);
    }

    public Parent getView() {
        return root;
    }
}
