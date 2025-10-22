package org.example.hms.tests;

import org.example.hms.dao.UsersDAO;
import org.example.hms.models.User;
import org.example.hms.models.UserRoles;
import org.example.hms.services.AuthService;

import java.util.ArrayList;
import java.util.List;

public class AuthTest {
    private final AuthService  authService;
    private final UsersDAO usersDAO;

    public AuthTest(AuthService authService, UsersDAO usersDAO) {
        this.authService = authService;
        this.usersDAO = usersDAO;
    }

    public AuthTest() {
        authService = new AuthService();
        usersDAO = new UsersDAO();
    }

    public void RegisterUser(String firstname, String lastname, String email, String hash_pass, String roles) {
        boolean register = authService.register(firstname, lastname, email, hash_pass, roles);
        System.out.println("User registered successfully");
    }
    public void getUsers() {
        List<User> users = usersDAO.getAll();
        users.forEach(System.out::println);
    }
    public void LoginUser(String email, String password) {
        boolean login = authService.login(email, password);
        if (login) {
            System.out.println("Login successfully");
        }
        else {
            System.out.println("Login failed");
        }
    }
}
