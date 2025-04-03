package com.medicalapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSchemaConnection {
    public static void main(String[] args) {
        String mysqlURL = "mysql://avnadmin:password@mysql-1184aba2-onlyibrahim13-ff30.i.aivencloud.com:12768/defaultdb?ssl-mode=REQUIRED";
        String username = "avnadmin";
        String password = "password"; // Change the password

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = connection.createStatement();

            System.out.println("Database schema connection established.");

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
