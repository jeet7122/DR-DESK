package org.example.hms.ui.appointment;

import javafx.scene.Parent;
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

public class AddAppointmentView {
    private final AppointmentDAO dao;
    private ComboBox<Integer> minutes;
    private ComboBox<Integer> hours;

    private final VBox root;
    public AddAppointmentView(){
        dao = new AppointmentDAO();
        root = new VBox(10);
        Label title = new Label("Add Appointment");
        title.getStyleClass().add("form-title");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.getStyleClass().add("gp");

        TextField doc_id = new TextField();
        TextField patient_id = new TextField();
        TextField status = new TextField();
        DatePicker appointment_date = new DatePicker();
        HBox timePicker = getTimePicker();
        TextArea notes = new TextArea();
        gridPane.add(new Label("Doctor Id:"), 0, 0);
        gridPane.add(doc_id, 1, 0);
        gridPane.add(new Label("Patient Id:"), 3,0);
        gridPane.add(patient_id, 4, 0);
        gridPane.add(new Label("Status:"), 0,2);
        gridPane.add(status, 1, 2);
        gridPane.add(new  Label("Appointment Date:"), 0,3);
        gridPane.add(appointment_date, 1, 3);
        gridPane.add(timePicker, 2, 3);
        gridPane.add(new Label("Notes :"), 0, 4);
        gridPane.add(notes, 1, 4);
        Button add = new Button("Add");
        add.setOnAction(e -> {
            try{
                Appointment appointment = new Appointment();
                appointment.setDoctorId(Integer.parseInt(doc_id.getText()));
                appointment.setPatientId(Integer.parseInt(patient_id.getText()));
                appointment.setStatus(Status.SCHEDULED);
                //Date and time conversion Logic
                String minAndHrs = getSelectedTime();
                LocalDate date = appointment_date.getValue();
                LocalDateTime appointment_date_and_time = LocalDateTime.of(date, LocalTime.parse(minAndHrs));
                appointment.setAppointmentDate(appointment_date_and_time);
                appointment.setNotes(notes.getText());
                dao.insert(appointment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Appointment Added Successfully");
                alert.showAndWait();
            }
            catch (NumberFormatException ex){
                System.out.println("Error parsing data" + ex.getMessage());
            }
            catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
            doc_id.clear();
            patient_id.clear();
            status.clear();
            notes.clear();
        });

        root.getChildren().addAll(title, gridPane, add);

    }
    public HBox getTimePicker(){
        HBox hBox = new HBox(10);
        hours = new ComboBox<>();
        minutes = new ComboBox<>();

        //Adding hours
        for(int i=0;i<24;i++){
            hours.getItems().add(i);
        }

        //Adding minutes
        for(int i=0;i<60;i++){
            minutes.getItems().add(i);
        }

        hBox.getChildren().addAll(minutes,hours);
        return hBox;
    }
    public String getSelectedTime(){
        Integer hour = hours.getValue();
        Integer minute = minutes.getValue();
        return(hour != null && minute != null) ? String.format("%02d:%02d", hour, minute) : null;
    }

    public Parent getView(){
        return root;
    }
}
