package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // Database connection
    private static final String URL= "jdbc:postgresql://localhost:5432/banksystem";
    private static final String USERNAME= "postgres";
    private static final String PASSWORD= "ser123";
    // Database connect methode
    public static Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully");


        }catch (SQLException e){
            System.err.println("Connection failed");
            System.err.println(e.getMessage());
            e.printStackTrace();

        }
        return conn;
    }

    public static void disconnect(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
