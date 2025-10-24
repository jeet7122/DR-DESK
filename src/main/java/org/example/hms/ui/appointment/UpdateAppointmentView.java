package org.example.hms.ui.appointment;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.hms.dao.AppointmentDAO;
import org.example.hms.models.Appointment;
import org.example.hms.models.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UpdateAppointmentView {
    private final AddAppointmentView view;
    private final VBox root;
    private final AppointmentDAO dao;
    private final GridPane gp;
    public UpdateAppointmentView(){
        view = new AddAppointmentView();
        root = new VBox();
        dao = new AppointmentDAO();
        gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.getStyleClass().add("gp");

        Label title = new Label("Update Appointments");
        title.getStyleClass().add("form-title");

        TextField app_id = new TextField();
        TextField doc_id = new TextField();
        TextField patient_id = new TextField();
        TextField status = new TextField();
        TextArea notes = new TextArea();
        DatePicker date = new DatePicker();
        HBox timePicker = view.getTimePicker();

        gp.add(new Label("Appointment ID:"), 0, 0);
        gp.add(app_id, 1, 0);
        gp.add(new Label("Document ID:"), 0, 1);
        gp.add(doc_id, 1, 1);
        gp.add(new Label("Patient ID:"), 3, 1);
        gp.add(patient_id, 4, 1);
        gp.add(new Label("Status:"), 0, 2);
        gp.add(status, 1, 2);
        gp.add(new Label("Notes:"), 3, 2);
        gp.add(notes, 4, 2);
        gp.add(new Label("Date:"), 0, 3);
        gp.add(date, 1, 3);
        gp.add(new Label("Time: MIN | HR"), 2, 3);
        gp.add(timePicker, 3, 3);

        Button save = new Button("Update");

        save.setOnAction(e ->{
            try
            {
                Appointment appToUpdate = new Appointment();
                appToUpdate.setDoctorId(Integer.parseInt(doc_id.getText()));
                appToUpdate.setPatientId(Integer.parseInt(patient_id.getText()));
                appToUpdate.setNotes(notes.getText());
                appToUpdate.setStatus(Status.valueOf(status.getText()));
                int id = Integer.parseInt(app_id.getText());

                String timeVal = view.getSelectedTime();
                LocalDate dateValue = date.getValue();
                LocalDateTime localDateTime = LocalDateTime.of(dateValue, LocalTime.parse(timeVal));
                appToUpdate.setAppointmentDate(localDateTime);
                dao.update(appToUpdate, id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Updated");
                alert.setHeaderText("Appointment Updated");
                alert.showAndWait();
            }
            catch (NumberFormatException ex){
                System.out.println("Error parsing data : " + ex.getMessage());
            }
            catch (Exception ex){
                System.out.println("Error : " + ex.getMessage());
            }

            app_id.clear();
            doc_id.clear();
            patient_id.clear();
            status.clear();
            notes.clear();
            date.setValue(null);
        });

        root.getChildren().addAll(title, gp, save);

    }

    public Node getView() {
        return root;
    }
}
