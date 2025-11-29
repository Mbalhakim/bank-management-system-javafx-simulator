package org.example.bankmanagementsystemjavasim.DAO;

import DB.DatabaseConnector;
import org.example.bankmanagementsystemjavasim.models.Customer;

import java.sql.*;

public class CustomerDAO {
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS customers (\n" +
                "    customer_id SERIAL PRIMARY KEY,\n" +
                "    full_name VARCHAR(100) NOT NULL,\n" +
                "    email VARCHAR(100) UNIQUE NOT NULL,\n" +
                "    pin VARCHAR(10) NOT NULL,\n" +
                "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                ");";
        try {
            Connection conn = DatabaseConnector.connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println(" customers table exist.");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            System.out.println("error creating customers table");

        }

    }

    // --- Registration Logic ---
    public boolean registerCustomer (Customer customer){
        // PreparedStatement for secure insertion
        String sql = "INSERT INTO customers (full_name, email, pin) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnector.connect();
            //Statement.RETURN_GENERATED_KEYS to get the database-generated 'id' back
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))  {
            pstmt.setString(1, customer.getFullName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPin());
            int affectedRows = pstmt.executeUpdate();
            if(affectedRows > 0) {
                // Retrieve the auto-generated ID and set it back on the Customer object
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()){
                        customer.setCustomerId(rs.getInt(1));
                        System.out.println("Customer registered with ID: " + customer.getCustomerId());
                        return true;
                    }
                }
            }


        } catch (SQLException e){
            System.err.println("Error registering customer: " + e.getMessage());
            e.printStackTrace();
            return false;

        }
        return false;
    }

}
