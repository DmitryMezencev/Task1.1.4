package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao USERDAO_HIBERNATE = new UserDaoHibernateImpl();

    public void createUsersTable() {
        USERDAO_HIBERNATE.createUsersTable();
    }

    public void dropUsersTable() {
        USERDAO_HIBERNATE.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        USERDAO_HIBERNATE.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных\n", name);
    }

    public void removeUserById(long id) {
        USERDAO_HIBERNATE.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = USERDAO_HIBERNATE.getAllUsers();
        System.out.println(allUsers);
        return allUsers;

    }

    public void cleanUsersTable() {
        USERDAO_HIBERNATE.cleanUsersTable();
    }
}
