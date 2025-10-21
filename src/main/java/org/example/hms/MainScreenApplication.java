package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;

import org.example.hms.ui.SceneManager;


import java.io.IOException;



public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.init(stage);
        stage.setTitle("DR-DESK - Hospital Management System");
        SceneManager.showDashboard();
        stage.show();
    }
}
