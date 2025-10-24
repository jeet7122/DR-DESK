package org.example.hms.dao;

import javafx.collections.FXCollections;
import org.example.hms.Mappers;
import org.example.hms.dto.AppointmentDTO;
import org.example.hms.models.Appointment;
import org.example.hms.utils.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements GenericDAO<Appointment> {
    private final Mappers mappers =  new Mappers();

    public AppointmentDAO() {
    }

    public void insertWithDTO(AppointmentDTO obj) {
        String query = """
                INSERT INTO appointment
                (doctor_id, patient_id, status, notes, appointment_date_and_time)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try
                (
                        Connection con = DatabaseConnector.getConnection();
                        PreparedStatement ps = con.prepareStatement(query)
                        )
        {
            ps.setInt(1, obj.getDoctorId());
            ps.setInt(2, obj.getPatientId());
            ps.setString(3, obj.getStatus());
            ps.setString(4, obj.getNotes());
            ps.setObject(5, obj.getAppointmentDate(), Types.TIMESTAMP);
            ps.execute();
        }
        catch (SQLException e){
            System.out.println("SQLException: "  + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: "  + e.getMessage());
        }

    }
    @Override
    public void insert(Appointment obj) {
        String query = """
                INSERT INTO appointment
                (doctor_id, patient_id, status, notes, appointment_date_and_time)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try
                (
                        Connection con = DatabaseConnector.getConnection();
                        PreparedStatement ps = con.prepareStatement(query)
                )
        {
            ps.setInt(1, obj.getDoctorId());
            ps.setInt(2, obj.getPatientId());
            ps.setString(3, obj.getStatus().toString());
            ps.setString(4, obj.getNotes());
            ps.setObject(5, obj.getAppointmentDate(), Types.TIMESTAMP);
            ps.execute();
        }
        catch (SQLException e){
            System.out.println("SQLException: "  + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: "  + e.getMessage());
        }

    }



    @Override
    public void update(Appointment obj, int id) {

        String query = """
                UPDATE appointment
                SET doctor_id = ?, patient_id = ?, appointment_date_and_time = ?, status = ?, notes = ?
                WHERE appointment_id = ?
                """;
        try(
                Connection con = DatabaseConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
                )
        {
            ps.setInt(1, obj.getDoctorId());
            ps.setInt(2, obj.getPatientId());
            ps.setObject(3, obj.getAppointmentDate(), Types.TIMESTAMP);
            ps.setString(4, obj.getStatus().toString());
            ps.setString(5, obj.getNotes());
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Appointment updated successfully!");
            }
            else {
                System.out.println("Appointment update failed!");
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: "  + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: "  + e.getMessage());
        }


    }

    @Override
    public void delete(int id) {
        String query = """
                DELETE FROM appointment WHERE appointment_id = ?
        """;
        try(
                Connection con = DatabaseConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
                )
        {
            ps.setInt(1, id);
            int rows =  ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Appointment with id: " + id + " has been deleted");
            }
            else  {
                System.out.println("Deletion failed");
            }

        }
        catch (SQLException e){
            System.out.println("SQLException: "  + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: "  + e.getMessage());
        }

    }

    @Override
    public List<Appointment> getAll() {
        List<Appointment> appointments = new ArrayList<>();
        String query = """
                SELECT * FROM appointment
        """;

        try
                (Connection con = DatabaseConnector.getConnection();
                 PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery())
        {

            resultSetToModel(appointments, rs);
        }
        catch (SQLException e){
            System.out.println("SQLException: "  + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: "  + e.getMessage());
        }
        return appointments;
    }

    private void resultSetToModel(List<Appointment> appointments, ResultSet rs) throws SQLException {
        while (rs.next()) {
            AppointmentDTO obj = new AppointmentDTO();
            obj.setAppointmentId(rs.getInt("appointment_id"));
            obj.setDoctorId(rs.getInt("doctor_id"));
            obj.setPatientId(rs.getInt("patient_id"));
            Timestamp timestamp = rs.getTimestamp("appointment_date_and_time");
            if (timestamp != null) {
                obj.setAppointmentDate(timestamp.toLocalDateTime());
                System.out.println(obj.getAppointmentDate());
            }
            else {
                obj.setAppointmentDate(null);
                System.out.println("Appointment date is null");
            }

            obj.setNotes(rs.getString("notes"));
            obj.setStatus(rs.getString("status"));
            Appointment app = mappers.mapFromDTO(obj);

            System.out.println("Appointment model" + app.getAppointmentDate());
            appointments.add(app);
        }
    }

    public List<Appointment> getAppointmentsPaginated(String searchQuery, int rowsPerPage, int pageNumber) {
        List<Appointment> appointments = new ArrayList<>();
        int offset = (pageNumber - 1) * rowsPerPage;
        String baseQuery;
        boolean isSearching = searchQuery != null && !searchQuery.trim().isEmpty();

        if (isSearching) {
            baseQuery = """
            SELECT * FROM appointment
            WHERE doctor_id = ? OR patient_id = ?
            ORDER BY appointment_date_and_time DESC LIMIT ? OFFSET ?;
        """;
        } else {
            baseQuery = """
            SELECT * FROM appointment
            ORDER BY appointment_date_and_time DESC LIMIT ? OFFSET ?;
        """;
        }

        try (
                Connection con = DatabaseConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(baseQuery)
        ) {
            int paramIndex = 1;

            if (isSearching) {
                int searchValue = 0;
                try {
                    searchValue = Integer.parseInt(searchQuery.trim());
                } catch (NumberFormatException e) {
                    // Handle non-numeric search query (e.g., show no results or log error)
                    System.out.println("Invalid search query format. Must be an integer ID.");
                    return FXCollections.observableArrayList();
                }
                // Set the two WHERE clause parameters
                ps.setInt(paramIndex++, searchValue);
                ps.setInt(paramIndex++, searchValue);
            }

            // Set the LIMIT and OFFSET parameters (always required)
            ps.setInt(paramIndex++, rowsPerPage);
            ps.setInt(paramIndex, offset);

            ResultSet rs = ps.executeQuery();
            resultSetToModel(appointments, rs);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return FXCollections.observableArrayList(appointments);
    }

    public int getTotalCount(String searchQuery) {
        String query;
        boolean isSearching = searchQuery != null && !searchQuery.trim().isEmpty();

        if (isSearching) {
            query = """
            SELECT COUNT(*) FROM appointment WHERE doctor_id = ? OR patient_id = ?;
        """;
        } else {
            query = """
            SELECT COUNT(*) FROM appointment;
        """;
        }

        try (
                Connection con = DatabaseConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            if (isSearching) {
                int searchValue = 0;
                try {
                    searchValue = Integer.parseInt(searchQuery.trim());
                } catch (NumberFormatException e) {
                    // If search is numeric but the input is bad, assume 0 for count
                    System.out.println("Invalid search query format. Must be an integer ID.");
                    return 0;
                }
                ps.setInt(1, searchValue);
                ps.setInt(2, searchValue);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return 0;
    }
}
