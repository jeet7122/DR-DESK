package org.example.hms.ui.doctors;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.example.hms.dao.DoctorDAO;

public class DeleteDoctorFormView {
    private final HBox root;
    public DeleteDoctorFormView() {
        root = new HBox();
        root.setSpacing(10);

        TextField doctor_id =  new TextField();
        doctor_id.setPromptText("Doctor ID");
        Button delete = new Button("Delete");
        root.getChildren().addAll(doctor_id, delete);
        delete.setOnAction(e -> {
            try {
                String id = doctor_id.getText().trim();
                if (id.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Field Empty");
                }
                int did = Integer.parseInt(id);
                deleteDoctor(did);
                doctor_id.clear();

            }

            catch (NumberFormatException ex) {
                System.out.println("Invalid Doctor ID: " + ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Doctor ID");
                alert.show();
            }
            catch (Exception ex) {
                System.out.println("Doctor Id not valid" +  ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Doctor ID");
                alert.show();
            }
        });
    }
    private void deleteDoctor(int id) {
        DoctorDAO doctorDAO = new DoctorDAO();
        doctorDAO.delete(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Doctor deleted successfully");
        alert.show();

    }

    public Node getView() {
        return root;
    }
}
