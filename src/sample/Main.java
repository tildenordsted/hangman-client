package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hangman Login");

        //Velkomstlabel
        Label welcomeLabel = new Label("Velkommen til Hangman, opret bruger her:");

        //Tekstboks
        TextField usernameField = new TextField("Indtast brugernavn");

        //Loginbutton
        Button loginButton = new Button("Opret");

        //Opretter tile pane objekt (r = root)
        TilePane r = new TilePane();

        //Tilføjer label, username og loginbutton til tile
        r.getChildren().add(welcomeLabel);
        r.getChildren().add(usernameField);
        r.getChildren().add(loginButton);



        primaryStage.setScene(new Scene(r, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
