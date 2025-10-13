package org.example.hms.dao;

import org.example.hms.models.MedicalRecord;
import org.example.hms.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

    
}
