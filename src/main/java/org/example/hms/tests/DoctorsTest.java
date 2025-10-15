package org.example.hms.tests;

import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;

import java.util.List;

public class DoctorsTest {
    public static void TestInsertDoctor(Doctor doctor) {
        DoctorDAO.InsertDoctor(doctor);
    }
    public static void TestUpdateDoctor(Doctor doctor, int id) {
        DoctorDAO.UpdateDoctor(doctor, id);
    }
    public static void TestDeleteDoctor(int id) {
        DoctorDAO.DeleteDoctor(id);
    }
    public static List<Doctor> TestGetAllDoctor() {
        return DoctorDAO.GetAllDoctors();
    }
}
