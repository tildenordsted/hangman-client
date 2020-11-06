package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    //FXML IDer for Game Screen
    @FXML
    private Button submitButton;

    @FXML
    private TextField guessingField;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label guessedLettersBox;

    @FXML
    private ImageView hangmanImage;


    public ControllerGame(){}

    //initializer
    @Override
    public void initialize(URL url, ResourceBundle rb) { }


    //Getters and Setters

    public Button getSubmitGuessButton(){
        return this.submitButton;
    }

    public TextField getGuessingField(){
        return this.guessingField;
    }

    public Label getUsernameLabel() {
        return this.usernameLabel;
    }

    public ImageView getHangmanImage() { return this.hangmanImage; }

    public String getGuessedLettersBox() {
        return this.guessedLettersBox.getText();
    }

    public void setGuessedLettersBox(String str) {
        this.guessedLettersBox.setText(str);
    }


    //set and get username label
    public String getUsersList() {
        return this.usernameLabel.getText();
    }

    public void setUsersList(String str) {
        this.usernameLabel.setText(str);
    }
}
