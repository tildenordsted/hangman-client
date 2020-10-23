package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class TestScene extends Application {
    public void start(Stage stage) {
        Label testSceneLabel = new Label("This is a new window");
        Pane pane = new Pane();
        pane.getChildren().addAll(testSceneLabel);

        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.setTitle("Hangman Lobby");
        stage.show();
    }
}
