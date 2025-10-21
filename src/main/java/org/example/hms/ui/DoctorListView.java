package org.example.hms.ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;

import java.time.LocalDate;

public class DoctorListView {
    private final BorderPane root;
    private final ObservableList<Doctor> doctors;
    private final TableView<Doctor> table;
    private final HBox hbox;
    public DoctorListView() {
        root = new BorderPane();
        table = new TableView<>();
        doctors = FXCollections.observableArrayList();
        hbox = new HBox(10);


        TableColumn<Doctor, Integer>  idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(
                data ->
                        new SimpleIntegerProperty(data.getValue().getId()).asObject()
        );

        TableColumn<Doctor, String>  firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(data.getValue().getFirstName())
        );
        TableColumn<Doctor, String>  lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data ->
                    new SimpleStringProperty(data.getValue().getLastName()));
        TableColumn<Doctor, String>  emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Doctor, String> specCol = new TableColumn<>("Specialization");
        specCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getSpecialization()));

        TableColumn<Doctor, String> contactCol = new TableColumn<>("Contact Number");
        contactCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getContactNumber()));

        TableColumn<Doctor, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getAddress()));

        TableColumn<Doctor, Integer> deptCol = new TableColumn<>("Department ID");
        deptCol.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getDepartmentId()).asObject());

        TableColumn<Doctor, LocalDate> joinDateCol = new TableColumn<>("Joining Date");
        joinDateCol.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getJoiningDate()));

        TableColumn<Doctor, Boolean> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(data ->
                new SimpleBooleanProperty(data.getValue().isAvailable()));

        // Add columns to table
        table.getColumns().addAll(
                idCol, firstNameCol, lastNameCol, emailCol,
                specCol, contactCol, addressCol, deptCol,
                joinDateCol, availableCol
        );

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        root.setCenter(table);



        // Placeholder text
        table.setPlaceholder(new Label("No doctors found"));

        // TODO: Replace this with DAO call later:
        DoctorDAO dd =  new DoctorDAO();
        doctors.setAll(dd.getAll());

        FilteredList<Doctor> filteredData = new FilteredList<>(doctors, p -> true);
        table.setItems(filteredData);

        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        Button searchButton = new Button("Search");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String lcf = newValue.toLowerCase().trim();
            filteredData.setPredicate(doctor -> {
                if(lcf.isEmpty()) {
                    return true;
                }
                return doctor.getFirstName().toLowerCase().contains(lcf) || doctor.getLastName().toLowerCase().contains(lcf);
            });
        });
        searchButton.setOnAction(event -> {
            String lcf = searchField.getText().toLowerCase().trim();
            filteredData.setPredicate(doctor -> {
                if(lcf.isEmpty()) {
                    return true;
                }
                return doctor.getFirstName().toLowerCase().contains(lcf) || doctor.getLastName().toLowerCase().contains(lcf);
            });

        });
        hbox.getChildren().addAll(searchField, searchButton);
        searchField.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(hbox);



    }

    public Parent getView() {
        return root;
    }
}
