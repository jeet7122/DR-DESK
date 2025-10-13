package org.example.hms;

import javafx.application.Application;
import javafx.stage.Stage;
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

        Department departmentToUpdate = new Department();
        departmentToUpdate.setDepartmentName("Neurology");
        departmentToUpdate.setDepartmentDescription("Specializes in disorders of the nervous system");
        departmentToUpdate.setLocation("Floor 3");
        boolean updated = DepartmentTests.updateDepartment(departmentToUpdate, 2);
        if (updated){
            System.out.println("Department update Test Passed");
        }
        else {
            System.out.println("Department update Test Failed");
        }

        boolean deleted = DepartmentTests.deleteDepartment(3);
        if (deleted){
            System.out.println("Department delete Test Passed");
        }
        else {
            System.out.println("Department delete Test Failed");
        }

        List<Department> departments = DepartmentTests.getAllDepartments();
        departments.forEach(System.out::println);

    }
}
