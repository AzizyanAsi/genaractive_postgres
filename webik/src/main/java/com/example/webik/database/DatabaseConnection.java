package com.example.webik.database;

import com.example.webik.util.DatabaseConfigurationUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection initializeConnection() throws SQLException {

        Properties props = DatabaseConfigurationUtil.getConnectionProperties();

        String dbDriverClass = props.getProperty("db.driver.class");
        String dbConnUrl = props.getProperty("db.url");
        String dbUsername = props.getProperty("db.username");
        String dbPassword = props.getProperty("db.password");

        try {
            Class.forName(dbDriverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return DriverManager.getConnection(dbConnUrl, dbUsername, dbPassword);
    }
}
