package org.example.hms.dao;

import org.example.hms.models.Appointment;
import org.example.hms.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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
        return List.of();
    }
}
