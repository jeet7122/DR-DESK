package org.example.hms.tests;

import org.example.hms.dao.DoctorDAO;
import org.example.hms.models.Doctor;

import java.util.List;

public class DoctorsTest {
    DoctorDAO dao = new DoctorDAO();
    public void TestInsertDoctor(Doctor doctor) {
        dao.insert(doctor);
    }
    public void TestUpdateDoctor(Doctor doctor, int id) {
        dao.update(doctor, id);
    }
    public void TestDeleteDoctor(int id) {
        dao.delete(id);
    }
    public List<Doctor> TestGetAllDoctor() {
        return dao.getAll();
    }
}
