package org.example.hms.ui.patients;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.hms.dao.PatientDAO;
import org.example.hms.models.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddPatientView {
    private VBox root;
    public AddPatientView(){
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Add Patient");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField blood_group = new TextField();
        TextField address = new TextField();
        TextField age = new TextField();
        DatePicker registration_date = new DatePicker(LocalDate.now());
        CheckBox has_chronic_disease = new CheckBox("Chronic Disease");
        TextField gender = new TextField();

        // Labels and fields
        form.add(new Label("First Name:"), 0, 0);
        form.add(firstName, 1, 0);
        form.add(new Label("Last Name:"), 0, 1);
        form.add(lastName, 1, 1);
        form.add(new Label("Email:"), 0, 2);
        form.add(email, 1, 2);
        form.add(new Label("Blood Group:"), 0, 3);
        form.add(blood_group, 1, 3);
        form.add(new Label("Age:"), 0, 4);
        form.add(age, 1, 4);
        form.add(new Label("Address:"), 0, 5);
        form.add(address, 1, 5);
        form.add(new Label("Date of Registration:"), 0, 6);
        form.add(registration_date, 1, 6);
        form.add(new Label("Gender:"), 0, 7);
        form.add(gender, 1, 7);
        form.add(has_chronic_disease, 1, 8);

        Button saveBtn = new Button("Save Patient");
        saveBtn.setOnAction(e -> {
            try {
                Patient patient = new Patient();
                patient.setFirstName(firstName.getText());
                patient.setLastName(lastName.getText());
                patient.setEmail(email.getText());
                patient.setAge(Integer.parseInt(age.getText()));
                patient.setAddress(address.getText());
                patient.setBloodGroup(blood_group.getText());
                patient.setHasChronicDisease(has_chronic_disease.isSelected());
                patient.setGender(gender.getText());
                PatientDAO patientDAO =  new PatientDAO();
                patientDAO.insert(patient);
                // TODO: replace with DAO insert
                new Alert(Alert.AlertType.INFORMATION,
                        "Patient saved successfully!").showAndWait();

                firstName.clear();
                lastName.clear();
                email.clear();
                blood_group.clear();
                age.clear();
                address.clear();
                registration_date.setValue(LocalDate.now());
                has_chronic_disease.setSelected(false);

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR,
                        "Invalid Department ID! Please enter a number.").showAndWait();
            }
        });

        title.getStyleClass().add("form-title");
        form.getStyleClass().add("gp");

        root.getChildren().addAll(title, form, saveBtn);
    }

    public Node getView() {
        return root;
    }
    }

