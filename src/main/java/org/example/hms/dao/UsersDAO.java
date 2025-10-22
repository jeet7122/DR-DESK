package org.example.hms.dao;

import org.example.hms.models.User;
import org.example.hms.models.UserRoles;
import org.example.hms.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDAO implements GenericDAO<User>{
    @Override
    public void insert(User obj) {
        String sql = "INSERT INTO users(first_name, last_name, email, password, roles) VALUES (?,?,?,?,?)";
        try(
                Connection conn = DatabaseConnector.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)
                )
        {
            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getPassword());
            statement.setString(5, String.valueOf(obj.getRole()));
            int rows =  statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Successfully Inserted");
            }
            else {
                System.out.println("Failed Inserted");
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
    public void update(User obj, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY user_id";
        try
                (
                        Connection connection = DatabaseConnector.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql);
                        ResultSet rs = statement.executeQuery()
                        )
        {
            User user = new User();
            while (rs.next()) {
                MapFromDB(rs, user);
                users.add(user);
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        return users;
    }

    private void MapFromDB(ResultSet rs, User user) throws SQLException {

        OffsetDateTime updated = rs.getObject("updated_at", OffsetDateTime.class);
        OffsetDateTime created = rs.getObject("created_at", OffsetDateTime.class);
        if (updated != null && created != null) {
            user.setLastModifiedDate(updated.toLocalDateTime());
            user.setCreatedDate(created.toLocalDateTime());
        }

        user.setId(rs.getString("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        String roleStr =  rs.getString("roles");
        user.setRole(UserRoles.valueOf(roleStr));

    }

    public void login(String email, String password) {

    }
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try
                (
                        Connection conn = DatabaseConnector.getConnection();
                        PreparedStatement statement = conn.prepareStatement(sql)
                        )
        {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                MapFromDB(rs, user);
                return user;
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
}
