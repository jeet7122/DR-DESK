package org.example.hms.dao;

import org.example.hms.models.Patient;
import org.example.hms.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public static boolean insertPatient(Patient p){
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
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY id";

        try(Connection connection = DatabaseConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            while(rs.next()){
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
        catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return false;
    }
}
