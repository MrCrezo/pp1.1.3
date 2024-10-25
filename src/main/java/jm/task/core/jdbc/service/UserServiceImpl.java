package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "lastName VARCHAR(50) NOT NULL, "
                + "age INT)";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO  users (name, lastName, age) VALUES(?, ?, ?)";

        try (Connection u = Util.getConnection(); PreparedStatement preparedStatement = u.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    public void removeUserById(long id) {

        String sql = "delete from users where id = ?";

        try (Connection u = Util.getConnection(); PreparedStatement preparedStatement = u.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        try (Connection u = Util.getConnection();
             Statement statement = u.createStatement();
             ResultSet res = statement.executeQuery(sql)) {

            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users";

        try (Connection u = Util.getConnection(); Statement statement = u.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
