package org.example.hms.dao;

import org.example.hms.models.Patient;
import org.example.hms.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatientDAO {
    public static boolean insertPatient(Patient p){
        String sql = "INSERT INTO patients(first_name, last_name, age, gender, address, blood_group, has_chronic_disease) VALUES (?,?,?,?,?,?,?)";

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
            statement.execute();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
