package org.example.hms.utils;

import org.example.hms.models.User;

public class SessionManager {
    public static User loggedUser;

    public static void Login(User user) {
        loggedUser = user;
    }
    public static void Logout() {
        loggedUser = null;
    }
    public static boolean isLoggedIn() {
        return loggedUser != null;
    }
    public static User getLoggedUser() {
        return loggedUser;
    }
}
