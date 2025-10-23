package org.example.hms.ui.auth;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.hms.dao.UsersDAO;
import org.example.hms.ui.SceneManager;


public class UpdateUsersView {
    private final VBox root;
    private final UsersDAO  usersDAO;
    public UpdateUsersView() {

        usersDAO = new UsersDAO();
        root = new VBox(10);
        root.setPadding(new Insets(10));
        Label title = new Label("Update Users Information");
        title.getStyleClass().add("form-title");
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gp");


        TextField user_id = new TextField();
        TextField email = new TextField();
        TextField password = new TextField();
        TextField roles = new TextField();
        Button submit = new Button("Submit");

        Label back = new Label("Back to Login");
        back.setOnMouseClicked(event -> SceneManager.showLogin());

        gridPane.add(new Label("User ID:"), 0, 0);
        gridPane.add(user_id, 1, 0);
        gridPane.add(new Label("Email:"), 0, 1);
        gridPane.add(email, 1, 1);
        gridPane.add(new Label("Password:"), 0, 2);
        gridPane.add(password, 1, 2);
        gridPane.add(new Label("Roles:"), 0, 3);
        gridPane.add(roles, 1, 3);
        root.getChildren().addAll(title, gridPane, submit, back);
        submit.setOnAction(event -> {
            try {
                usersDAO.updateUser(email.getText(), password.getText(), roles.getText(), Integer.parseInt(user_id.getText()));
                System.out.println("User updated successfully");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User updated successfully");
                alert.showAndWait();
            }
            catch (Exception ex){
                System.out.println("Generic Exception" + ex.getMessage());
            }
        });
    }
    public Parent getView(){
        return root;
    }
}
