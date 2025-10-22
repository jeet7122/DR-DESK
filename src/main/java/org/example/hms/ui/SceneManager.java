package org.example.hms.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage primaryStage;
    public static void init(Stage stage) {
        primaryStage = stage;
    }
    public static void showDashboard(){
        Dashboard view = new Dashboard();
        Scene scene = new Scene(view.getView(), 1200, 800);
        scene.getStylesheets().add(SceneManager.class.getResource("/css/card.css").toExternalForm());
        primaryStage.setScene(scene);
    }
}
