package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMySQLConnection() throws SQLException {
        String connectionURL = "jdbc:mysql://localhost:3306/pp";
        return DriverManager.getConnection(connectionURL, "root", "1234");
    }
}
