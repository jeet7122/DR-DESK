package org.example.hms.ui.department;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.hms.dao.DepartmentDAO;
import org.example.hms.models.Department;
import org.example.hms.ui.SceneManager;

public class UpdateDepartmentForm {
    private final VBox root;
    private final DepartmentDAO departmentDAO;
    public UpdateDepartmentForm(){
        root=new VBox(10);
        root.setPadding(new Insets(10));
        departmentDAO=new DepartmentDAO();

        Label title = new Label("Update Department");
        title.getStyleClass().add("form-title");
        GridPane grid = new GridPane();
        grid.getStyleClass().add("gp");
        grid.setHgap(10);
        grid.setVgap(10);

        TextField dep_id = new TextField();
        TextField name = new TextField();
        TextField description = new TextField();
        TextField location = new TextField();

        grid.add(new Label("Department ID:"), 0, 0);
        grid.add(dep_id, 1, 0);
        grid.add(new Label("New Department Name:"), 0, 1);
        grid.add(name, 1, 1);
        grid.add(new Label("New Department Description:"), 0, 2);
        grid.add(description, 1, 2);
        grid.add(new Label("New Department Location:"), 0, 3);
        grid.add(location, 1, 3);

        HBox buttons = new HBox(10);
        buttons.setSpacing(10);

        Button updateButton = new Button("Update Department");

        Button backButton = new Button("Back");
        buttons.getChildren().addAll(updateButton, backButton);

        backButton.setOnAction(e -> SceneManager.showDashboard());

        updateButton.setOnAction(e -> {
            try {
                Department department = new Department();
                department.setDepartmentName(name.getText());
                department.setDepartmentDescription(description.getText());
                department.setLocation(location.getText());
                departmentDAO.update(department, Integer.parseInt(dep_id.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Department");
                alert.setHeaderText("Success");
                alert.showAndWait();
            }
            catch (Exception ex){
                System.out.println("Error in inserting department" + ex.getMessage());
            }
        });

        root.getChildren().addAll(title, grid, buttons);
    }

    public Node getView() {
        return root;
    }
}
