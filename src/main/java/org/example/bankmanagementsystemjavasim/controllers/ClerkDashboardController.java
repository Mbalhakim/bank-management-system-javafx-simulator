package org.example.bankmanagementsystemjavasim.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Handles interactions for the clerk's main dashboard.
public class ClerkDashboardController {

    // Action to load the Customer Registration form
    @FXML
    private void handleRegisterCustomer(ActionEvent event) {
        System.out.println("Loading Customer Registration Form...");
        try {
            // Path to the registration form FXML
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/bankmanagementsystemjavasim/views/loginScreens/clerk/customer_registration.fxml")));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Customer Registration");
            stage.setScene(new Scene(root, 700, 500));

        } catch (IOException e) {
            System.err.println("Error loading registration view:");
            e.printStackTrace();
        }
    }

    // Action to log out (for completeness)
    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Clerk logging out...");

    }
}