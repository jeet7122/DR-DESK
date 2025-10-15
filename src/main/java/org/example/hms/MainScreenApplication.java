package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.models.Doctor;
import org.example.hms.tests.DoctorsTest;


import java.io.IOException;
import java.util.List;

import static org.example.hms.tests.DoctorsTest.TestGetAllDoctor;

public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        Doctor doctor = new Doctor();
//        doctor.setFirstName("Peter");
//        doctor.setLastName("Parker");
//        doctor.setEmail("peterparker@hmsdr.com");
//        doctor.setAvailable(true);
//        doctor.setAddress("2100 Baby Street");
//        doctor.setContactNumber("519-459-4000");
//        doctor.setSpecialization("Child-Care Specialization");
//        doctor.setDepartmentId(7);
//        DoctorsTest.TestInsertDoctor(doctor);
//        System.out.println("TEST 1 PASSED!! Doctor has been inserted");


//        Doctor docToUpdate = new Doctor();
//        docToUpdate.setFirstName("John");
//        docToUpdate.setLastName("Doe");
//        docToUpdate.setEmail("johndoe@hmsdr.com");
//        docToUpdate.setAvailable(true);
//        docToUpdate.setAddress("3575 Sandwich Street");
//        docToUpdate.setContactNumber("519-459-0000");
//        docToUpdate.setSpecialization("Cardiac Surgeon");
//        docToUpdate.setDepartmentId(4);
//        DoctorsTest.TestUpdateDoctor(docToUpdate, 1);
//        System.out.println("TEST 2 PASSED!! Doctor has been updated");

//
//


        DoctorsTest.TestDeleteDoctor(3);
        List<Doctor> doctors = DoctorsTest.TestGetAllDoctor();
        doctors.forEach(System.out::println);


    }
}
