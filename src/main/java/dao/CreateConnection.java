package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {
    private final static String PASSWORD = "script101";
    private final static String USERNAME = "root";
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/flightbookingapp";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
