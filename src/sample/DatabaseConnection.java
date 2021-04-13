package sample;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseConnection;

    public Connection getConnection() {
        String host = "localhost";
        String database = "app_fx_db";
        String database_username = "TechGuy";
        String database_password = "dev@techguy";
        String url = "jdbc:mysql://" + host + ":3306/" + database;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(url, database_username, database_password);
        } catch (Exception e) {
            e.getStackTrace();
            e.getCause();
        }

        return databaseConnection;
    }
}
