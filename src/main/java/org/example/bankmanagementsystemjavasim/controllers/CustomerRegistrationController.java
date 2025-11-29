package org.example.bankmanagementsystemjavasim.controllers;


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

import org.example.bankmanagementsystemjavasim.DAO.AccountDAO;
import org.example.bankmanagementsystemjavasim.DAO.CustomerDAO;
import org.example.bankmanagementsystemjavasim.models.Customer;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Handles the logic for the customer registration form.
public class CustomerRegistrationController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField pinField;
    @FXML private Label messageLabel;

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final AccountDAO accountDAO = new AccountDAO();

    // Simple mock hash function (DO NOT USE IN PRODUCTION)
    private String hashPin(String pin) {
        return "HASH_" + pin + "_END";
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        messageLabel.setText(""); // Clear previous messages
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String pin = pinField.getText().trim();

        // 1. Input Validation
        if (fullName.isEmpty() || email.isEmpty() || pin.isEmpty()) {
            messageLabel.setText("Error: All fields are required.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!pin.matches("\\d{4}")) {
            messageLabel.setText("Error: PIN must be exactly 4 digits.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Simple email regex for client-side check
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        if (!emailPattern.matcher(email).matches()) {
            messageLabel.setText("Error: Invalid email format.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // 2. Data Persistence (using DAOs)
        String pinHash = hashPin(pin);
        Customer newCustomer = new Customer(fullName, email, pinHash);

        if (customerDAO.registerCustomer(newCustomer)) {
            // 3. Create Default Account
            if (accountDAO.createDefaultAccount(newCustomer.getCustomerId())) {
                messageLabel.setText("Success! Customer registered and checking account created.");
                messageLabel.setStyle("-fx-text-fill: green;");

                // Clear fields on success
                fullNameField.clear();
                emailField.clear();
                pinField.clear();
            } else {
                messageLabel.setText("Warning: Customer registered, but failed to create account.");
                messageLabel.setStyle("-fx-text-fill: orange;");
                // In a real app, you would implement transaction rollback here!
            }
        } else {
            messageLabel.setText("Error: Failed to register customer (Email may already exist).");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        System.out.println("Returning to Clerk Dashboard...");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("org/example/bankmanagementsystemjavasim/views/loginScreens/clerk/clerk_dashboard.fxml")));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Clerk Dashboard");
            stage.setScene(new Scene(root, 800, 600));

        } catch (IOException e) {
            System.err.println("Error loading dashboard view:");
            e.printStackTrace();
        }
    }
}