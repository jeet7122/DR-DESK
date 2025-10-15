package org.example.hms.tests;

import org.example.hms.dao.DepartmentDAO;
import org.example.hms.models.Department;

import java.util.List;

public class DepartmentTests {
    DepartmentDAO dao = new DepartmentDAO();

    public void AddDepartmentTest(Department department){

    }
    public List<Department> getAllDepartments(){
        return dao.getAll();
    }
    public void deleteDepartment(int department_id){
         dao.delete(department_id);
    }
    public void updateDepartment(Department department, int department_id){
        dao.update(department, department_id);
    }
}
