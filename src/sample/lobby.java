package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lobby extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //Loader fxml-fil
        Parent gamelobbyroot = FXMLLoader.load(getClass().getResource("Resources/game_lobby.fxml"));


        //laver stage/window
        stage.setTitle("Hangman Lobby");

        //laver scene
        Scene lobby = new Scene(gamelobbyroot);


        //viser scene ind i stage
        stage.setScene(lobby);

        stage.show();
    }
}
