package org.example.hms.dao;

import org.example.hms.models.Department;
import org.example.hms.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements GenericDAO<Department> {

    public List<Department> getAll()
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

            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }

        return departments;
    }


    public void insert(Department department)
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


    public void update(Department department, int id)
    {

        String query = """
                UPDATE department
                SET department_name = ?, description = ?, location = ?
                WHERE department_id = ?
        """;

        try(
                Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
                )
        {
            statement.setString(1, department.getDepartmentName());
            statement.setString(2, department.getDepartmentDescription());
            statement.setString(3, department.getLocation());
            statement.setInt(4, id);
            int result = statement.executeUpdate();
            if(result > 0)
            {
                System.out.println("Department has been updated");
            }
            else
            {
                System.out.println("Department has NOT been updated");
            }
        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }
    }

    public void delete(int id)
    {
        String query = """
                DELETE FROM department
                WHERE department_id = ?
        """;
        try(Connection connection = DatabaseConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)
        )
        {
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if(result > 0)
            {
                System.out.println("Department has been deleted");
            }
            else
            {
                System.out.println("Department has NOT been deleted");
            }

        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }
    }


    public List<Department> getDepartmentsPaginated(String searchQuery, int rowsPerPage, int pageNumber) {
        List<Department> departments = new ArrayList<>();
        int offset = (pageNumber - 1) * rowsPerPage;
        String query = """
        SELECT * FROM department
        WHERE lower(department_name) LIKE ?
        ORDER BY department_id
        LIMIT ?
        OFFSET ?
        """;
        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(query)
                        )
        {
            query = "%" + searchQuery + "%";
            statement.setString(1, query);
            statement.setInt(2, rowsPerPage);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
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
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }

        return departments;
    }

    public int getTotalCount(String searchQuery) {
        String query = "SELECT COUNT(*) FROM department WHERE LOWER(department_name) LIKE ?";
        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(query)
                        )
        {
            query = "%" + searchQuery.toLowerCase() + "%";
            statement.setString(1, query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
        catch (Exception e){
            System.out.println("Not a supported operation" + e.getMessage());
        }

        return 0;
    }
}
