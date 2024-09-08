package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.conect();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Бил", "Гейтс", (byte) 45);
        userDao.saveUser("Илон", "Маск", (byte) 43);
        userDao.saveUser("Дмитрий", "Мезенцев", (byte) 20);
        userDao.saveUser("Герман", "Севостьянов", (byte) 28);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
