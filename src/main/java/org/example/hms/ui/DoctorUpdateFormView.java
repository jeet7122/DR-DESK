package org.example.hms.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;

public class DoctorUpdateFormView {

    private final VBox root;
    public DoctorUpdateFormView() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        Label title = new Label("Update Doctor");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        TextField doc_id = new TextField();
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        TextField address = new TextField();
        TextField specialization = new TextField();
        TextField department_id = new TextField();
        CheckBox isAvailable = new CheckBox("Available");

        form.add(new Label("Doctor ID:"), 0, 0);
        form.add(doc_id, 1, 0);
        form.add(new Label("First Name:"), 0, 1);
        form.add(firstName, 1, 1);
        form.add(new Label("Last Name:"), 0, 2);
        form.add(lastName, 1, 2);
        form.add(new Label("Email:"), 0, 3);
        form.add(email, 1, 3);
        form.add(new Label("Phone:"), 0, 4);
        form.add(phone, 1, 4);
        form.add(new Label("Address:"), 0, 5);
        form.add(address, 1, 5);
        form.add(new Label("Specialization:"), 0, 6);
        form.add(specialization, 1, 6);
        form.add(new Label("Department:"), 0, 7);
        form.add(department_id, 1, 7);
        form.add(new Label("Available:"), 0, 8);
        form.add(isAvailable, 1, 8);

        Button update  = new Button("Update");
        update.setOnAction(e -> {
            try {
                Doctor docToUpdate = new Doctor();
                docToUpdate.setDepartmentId(Integer.parseInt(department_id.getText()));
                docToUpdate.setFirstName(firstName.getText());
                docToUpdate.setLastName(lastName.getText());
                docToUpdate.setEmail(email.getText());
                docToUpdate.setAddress(address.getText());
                docToUpdate.setSpecialization(specialization.getText());
                docToUpdate.setContactNumber(phone.getText());
                docToUpdate.setAvailable(isAvailable.isSelected());

                DoctorDAO dao = new DoctorDAO();
                dao.update(docToUpdate, Integer.parseInt(doc_id.getText()));
                new Alert(Alert.AlertType.INFORMATION,
                        "Doctor detail's updated successfully!").showAndWait();



                doc_id.clear();
                firstName.clear();
                lastName.clear();
                email.clear();
                phone.clear();
                address.clear();
                specialization.clear();
                department_id.clear();
                isAvailable.setSelected(false);

            }
            catch (Exception ex) {
                System.out.println("Parsing Exception" + ex.getMessage());
            }
        });
        title.getStyleClass().add("form-title");
        form.getStyleClass().add("gp");
        root.getChildren().addAll(title, form, update);



    }
    public Parent getView(){
        return root;
    }
}
