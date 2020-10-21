package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hangman Login");

        //Velkomstlabel
        Label welcomeLabel = new Label("Velkommen til Hangman! Opret bruger her:");

        //Tekstboks
        TextField usernameField = new TextField("Indtast brugernavn");

        //Loginbutton
        Button loginButton = new Button("Opret");

        //Oprettelseslabel
        Label loginCheck = new Label();

        //Omrokerer indholdet
        welcomeLabel.relocate(150,125);
        usernameField.relocate(150,150);
        loginButton.relocate(320,150);
        loginCheck.relocate(150,200);

        //Respons på knappen
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (usernameField.getText() == null || usernameField.getText().trim().isEmpty()) {
                    loginCheck.setText("Fejl. Indtast venligst et brugernavn.");
                } else {
                    String username = usernameField.getText();
                    loginCheck.setText("Velkommen, " + username + "!");
                }
            }
        });


        //Opretter pane objekt (r = root)
        Pane r = new Pane();

        //Tilføjer label, username og loginbutton til tile
        r.getChildren().add(welcomeLabel);
        r.getChildren().add(usernameField);
        r.getChildren().add(loginButton);
        r.getChildren().add(loginCheck);


        primaryStage.setScene(new Scene(r, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
