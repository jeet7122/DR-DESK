package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dao.PatientDAO;
import org.example.hms.models.Patient;
import org.example.hms.tests.Patients;
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
            p.setFirstName("Dhrish");
            p.setLastName("Thakkar");
            p.setAge(4);
            p.setGender("Male");
            p.setAddress("1630 College Avenue");
            p.setBloodGroup("O+");
            p.setHasChronicDisease(false);
            p.setEmail("dh@123@x-mail.com");

            boolean inserted = PatientDAO.insertPatient(p);
            if(inserted){
                System.out.println("Patient inserted successfully.");
            }
            else{
                System.out.println("Patient insert failed.");
            }

            Patients.updatePatient("Jeet", "Thakkar", "j@123@x-mail.com", 6);

            PatientDAO.deletePatient(9);
            List<Patient> patients = PatientDAO.getAllPatients();
            patients.forEach(System.out::println);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
