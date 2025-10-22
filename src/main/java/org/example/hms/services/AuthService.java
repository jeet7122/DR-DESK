package org.example.hms.services;

import org.example.hms.dao.UsersDAO;
import org.example.hms.models.User;
import org.example.hms.models.UserRoles;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private final UsersDAO usersDAO;
    public AuthService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public AuthService() {
        usersDAO = new UsersDAO();
    }

    public boolean register(String firstname, String lastname, String email, String password, String roles) {
        if (usersDAO.findByEmail(email) != null) {
            System.out.println("User already exists");
            return false;
        }
        String hash_pass = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(firstname, lastname, email, hash_pass, UserRoles.valueOf(roles));
        usersDAO.insert(user);
        System.out.println("User registered successfully");
        return true;
    }

    public boolean login(String email, String password) {
        User user = usersDAO.findByEmail(email);
        if (user == null) {
            System.out.println("Incorrect email_id, user not found");
            return false;
        }
        if (BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("User logged in successfully");
            return true;
        }
        else {
            System.out.println("Incorrect password");
            return false;
        }
    }
}
