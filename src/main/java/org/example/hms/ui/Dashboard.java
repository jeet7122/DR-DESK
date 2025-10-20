package org.example.hms.ui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Dashboard {
    private final BorderPane root;
    public Dashboard() {
        root = new BorderPane();
        VBox sidebar = new VBox(10);
        sidebar.setStyle("-fx-background-color: #f4f6f8; -fx-padding: 15;");
        Button docs = new Button("Doctors");
        Button appointments = new Button("Appointments");
        Button patients = new Button("Patients");
        Button departments = new Button("Departments");
        Button bills = new Button("Billing");


        docs.setOnAction(e -> root.setCenter(new DoctorListView().getView()));

        sidebar.getChildren().addAll(docs, appointments, patients, departments, bills);
        root.setLeft(sidebar);
        root.setCenter(new DoctorListView().getView());
    }
    public Parent getView(){
        return root;
    }

}
