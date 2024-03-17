package app.config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static app.constants.DbConfigProperties.*;

public class DbConfig {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading MySQL JDBC Driver");
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }
}
