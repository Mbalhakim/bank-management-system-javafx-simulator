package org.example.bankmanagementsystemjavasim.controllers;


import DB.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CustomerDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label balanceLabel;

    private int customerId;
    private int accountId;

    public void initializeCustomer(int customerId, int accountId, String name, double balance) {
        this.customerId = customerId;
        this.accountId = accountId;

        welcomeLabel.setText("Welcome, " + name);
        balanceLabel.setText("Balance: €" + balance);
    }
}
