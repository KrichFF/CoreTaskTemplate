package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE if not exists user (" +
                    "  id BIGINT NOT NULL AUTO_INCREMENT," +
                    "  FirstName VARCHAR(45) NULL," +
                    "  LastName VARCHAR(45) NULL," +
                    "  age TINYINT NOT NULL," +
                    "  PRIMARY KEY (id));");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE if exists user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT user (FirstName, LastName, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', " + age + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM user where id = '" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from user");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("TRUNCATE TABLE  user;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
