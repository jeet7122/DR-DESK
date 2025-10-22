package org.example.hms.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.hms.models.UserRoles;
import org.example.hms.ui.auth.LoginView;
import org.example.hms.ui.auth.RegisterUserView;
import org.example.hms.utils.SessionManager;

public class SceneManager {
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void showDashboard() {
        if (!SessionManager.isLoggedIn()) {
            System.out.println("You are not logged in");
            showLogin();
        }
        UserRoles roles = SessionManager.getLoggedUser().getRole();
        Parent dashboard;
        int width = 1200;
        int height = 800;
        switch (roles) {
            case ADMIN:
                dashboard = new Dashboard().getView();
                break;
            case CLERK:
                dashboard = new DashboardForClerk().getView();
                break;
            default:
                System.out.println("Invalid role");
                showLogin();
                return;
        }
        Scene scene = new Scene(dashboard, width, height);
        scene.getStylesheets().add(SceneManager.class.getResource("/css/card.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    public static void showRegistration() {
        RegisterUserView view = new RegisterUserView();
        Scene scene = new Scene((Parent) view.getView(), 500, 600);
        scene.getStylesheets().add(SceneManager.class.getResource("/css/card.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    public static void showLogin() {
        SessionManager.Logout();
        LoginView view = new LoginView();
        Scene scene = new Scene(view.getView(), 500, 600);
        primaryStage.setScene(scene);
    }
}
