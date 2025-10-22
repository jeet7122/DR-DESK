package org.example.hms.ui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.hms.models.Doctor;


public class DoctorCardView {
    private final VBox root;
    private final Doctor doctor;
    private final GridPane grid;

    public DoctorCardView(Doctor doctor) {
        this.doctor = doctor;
        root = new VBox();
        grid = new GridPane();
        root.setSpacing(10);

        //DYNAMIC/CHANGING LABELS
        Label doctorFirstName = new Label();
        Label doctorLastName = new Label();
        Label doctorId = new Label();
        Label doctorPhone = new Label();
        Label doctorEmail = new Label();
        Label doctorAddress = new Label();
        Label doctorJoiningDate = new Label();
        Label docDepartment = new Label();
        Label isAvailable = new Label();

        //FIXED LABELS
        Label doctorFirstNameLabel = new Label("First Name");
        Label doctorLastNameLabel = new Label("Last Name");
        Label doctorIdLabel = new Label("Doctor ID");
        Label doctorPhoneLabel = new Label("Phone Number");
        Label doctorEmailLabel = new Label("Email");
        Label doctorAddressLabel = new Label("Address");
        Label doctorJoiningDateLabel = new Label("Joining Date");
        Label docDepartmentLabel = new Label("Department");
        Label isAvailableLabel = new Label("Available");


        doctorIdLabel.getStyleClass().add("labels");
        doctorPhoneLabel.getStyleClass().add("labels");
        doctorEmailLabel.getStyleClass().add("labels");
        doctorAddressLabel.getStyleClass().add("labels");
        doctorJoiningDateLabel.getStyleClass().add("labels");
        docDepartmentLabel.getStyleClass().add("labels");
        isAvailableLabel.getStyleClass().add("labels");
        doctorFirstNameLabel.getStyleClass().add("labels");
        doctorLastNameLabel.getStyleClass().add("labels");






        isAvailable.setText(doctor.isAvailable() ? "Yes" : "No");
        doctorFirstName.setText(doctor.getFirstName());
        doctorLastName.setText(doctor.getLastName());
        docDepartment.setText (String.valueOf(doctor.getDepartmentId()));
        doctorEmail.setText(doctor.getEmail());
        doctorAddress.setText(doctor.getAddress());
        doctorPhone.setText(doctor.getContactNumber());
        doctorId.setText(String.valueOf(doctor.getId()));
        doctorJoiningDate.setText(String.valueOf(doctor.getJoiningDate()));

        grid.add(doctorIdLabel, 0, 0);
        grid.add(doctorId, 1, 0);
        grid.add(doctorFirstNameLabel, 0, 1);
        grid.add(doctorFirstName, 1, 1);
        grid.add(doctorLastNameLabel, 0, 2);
        grid.add(doctorLastName, 1 ,2);
        grid.add(doctorEmailLabel, 0, 3);
        grid.add(doctorEmail, 1, 3);
        grid.add(doctorAddressLabel, 0, 4);
        grid.add(doctorAddress, 1, 4);
        grid.add(doctorPhoneLabel, 0, 5);
        grid.add(doctorPhone, 1, 5);
        grid.add(doctorJoiningDateLabel, 0, 6);
        grid.add(doctorJoiningDate, 1, 6);
        grid.add(docDepartmentLabel, 0, 7);
        grid.add(docDepartment, 1, 7);
        grid.add(isAvailableLabel, 0, 8);
        grid.add(isAvailable, 1, 8);

        grid.getStyleClass().add("card");

        Button backToList = new Button("Back to List");
        backToList.setOnAction(e -> SceneManager.showDashboard());
        root.getChildren().addAll(grid, backToList);


    }

    public Node getView() {
        return root;
    }
}
