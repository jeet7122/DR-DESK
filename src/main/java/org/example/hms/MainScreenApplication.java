package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dao.PatientDAO;
import org.example.hms.models.Patient;
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
            Patient p  = new Patient();
            p.setFirstName("Jeet");
            p.setLastName("Thakkar");
            p.setAge(21);
            p.setGender("Male");
            p.setAddress("1630 College Avenue");
            p.setBloodGroup("B+");
            p.setHasChronicDisease(true);
            p.setEmail("jeetthakkar2612@gmail.com");

            boolean inserted = PatientDAO.insertPatient(p);
            if(inserted){
                System.out.println("Patient inserted successfully.");
            }
            else{
                System.out.println("Patient insert failed.");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
