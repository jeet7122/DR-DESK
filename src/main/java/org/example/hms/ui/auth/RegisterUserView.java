package org.example.hms.ui.auth;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.hms.services.AuthService;
import org.example.hms.ui.SceneManager;

public class RegisterUserView {
    private final VBox root;
    private final AuthService authService;

    public RegisterUserView() {
        authService = new AuthService();
        root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        Label title = new Label("Register Account");
        title.getStyleClass().add("form-title");
        GridPane form = new GridPane();
        form.getStyleClass().add("gp");
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField password = new TextField();
        TextField role = new TextField();

        form.add(new Label("First Name:"), 0, 0);
        form.add(firstName, 1, 0);
        form.add(new Label("Last Name:"), 0, 1);
        form.add(lastName, 1, 1);
        form.add(new Label("Email:"), 0, 2);
        form.add(email, 1, 2);
        form.add(new Label("Password:"), 0, 3);
        form.add(password, 1, 3);
        form.add(new Label("Role:"), 0, 4);
        form.add(role, 1, 4);
        Button registerBTN = new Button("Register");
        HBox box = new HBox(10);
        box.getStyleClass().add("box");
        Label info = new Label("Already have an account?");
        Label login = new Label("Login");
        login.setOnMouseClicked(e -> SceneManager.showLogin());
        box.getChildren().addAll(info, login);
        registerBTN.setOnAction(e -> {
            try {
                authService.register(firstName.getText(), lastName.getText(), email.getText(), password.getText(), role.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Registered Successfully");
                alert.showAndWait();
                firstName.clear();
                lastName.clear();
                email.clear();
                password.clear();
                role.clear();
            } catch (Exception ex) {
                System.out.println("Error" + ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Registering User");
                alert.showAndWait();
            }
        });
        root.getChildren().addAll(title, form, registerBTN, box);
    }

    public Node getView() {
        return root;
    }
}
