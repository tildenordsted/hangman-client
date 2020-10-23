package Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLOutput;

public class ControllerLobby {

    public ListView lobbylistview = null;
    public Button newgame = null;
    public Button joingame = null;

    public void onClickEvent(MouseEvent mouseEvent) {
        System.out.println("You have joined a game");
    }
}
