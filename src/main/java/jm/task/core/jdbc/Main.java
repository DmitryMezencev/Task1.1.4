package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Бил", "Гейтс", (byte) 45);
        userService.saveUser("Илон", "Маск", (byte) 43);
        userService.saveUser("Дмитрий", "Мезенцев", (byte) 20);
        userService.saveUser("Герман", "Севостьянов", (byte) 28);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
