package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerLobby implements Initializable {

    //Laver observablelist object til at putte lobbylist ind i for at kunne displaye i lobbylistview
    ObservableList lobbylist = FXCollections.observableArrayList();

    //FXML IDer
    @FXML
    public ListView<String> lobbylistview = null;

    @FXML
    public Button newgame = null;

    @FXML
    public Button joingame = null;

    //initializer loadData funktion samt disableproperty til joingame button før at man vælger en lobby
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        joingame.disableProperty().bind(lobbylistview.getSelectionModel().selectedItemProperty().isNull());
    }

    //Setter loadData funktion til at displaye placeholder lobby-strings fra lobbylist i lobbylistview
    public void loadData() {
        lobbylist.clear();
        String a = "Placeholder lobby nr. 1     |   3/3 players";
        String b = "Placeholder lobby nr. 2     |   2/3 players";
        lobbylist.addAll(a, b);
        lobbylistview.getItems().addAll(lobbylist);
    }


    //onClickEvent ser først hvilken knap der trykkes og så gemmes knap-tekst i buttontext String. Det printes som svar alt
//efter hvilken knap trykkes på baseret på switch statement
    public void onClickEvent(MouseEvent mouseEvent) {

        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case "Create new game":
                System.out.println("You have started a new game");
                break;
            case "Join game":
                System.out.println("You have joined a game");
                break;
        }
    }
}



