package org.example.hms.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;

import java.time.LocalDate;

public class DoctorFormView {
    private final VBox root;

    public DoctorFormView() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Add Doctor");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField specialization = new TextField();
        TextField contactNumber = new TextField();
        TextField address = new TextField();
        TextField departmentId = new TextField();

        DatePicker joiningDate = new DatePicker(LocalDate.now());
        CheckBox isAvailable = new CheckBox("Available");

        // Labels and fields
        form.add(new Label("First Name:"), 0, 0);
        form.add(firstName, 1, 0);
        form.add(new Label("Last Name:"), 0, 1);
        form.add(lastName, 1, 1);
        form.add(new Label("Email:"), 0, 2);
        form.add(email, 1, 2);
        form.add(new Label("Specialization:"), 0, 3);
        form.add(specialization, 1, 3);
        form.add(new Label("Contact Number:"), 0, 4);
        form.add(contactNumber, 1, 4);
        form.add(new Label("Address:"), 0, 5);
        form.add(address, 1, 5);
        form.add(new Label("Department ID:"), 0, 6);
        form.add(departmentId, 1, 6);
        form.add(new Label("Joining Date:"), 0, 7);
        form.add(joiningDate, 1, 7);
        form.add(isAvailable, 1, 8);

        Button saveBtn = new Button("Save Doctor");
        saveBtn.setOnAction(e -> {
            try {
                Doctor newDoctor = new Doctor(
                        0,
                        firstName.getText(),
                        lastName.getText(),
                        email.getText(),
                        specialization.getText(),
                        contactNumber.getText(),
                        address.getText(),
                        Integer.parseInt(departmentId.getText()),
                        joiningDate.getValue(),
                        isAvailable.isSelected()
                );
                DoctorDAO dd = new DoctorDAO();
                dd.insert(newDoctor);

                // TODO: replace with DAO insert
                new Alert(Alert.AlertType.INFORMATION,
                        "Doctor saved successfully!").showAndWait();

                firstName.clear();
                lastName.clear();
                email.clear();
                specialization.clear();
                contactNumber.clear();
                address.clear();
                departmentId.clear();
                joiningDate.setValue(LocalDate.now());
                isAvailable.setSelected(false);

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
