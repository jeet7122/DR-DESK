package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dto.AppointmentDTO;

import org.example.hms.models.Status;
import org.example.hms.tests.AppointmentTest;

import java.io.IOException;
import java.time.LocalDateTime;


public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppointmentTest test = new AppointmentTest();
        test.getAllAppointment();


    }
}
