package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.security.spec.ECField;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hangman Login");

        //HBox til text field og loginknap
        HBox loginboxH = new HBox();
        loginboxH.setSpacing(10);
        loginboxH.setAlignment(Pos.CENTER);

        //VBox til velkomstlabel og HBox
        VBox loginboxV = new VBox();
        loginboxV.setSpacing(15);
        loginboxV.setAlignment(Pos.CENTER);

        //Velkomstlabel
        Label welcomeLabel = new Label("Velkommen til Hangman! \n Opret bruger her:");
        welcomeLabel.setTextAlignment(TextAlignment.CENTER);

        //Tekstboks
        TextField usernameField = new TextField("Indtast brugernavn");

        //Loginbutton
        Button loginButton = new Button("Opret");

        //Oprettelseslabel
        Label loginCheck = new Label();


        //Respons på Loginbutton
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (usernameField.getText() == null || usernameField.getText().trim().isEmpty()) {
                    loginCheck.setText("Fejl: Indtast venligst et brugernavn for at spille.");
                } else {
                    String username = usernameField.getText();
                    serverConnect(username);
                    loginCheck.setText("Velkommen, " + username + "!");
                }
            }
        });

        //Tilføjer usernameField og loginButton til HBox
        loginboxH.getChildren().addAll(usernameField, loginButton);

        //Tilføjer HBox og velkomstLabel til VBox
        loginboxV.getChildren().addAll(welcomeLabel, loginboxH, loginCheck);

        //Opretter pane objekt (r = root)
        StackPane r = new StackPane();


        //Tilføjer VBox til tile
        r.getChildren().add(loginboxV);


        primaryStage.setScene(new Scene(r, 500, 500));
        primaryStage.show();
    }

    public void serverConnect(String username) {
        try {

            //Opretter socket objekt
            Socket socket = new Socket("localhost",6666);

            //Client/Server input/output
            DataOutputStream outputFromClient = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());

            //Sender username-string til server
            outputFromClient.writeUTF(username);

            //Modtager forbindelsesbesked fra serveren
            String stringFromServer = (String)inputFromServer.readUTF();

            System.out.println(stringFromServer);

            outputFromClient.close();
            socket.close();

        } catch (Exception e) {

            //Fejlbesked
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
