package org.example.hms.dao;

import org.example.hms.models.Doctor;
import org.example.hms.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements GenericDAO<Doctor> {

    @Override
    public void insert(Doctor doctor) {
        String sql = """
                INSERT INTO doctors
                (first_name, last_name, email, specialization, contact_number, address, department_id, is_available)
                VALUES
                (?,?,?,?,?,?,?,?)
        """;
        try (
                Connection conn = DatabaseConnector.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)
        )
        {

            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, doctor.getSpecialization());
            statement.setString(5, doctor.getContactNumber());
            statement.setString(6, doctor.getAddress());
            statement.setInt(7, doctor.getDepartmentId());
            statement.setBoolean(8, doctor.isAvailable());
            statement.execute();
            System.out.println("Doctor Inserted Successfully!");
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }
    }

    @Override
    public void update(Doctor doctor, int doc_id) {
        String sql = """
                UPDATE doctors
                SET first_name = ?, last_name = ?, email = ?, contact_number = ?, specialization = ?, address = ?, department_id = ? ,is_available = ?
                WHERE doctor_id = ?;
        """;

        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)
                )
        {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, doctor.getContactNumber());
            statement.setString(5, doctor.getSpecialization());
            statement.setString(6, doctor.getAddress());
            statement.setInt(7, doctor.getDepartmentId());
            statement.setBoolean(8, doctor.isAvailable());
            statement.setInt(9, doc_id);
            int updated = statement.executeUpdate();
            if (updated > 0) {
                System.out.println("Doctor Updated Successfully!");
            }
            else {
                System.out.println("Doctor Updated Failed!");
            }
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = """
                DELETE FROM doctors
                WHERE doctor_id = ?;
        """;
        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)
                )
        {
            statement.setInt(1, id);
            int deleted = statement.executeUpdate();
            if (deleted > 0) {
                System.out.println("Doctor Deleted Successfully!");
            }
            else {
                System.out.println("Doctor Deleted Failed!");
            }
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }

    }

    @Override
    public List<Doctor> getAll() {
        String sql = """
                SELECT * FROM doctors
        """;
        List<Doctor> doctors = new ArrayList<>();
        try(
                Connection conn = DatabaseConnector.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        )
        {
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doctor_id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setEmail(rs.getString("email"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setContactNumber(rs.getString("contact_number"));
                doctor.setAddress(rs.getString("address"));
                doctor.setDepartmentId(rs.getInt("department_id"));
                doctor.setAvailable(rs.getBoolean("is_available"));
                doctor.setJoiningDate(rs.getObject("joining_date", LocalDate.class));
                doctors.add(doctor);
            }
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }
        return doctors;
    }

    public List<Doctor> getDoctorsPaginated(String searchQuery, int rowsPerPage, int pageNumber) {
        List<Doctor> doctors = new ArrayList<>();
        int offset = (pageNumber - 1) * rowsPerPage;

        String sql = """
            SELECT * FROM doctors
            WHERE LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?
            ORDER BY doctor_id
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
            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("specialization"),
                        rs.getString("contact_number"),
                        rs.getString("address"),
                        rs.getInt("department_id"),
                        rs.getDate("joining_date").toLocalDate(),
                        rs.getBoolean("is_available")
                );
                doctors.add(d);
            }

        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generic Exception: " + e.getMessage());
        }

        return doctors;
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
