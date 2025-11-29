package org.example.bankmanagementsystemjavasim;

import DB.DatabaseConnector;
import javafx.application.Application;

import java.sql.Connection;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
        // Database connection test
        Connection conn = DatabaseConnector.connect();
        if(conn!=null){
            DatabaseConnector.disconnect(conn);

        } else {
            System.out.println("application not lunched due database connection failure");
        }

    }
}
