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
import org.example.hms.ui.utils.AbstractListView;

import java.time.LocalDateTime;
import java.util.List;

public class ListPatientsView extends AbstractListView<Patient, PatientDAO> {
    public ListPatientsView() {
        super();
    }

    @Override
    protected PatientDAO createDAO() {
        return new PatientDAO();
    }

    // -------------------- TABLE SETUP --------------------
    @Override
    protected void setupColumns() {
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

    @Override
    protected String getSearchPrompt() {
        return "Search for Patients";
    }

    @Override
    protected List<Patient> getPaginatedData(String query, int limit, int offset) {
        return dao.getPatientsPaginated(query, limit, offset);
    }

    @Override
    protected int getTotalDataCount(String query) {
        return dao.getTotalCount(query);
    }

}
