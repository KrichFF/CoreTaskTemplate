package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMySQLConnection() {
        String connectionURL = "jdbc:mysql://localhost:3306/pp";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(connectionURL, "root", "1234");
        }catch (SQLException e){
            System.out.println("Открылось");
        }
        return connection;
    }
    public static void disconnect(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не закрылось");
        }
    }
}
