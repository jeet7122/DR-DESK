package org.example.hms.dao;

import org.example.hms.models.Appointment;
import org.example.hms.models.Status;
import org.example.hms.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements GenericDAO<Appointment> {

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

    }

    @Override
    public void delete(int id) {

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

            while (rs.next()) {
                Appointment obj = new Appointment();
                obj.setAppointmentId(rs.getInt("appointment_id"));
                obj.setDoctorId(rs.getInt("doctor_id"));
                obj.setPatientId(rs.getInt("patient_id"));
                obj.setAppointmentDate(rs.getObject("appointment_date_and_time", LocalDateTime.class));
                obj.setNotes(rs.getString("notes"));
                obj.setStatus(rs.getObject("status", Status.class));
                appointments.add(obj);
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: "  + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: "  + e.getMessage());
        }
        return appointments;
    }
}
