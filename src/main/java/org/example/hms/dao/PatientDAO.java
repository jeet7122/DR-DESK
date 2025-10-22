package org.example.hms.dao;

import org.example.hms.models.Doctor;
import org.example.hms.models.Patient;
import org.example.hms.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements GenericDAO<Patient> {
    public void insert(Patient p){
        String sql = "INSERT INTO patients(first_name, last_name, age, gender, address, blood_group, has_chronic_disease, email) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setInt(3, p.getAge());
            statement.setString(4, p.getGender());
            statement.setString(5, p.getAddress());
            statement.setString(6, p.getBloodGroup());
            statement.setBoolean(7, p.isHasChronicDisease());
            statement.setString(8,p.getEmail());
            statement.execute();
        }
        catch (Exception e) {
            System.out.println("Error in inserting patient into table: " + e.getMessage());
        }
    }

    @Override
    public void update(Patient obj, int id) {
        String sql = """
                UPDATE patients
                SET first_name = ?, last_name = ?, age = ? , gender = ?, blood_group = ?, has_chronic_disease = ?
                WHERE id = ?
                """;

        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)
                        )
        {
            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setInt(3, obj.getAge());
            statement.setString(4, obj.getGender());
            statement.setString(5, obj.getBloodGroup());
            statement.setBoolean(6, obj.isHasChronicDisease());
            statement.setInt(7, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Patient updated successfully!");
            }
            else {
                System.out.println("Patient update failed!");
            }

        }
        catch (Exception e) {
            System.out.println("Error in updating a patient into table: " + e.getMessage());
        }
    }

    public List<Patient> getAll(){
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY id";

        try(Connection connection = DatabaseConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            mapFromDB(patients, rs);
        }
        catch (Exception e) {
            System.out.println("Error fetching patient from table: " + e.getMessage());
        }
        return patients;
    }

    public static boolean updatePatient(String firstName, String lastName, String email ,int id){

        String sql = """
                UPDATE patients
                SET first_name = ?, last_name = ?, email = ?
                WHERE id = ?""";
        try(Connection connection = DatabaseConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setInt(4, id);

            int rows = statement.executeUpdate();

            if(rows > 0){
                System.out.println("Successfully updated patient with id " + id);
                return true;
            }
            else {
                System.out.println("Failed to update patient with id " + id);
            }
        }
        catch (Exception e) {
            System.out.println("Error:  " + e.getMessage());
        }

        return false;
    }

    public void delete(int id){
        String sql = """
                DELETE FROM patients
                WHERE id = ?
                """;

        try(
                Connection conn = DatabaseConnector.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)
                )
        {
            statement.setInt(1, id);
            int executed = statement.executeUpdate();
            if (executed > 0){
                System.out.println("Successfully deleted patient with id " + id);
            }
            else {
                System.out.println("Failed to delete patient with id " + id);
            }
        }

        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<Patient> getPatientsPaginated(String searchQuery, int rowsPerPage, int pageNumber) {
        List<Patient> patients = new ArrayList<>();
        int offset = (pageNumber - 1) * rowsPerPage;
        String sql = """
            SELECT * FROM patients
            WHERE LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?
            ORDER BY id
            LIMIT ? OFFSET ?
        """;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String query = "%" + searchQuery.toLowerCase() + "%";
            stmt.setString(1, query);
            stmt.setString(2, query);
            stmt.setInt(3, rowsPerPage);
            stmt.setInt(4, offset);

            ResultSet rs = stmt.executeQuery();
            mapFromDB(patients, rs);

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }

        return patients;

    }

    private void mapFromDB(List<Patient> patients, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Patient p = new Patient();
            p.setId(rs.getInt("id"));
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setAge(rs.getInt("age"));
            p.setGender(rs.getString("gender"));
            p.setAddress(rs.getString("address"));
            p.setBloodGroup(rs.getString("blood_group"));
            p.setHasChronicDisease(rs.getBoolean("has_chronic_disease"));
            p.setDateOfRegistration(rs.getObject("registration_date", LocalDateTime.class));
            p.setEmail(rs.getString("email"));
            patients.add(p);
        }
    }

    public int getTotalCount(String searchQuery) {
        String sql = "SELECT COUNT(*) FROM doctors WHERE LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String query = "%" + searchQuery.toLowerCase() + "%";
            stmt.setString(1, query);
            stmt.setString(2, query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }
        return 0;
    }
}
