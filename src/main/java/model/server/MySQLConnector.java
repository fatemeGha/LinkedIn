package model.server;

import java.sql.*;


public class MySQLConnector {

    private static Connection connection;



    public static void makeConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/linkedin", "root" , "fafa1384X$");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to MySQL database", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to MySQL database", e);
        }
    }
}
