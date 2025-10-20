package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dto.AppointmentDTO;
import org.example.hms.models.Appointment;
import org.example.hms.models.Billing;
import org.example.hms.models.Status;
import org.example.hms.tests.AppointmentTest;
import org.example.hms.tests.BillingTest;
import org.example.hms.ui.SceneManager;


import java.io.IOException;
import java.time.LocalDateTime;


public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.init(stage);
        stage.setTitle("DR-DESK - Hospital Management System");
        SceneManager.showDashboard();
        stage.show();
    }
}
