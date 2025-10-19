package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.models.Appointment;
import org.example.hms.models.Status;
import org.example.hms.tests.AppointmentTest;

import java.io.IOException;
import java.time.LocalDateTime;


public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Appointment  appointment = new Appointment();
        AppointmentTest test = new AppointmentTest();


        appointment.setAppointmentDate(LocalDateTime.parse("2025-10-12T23:54:48.001911"));
        appointment.setStatus(Status.COMPLETED);
        appointment.setPatientId(8);
        appointment.setDoctorId(1);
        appointment.setNotes("This is a test");

        test.addAppointment(appointment);
        test.getAllAppointment();


    }
}
