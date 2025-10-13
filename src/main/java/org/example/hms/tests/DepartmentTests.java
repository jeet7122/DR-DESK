package org.example.hms.tests;

import org.example.hms.dao.DepartmentDAO;
import org.example.hms.models.Department;

import java.util.List;

public class DepartmentTests {

    public static void AddDepartmentTest(Department department){
        DepartmentDAO.addDepartment(department);
    }
    public static List<Department> getAllDepartments(){
        return DepartmentDAO.getAllDepartments();
    }
}
