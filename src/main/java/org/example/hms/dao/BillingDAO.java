package org.example.hms.dao;

import org.example.hms.models.Billing;
import org.example.hms.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO implements GenericDAO<Billing>{
    @Override
    public void insert(Billing obj) {
        String sql = """
                INSERT INTO billing
                (appointment_id, patient_id, consultation_fee, medicine_fee, service_fee, total_amount, paid)
                VALUES
                (?,?,?,?,?,?,?)
                """;

        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql)
                        )
        {

            statement.setInt(1,obj.getAppointmentId());
            statement.setInt(2,obj.getPatientId());
            statement.setDouble(3,obj.getConsultationFee());
            statement.setDouble(4,obj.getMedicineFee());
            statement.setDouble(5,obj.getServiceFee());
            statement.setDouble(6,obj.getTotalAmount());
            statement.setBoolean(7,obj.isPaid());
            int rows =  statement.executeUpdate();
            if(rows > 0)
            {
                System.out.println("Inserted successfully");
            }
            else
            {
                System.out.println("Insert failed");
            }

        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public void update(Billing obj, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Billing> getAll() {
        List<Billing> billings = new ArrayList<>();
        String sql = """
                SELECT * FROM billing
                ORDER BY bill_id
                """;
        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql);
                        ResultSet resultSet = statement.executeQuery()
                        )
        {
            while (resultSet.next()) {
                Billing billing = new Billing();
                billing.setBillId(resultSet.getInt("bill_id"));
                billing.setAppointmentId(resultSet.getInt("appointment_id"));
                billing.setPatientId(resultSet.getInt("patient_id"));
                billing.setConsultationFee(resultSet.getDouble("consultation_fee"));
                billing.setMedicineFee(resultSet.getDouble("medicine_fee"));
                billing.setServiceFee(resultSet.getDouble("service_fee"));
                billing.setTotalAmount(resultSet.getDouble("total_amount"));
                billing.setPaid(resultSet.getBoolean("paid"));
                billings.add(billing);
            }

        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return billings;
    }
}
