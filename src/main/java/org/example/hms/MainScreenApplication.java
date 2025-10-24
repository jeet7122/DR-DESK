package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.tests.AuthTest;
import org.example.hms.ui.SceneManager;
import org.example.hms.utils.DatabaseConnector;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.init(stage);
        stage.setTitle("DR-DESK - Hospital Management System");
        SceneManager.showLogin();
        stage.show();

//        try(Connection connection = DatabaseConnector.getConnection())
//        {
//
//            if (connection == null) {
//                System.out.println("Null Connection");
//            }
//            else {
//                System.out.println("Connection Successful");
//            }
//        }
//        catch (SQLException e){
//            System.out.println("SQLException: " + e.getMessage());
//        }
//        catch (ClassNotFoundException e) {
//            System.out.println("ClassNotFoundException: " + e.getMessage());
//        }

    }
}
