package org.example.bankmanagementsystemjavasim.controllers;

import DB.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerLoginController {


    @FXML
    private TextField accountIdField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Label errorLabel;

    public void handleLogin(ActionEvent event)


    {
        System.out.println("Welcome to CustomerLoginController");


        String accountId = accountIdField.getText();
        String pin = pinField.getText();

        if(accountId.isEmpty() || pin.isEmpty()){
            errorLabel.setText("Please enter account ID and PIN");
            return;
        }
        try(Connection conn = DatabaseConnector.connect()){
            String sql = """
                SELECT c.customer_id, c.full_name, a.account_id, a.balance 
                FROM customers c 
                JOIN accounts a ON c.customer_id = a.customer_id
                WHERE a.account_id = ? AND c.pin = ?
                """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(accountId));
            stmt.setString(2, pinField.getText());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int customerId = rs.getInt("customer_id");
                int accId = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                String name = rs.getString("full_name");

               try{
                   FXMLLoader loader = new FXMLLoader(getClass().getResource(
                           "/org/example/bankmanagementsystemjavasim/views/loginScreens/customer/CustomerDashboard.fxml"));
                   Stage stage = (Stage) accountIdField.getScene().getWindow();
                   stage.setScene(new Scene(loader.load()));

                   // Send data to dashboard
                   CustomerDashboardController controller = loader.getController();
                   controller.initializeCustomer(customerId, accId, name, balance);


               } catch (Exception e) {
                   e.printStackTrace();
               }
            }


         else {
        errorLabel.setText("Invalid account ID or PIN");
    }

        } catch (SQLException e) {
            e.printStackTrace();
            errorLabel.setText("Something went wrong.");
        }

    }

    public void handleBack(ActionEvent event)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/bankmanagementsystemjavasim/views/loginScreens/welcome_view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch(Exception e)
        {
            System.err.println("Error loading Welcome View FXML:");
            e.printStackTrace();
        }
    }
}
