package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hms.dao.DepartmentDAO;
import org.example.hms.models.Department;
import org.example.hms.tests.DepartmentTests;

import java.io.IOException;
import java.util.List;

public class MainScreenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Department department = new Department();
        department.setDepartmentName("Cardiology");
        department.setDepartmentDescription("Handles diagnosis and treatment of heart-related conditions");
        department.setLocation("Floor 2");
        DepartmentTests.AddDepartmentTest(department);
        List<Department> departments = DepartmentDAO.getAllDepartments();
        departments.forEach(System.out::println);
    }
}
