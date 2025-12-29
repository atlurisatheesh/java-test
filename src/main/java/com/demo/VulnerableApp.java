package com.example.vulnerable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableApp {

    // Hardcoded credentials (Security issue)
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "password123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // SQL Injection vulnerability
        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        String query = "SELECT * FROM users WHERE id = '" + userId + "'";

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    DB_USER,
                    DB_PASS
            );

            Statement stmt = conn.createStatement();
            stmt.execute(query); // SQL Injection

        } catch (Exception e) {
            // Empty catch block (Code smell)
        }

        // Resource leak (scanner not closed)
    }
}
