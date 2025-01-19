package com.tahrioussama.employeerecordsmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class EmployeeRecordsManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeRecordsManagementSystemApplication.class, args);
        System.out.println("Employee Records Management System is running...");
        String url = "jdbc:oracle:thin:@localhost:1521/employee_db"; // Update with your DB details
        String username = "system";
        String password = "oracle"; // Ensure this is correct

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to Oracle DB successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
