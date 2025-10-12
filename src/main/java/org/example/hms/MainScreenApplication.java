package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.utils.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;

public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Connection c = DatabaseConnector.getConnection();
            if(c != null){
                System.out.println("Connected to database.");
            }
            else{
                System.out.println("Database not connected.");
            }
            DatabaseConnector.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
