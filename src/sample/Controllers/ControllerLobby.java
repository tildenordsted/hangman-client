package sample.Controllers;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerLobby implements Initializable {

    //Laver observablelist object til at putte lobbylist ind i for at kunne displaye i lobbylistview
    ObservableList<String> lobbylist = FXCollections.observableArrayList();


    //FXML IDer for Lobby Screen
    @FXML
    private ListView<String> lobbylistview;
    @FXML
    private Button newgame;
    @FXML
    private Button joingame;
    @FXML
    private Text greetingMessage;


    public ControllerLobby() {
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    //Setter loadData funktion til at displaye placeholder lobby-strings fra lobbylist i lobbylistview
    public void loadData(String strLobbyData) {
        //first clear list before update
        lobbylistview.getItems().clear();

        //split string by ":" (string sent from server is roomnumber:usercount:roomnumber:usercount ...)
        String[] arrOfStr = strLobbyData.split(":");


        for(int i = 0; i < arrOfStr.length; i += 2){
            String strToAdd = ("Gameroom nr. " + arrOfStr[i] + "         |           " + arrOfStr[i + 1] + "/3 players");

            //add string to listview
            lobbylistview.getItems().add(strToAdd);
        }
        arrOfStr = null;

    }


    //Getters and Setters
    public Text getGreetingMessage() {
        return this.greetingMessage;
    }

    public Button getJoinButton() {
        return this.joingame;
    }


    public Button getNewGameButton() {
        return this.newgame;
    }

    public Observable getListViewItem() {
        return lobbylistview.getSelectionModel().getSelectedIndices();
    }



}



