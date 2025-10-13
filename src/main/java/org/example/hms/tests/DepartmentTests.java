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
    public static boolean deleteDepartment(int department_id){
        return DepartmentDAO.deleteDepartment(department_id);
    }
    public static boolean updateDepartment(Department department, int department_id){
        return DepartmentDAO.updateDepartment(department, department_id);
    }
}
