package org.example.hms.dao;

import org.example.hms.models.Department;
import org.example.hms.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public static List<Department> getAllDepartments()
    {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM Department";
        try(
                Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
                )
        {

            while(resultSet.next())
            {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setDepartmentDescription(resultSet.getString("description"));
                department.setLocation(resultSet.getString("location"));
                departments.add(department);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }

        return departments;
    }


    public static void addDepartment(Department department)
    {
        String sql = """
                INSERT INTO department
                (department_name, description, location)
                VALUES (?, ?, ?)
        """;
        try(
                Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
                )
        {
            statement.setString(1, department.getDepartmentName());
            statement.setString(2, department.getDepartmentDescription());
            statement.setString(3, department.getLocation());
            statement.execute();
            System.out.println("New Department has been added");
        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }
    }

    
}
