package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    //FXML IDer for Login Screen
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private Label loginCheck;

    public ControllerLogin(){}

    //initializer
    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    //Getters and Setters
    public Label getLoginCheck(){
        return this.loginCheck;
    }

    public TextField getUserNameField(){
        return this.usernameField;
    }

    public Button getLoginButton(){
        return this.loginButton;
    }

}
