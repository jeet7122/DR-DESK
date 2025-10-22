package org.example.hms.ui.patients;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.hms.models.Patient;
import org.example.hms.ui.SceneManager;

public class PatientCardView {
    private final Patient patient;
    private final VBox root;
    private final GridPane grid;
    public PatientCardView(Patient patient) {
        this.patient = patient;
        root = new VBox();
        grid = new GridPane();
        Label patientFirstName = new Label();
        Label patientLastName = new Label();
        Label patientId = new Label();
        Label age = new Label();
        Label patientEmail = new Label();
        Label patientAddress = new Label();
        Label patientRegistrationDate = new Label();
        Label blood_group = new Label();
        Label has_chronic_disease = new Label();

        //FIXED LABELS
        Label patientFirstNameLabel = new Label("First Name");
        Label patientLastNameLabel = new Label("Last Name");
        Label patientIdLabel = new Label("patient ID");
        Label patientBloodGroupLabel = new Label("Blood Group");
        Label patientEmailLabel = new Label("Email");
        Label patientAddressLabel = new Label("Address");
        Label patientRegistration = new Label("Registration Date");
        Label ageLabel = new Label("Age");
        Label has_chronic_disease_label = new Label("HAS_CHRONIC_DISEASE");


        patientIdLabel.getStyleClass().add("labels");
        patientBloodGroupLabel.getStyleClass().add("labels");
        patientEmailLabel.getStyleClass().add("labels");
        patientAddressLabel.getStyleClass().add("labels");
        patientRegistration.getStyleClass().add("labels");
        ageLabel.getStyleClass().add("labels");
        has_chronic_disease_label.getStyleClass().add("labels");
        patientFirstNameLabel.getStyleClass().add("labels");
        patientLastNameLabel.getStyleClass().add("labels");






        has_chronic_disease.setText(patient.isHasChronicDisease() ? "Yes" : "No");
        patientFirstName.setText(patient.getFirstName());
        patientLastName.setText(patient.getLastName());
        age.setText (String.valueOf(patient.getAge()));
        patientEmail.setText(patient.getEmail());
        patientAddress.setText(patient.getAddress());
        blood_group.setText(patient.getBloodGroup());
        patientId.setText(String.valueOf(patient.getId()));
        patientRegistrationDate.setText(String.valueOf(patient.getDateOfRegistration()));

        grid.add(patientIdLabel, 0, 0);
        grid.add(patientId, 1, 0);
        grid.add(patientFirstNameLabel, 0, 1);
        grid.add(patientFirstName, 1, 1);
        grid.add(patientLastNameLabel, 0, 2);
        grid.add(patientLastName, 1 ,2);
        grid.add(patientEmailLabel, 0, 3);
        grid.add(patientEmail, 1, 3);
        grid.add(patientAddressLabel, 0, 4);
        grid.add(patientAddress, 1, 4);
        grid.add(patientBloodGroupLabel, 0, 5);
        grid.add(blood_group, 1, 5);
        grid.add(patientRegistration, 0, 6);
        grid.add(patientRegistrationDate, 1, 6);
        grid.add(has_chronic_disease_label, 0, 7);
        grid.add(has_chronic_disease, 1, 7);
        grid.add(ageLabel, 0, 8);
        grid.add(age, 1, 8);

        grid.getStyleClass().add("card");

        Button backToList = new Button("Back to List");
        backToList.setOnAction(e -> SceneManager.showDashboard());
        root.getChildren().addAll(grid, backToList);


    }
    public Node getView() {
        return root;
    }
}
