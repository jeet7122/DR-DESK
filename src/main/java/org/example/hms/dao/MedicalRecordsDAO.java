package org.example.hms.dao;

import org.example.hms.models.MedicalRecord;
import org.example.hms.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsDAO {
    public static void AddMedicalRecord(MedicalRecord medicalRecord){
        String query = """
                INSERT INTO medical_records
                (patient_id, condition, description, current_status)
                VALUES (?, ?, ?, ?)
                """;
        try(
                Connection con = DatabaseConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
                )
        {
            ps.setInt(1, medicalRecord.getPatientId());
            ps.setString(2, medicalRecord.getCondition());
            ps.setString(3, medicalRecord.getDescription());
            ps.setString(4, medicalRecord.getCurrentStatus());
            int updated = ps.executeUpdate();
            if(updated > 0)
            {
                System.out.println("Record added successfully");
            }
            else
            {
                System.out.println("Failed to add medical record");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<MedicalRecord> getMedicalRecords(){
        String query = """
                SELECT * FROM medical_records
        """;
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
        )
        {
            while (resultSet.next()){
                MedicalRecord newMedicalRecord = new MedicalRecord();
                newMedicalRecord.setId(resultSet.getInt("id"));
                newMedicalRecord.setPatientId(resultSet.getInt("patient_id"));
                newMedicalRecord.setCondition(resultSet.getString("condition"));
                newMedicalRecord.setDescription(resultSet.getString("description"));
                newMedicalRecord.setCurrentStatus(resultSet.getString("current_status"));
                newMedicalRecord.setDateDiagnosed(resultSet.getObject("date_diagnosed", LocalDate.class));
                medicalRecords.add(newMedicalRecord);
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return medicalRecords;
    }

    public static void updateMedicalRecord(MedicalRecord medicalRecord, int id){

        String query = """
                UPDATE medical_records
                SET patient_id = ?, condition = ?, description = ?, current_status = ?
                WHERE id = ?
        """;

        try(
                Connection connection = DatabaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
                )
        {
            statement.setInt(1, medicalRecord.getPatientId());
            statement.setString(2, medicalRecord.getCondition());
            statement.setString(3, medicalRecord.getDescription());
            statement.setString(4, medicalRecord.getCurrentStatus());
            statement.setInt(5, id);
            int updated = statement.executeUpdate();
            if(updated > 0)
            {
                System.out.println("Record updated successfully");
            }
            else
            {
                System.out.println("Failed to update medical record");
            }
        }
        catch (Exception e) {
            System.out.println("SQLException: " +  e.getMessage());
        }
    }
}
