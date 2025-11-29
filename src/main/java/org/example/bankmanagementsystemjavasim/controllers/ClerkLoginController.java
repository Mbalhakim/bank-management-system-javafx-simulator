package org.example.bankmanagementsystemjavasim.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClerkLoginController {
    public void handleLogin(ActionEvent event){
        System.out.println("Welcome to CustomerLoginController");
    }
    public void handleBack(ActionEvent event)
    {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/bankmanagementsystemjavasim/views/loginScreens/welcome_view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }
}
