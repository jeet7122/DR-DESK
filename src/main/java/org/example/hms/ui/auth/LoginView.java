package org.example.hms.ui.auth;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.hms.dao.UsersDAO;
import org.example.hms.models.User;
import org.example.hms.models.UserRoles;
import org.example.hms.services.AuthService;
import org.example.hms.ui.SceneManager;

public class LoginView {
    private final VBox root;
    private final AuthService  authService;
    private final UsersDAO usersDAO;
    public LoginView(){
        root = new VBox(10);
        authService = new AuthService();
        usersDAO = new UsersDAO();
        root.setPadding(new Insets(10));
        GridPane gridPane = new GridPane();

        Label title = new Label("Login to your account");
        title.getStyleClass().add("title");

        TextField email = new TextField();
        TextField password = new TextField();

        gridPane.add(new Label("Email :"), 0, 0);
        gridPane.add(email, 1, 0);
        gridPane.add(new Label("Password :"), 0, 1);
        gridPane.add(password, 1, 1);

        Label info = new Label("Register a new account?");
        Label register = new Label("Register");
        register.setOnMouseClicked(event -> {
            SceneManager.showRegistration();
        });
        HBox box = new HBox(10);
        box.getChildren().addAll(info, register);

        Button loginBTN = new Button("Login");
        root.getChildren().addAll(title, gridPane, loginBTN,  box);

        loginBTN.setOnAction(e -> {
            try {
                User user = usersDAO.findByEmail(email.getText());

                boolean loggedIn = authService.login(email.getText(), password.getText());
                if (loggedIn) {
                    SceneManager.showDashboard();
                }
                else {
                    System.out.println("Login failed");
                }
                email.clear();
                password.clear();
            }
            catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.showAndWait();
                System.out.println("Login Failed: " + ex.getMessage());
            }
        });

    }
    public Parent getView(){
        return root;
    }
}
