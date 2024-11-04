package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        Util.getSessionFactory();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Sanya", "Nikitin", (byte) 15);
        userService.saveUser("Никита", "Андреев", (byte) 19);
        userService.saveUser("Кирилл", "Егоров", (byte) 28);
        userService.saveUser("Егор", "Хмель", (byte) 43);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
