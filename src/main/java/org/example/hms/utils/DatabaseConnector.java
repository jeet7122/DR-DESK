package org.example.hms.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private DatabaseConnector() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        if (connection == null) {
            try(InputStream inputStream = DatabaseConnector.class.getResourceAsStream("/config.properties")) {
                Properties prop = new Properties();
                prop.load(inputStream);

                String url = prop.getProperty("db.url");
                String username = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection established");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
