package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GameScene extends Application {
    private Image hm01 = new Image(new FileInputStream("graphics/hm01.jpg"));
    private Image hm02 = new Image(new FileInputStream("graphics/hm02.jpg"));
    private Image hm03 = new Image(new FileInputStream("graphics/hm03.jpg"));
    private Image hm04 = new Image(new FileInputStream("graphics/hm04.jpg"));
    private Image hm05 = new Image(new FileInputStream("graphics/hm05.jpg"));
    private Image hm06 = new Image(new FileInputStream("graphics/hm06.jpg"));
    private Image hm07 = new Image(new FileInputStream("graphics/hm07.jpg"));
    private Image hm08 = new Image(new FileInputStream("graphics/hm08.jpg"));
    private Image hm09 = new Image(new FileInputStream("graphics/hm09.jpg"));
    private Image hm10 = new Image(new FileInputStream("graphics/hm10.jpg"));
    private int lives = 1;

    public GameScene() throws FileNotFoundException {
    }


    public void start(Stage stage) {
        Pane pane = new Pane();
        Button plusButton = new Button("+");
        plusButton.setTranslateY(400);
        pane.getChildren().add(drawHangman(lives));
        plusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                lives += 1;
                System.out.println(lives);
                pane.getChildren().add(drawHangman(lives));
            }
        });
        pane.getChildren().add(plusButton);

        Scene scene = new Scene(pane, 500,500);
        stage.setScene(scene);
        stage.setTitle("Hangman Game");
        stage.show();
    }

    public Group drawHangman(int lives) {
        ImageView imageView = new ImageView();
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(350);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        switch (lives) {
            case 1:
                imageView.setImage(hm01);
                break;
            case 2:
                imageView.setImage(hm02);
                break;
            case 3:
                imageView.setImage(hm03);
                break;
            case 4:
                imageView.setImage(hm04);
                break;
            case 5:
                imageView.setImage(hm05);
                break;
            case 6:
                imageView.setImage(hm06);
                break;
            case 7:
                imageView.setImage(hm07);
                break;
            case 8:
                imageView.setImage(hm08);
                break;
            case 9:
                imageView.setImage(hm09);
                break;
            case 10:
                imageView.setImage(hm10);
                break;
        }
        Group drawHangman = new Group(imageView);
        return drawHangman;
    }

    public void drawWord(String word) {

    }
}
