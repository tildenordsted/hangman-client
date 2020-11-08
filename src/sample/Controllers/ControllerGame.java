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

    @FXML
    private Label playerTurn;

    @FXML
    private Label gameWord;


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

    public void setPlayersTurn(String str) {
        this.playerTurn.setText(str);
    }

    public String getPlayersTurn() {
        return this.playerTurn.getText();
    }

    //set and get username label
    public String getUsersList() {
        return this.usernameLabel.getText();
    }

    public void setUsersList(String str) {
        this.usernameLabel.setText(str);
    }

    //set and get gameword label
    public String getGameWord() {
        return this.gameWord.getText();
    }

    public void setGameWord(String str) {
        this.gameWord.setText(str);
    }
}
