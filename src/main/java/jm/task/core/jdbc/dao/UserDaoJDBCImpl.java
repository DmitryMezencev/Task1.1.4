package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_USERS_TABLE_SQL = """
                        CREATE TABLE IF NOT EXISTS users (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255),
                last_name VARCHAR(255),
                age TINYINT
            );
            """;

    private static final String DELETE_TABLE_USERS_SQL = """
            DROP TABLE IF EXISTS users;
            """;

    private static final String SAVE_SQL = """
            INSERT INTO users (name, last_name, age)
            VALUES (?, ?, ?);
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM users
            WHERE id = ?;
            """;

    private static final String CLEAR_SQL = """
        TRUNCATE TABLE users;
""";

    private static final String GET_ALL_USER_SQL = """
        SELECT id, name, last_name, age 
        FROM users;
""";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (var connection = Util.conect();
             var preparedStatement = connection.prepareStatement(CREATE_USERS_TABLE_SQL)) {
            preparedStatement.execute();
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(var connection = Util.conect();
        var preparedStatement = connection.prepareStatement(DELETE_TABLE_USERS_SQL)) {
        preparedStatement.execute();
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (var connection = Util.conect();
        var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (var connection = Util.conect();
             var preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("User с id - %s удален из базы данных\n", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (var connection = Util.conect();
        var preparedStatement = connection.prepareStatement(GET_ALL_USER_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<User> allUsers = new ArrayList<>();
            while (resultSet.next()) {
                allUsers.add(buildUser(resultSet));
            }
            System.out.println(allUsers);
            return allUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getString("name"),
                resultSet.getString("last_name"),
                resultSet.getObject("age", Byte.class)
        );
    }

    public void cleanUsersTable() {
        try (var connection = Util.conect();
        var preparedStatement = connection.prepareStatement(CLEAR_SQL)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
