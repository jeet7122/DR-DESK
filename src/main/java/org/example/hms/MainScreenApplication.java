package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dao.PatientDAO;
import org.example.hms.models.Patient;
import org.example.hms.utils.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
            p.setFirstName("Khushi");
            p.setLastName("Parikh");
            p.setAge(20);
            p.setGender("Female");
            p.setAddress("1630 College Avenue");
            p.setBloodGroup("B+");
            p.setHasChronicDisease(true);
            p.setEmail("khushiparikh@gmail.com");

            boolean inserted = PatientDAO.insertPatient(p);
            if(inserted){
                System.out.println("Patient inserted successfully.");
            }
            else{
                System.out.println("Patient insert failed.");
            }
            List<Patient> patients = PatientDAO.getAllPatients();
            patients.forEach(System.out::println);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
