package org.example.bankmanagementsystemjavasim.DAO;




import DB.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

    // CREATE TABLE
    public void createTable() {
        String sql = """
CREATE TABLE IF NOT EXISTS accounts (
    account_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    balance NUMERIC(12,2) DEFAULT 0,
    type VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
""";


        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("accounts table ensured to exist");

        } catch (SQLException e) {
            System.err.println("Error creating accounts table: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // CREATE DEFAULT ACCOUNT

    public boolean createDefaultAccount(int customerId) {
        String sql = "INSERT INTO accounts (customer_id, balance, type) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            pstmt.setBigDecimal(2, BigDecimal.ZERO);
            pstmt.setString(3, "CHECKING");

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Default CHECKING account created for customer ID: " + customerId);
                return true;
            }

            return false;

        } catch (SQLException e) {
            System.err.println("Error creating default account for customer " + customerId + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
