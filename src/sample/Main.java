package sample;

import com.hangman.message.Message;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Controllers.ControllerGame;
import sample.Controllers.ControllerLobby;
import sample.Controllers.ControllerLogin;

import java.io.*;
import java.net.Socket;

public class Main extends Application {

    //port and hostname
    private int port = 6666;
    private String hostName = "localhost";
    private int imageToShow = 1;

    //Input, output streams for client
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    //launch main
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //instanciate controllers
        ControllerLobby controllerLobby = new ControllerLobby();
        ControllerLogin controllerLogin = new ControllerLogin();
        ControllerGame controllerGame = new ControllerGame();

        //load login scene
        FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("Resources/login.fxml"));
        loaderLogin.setController(controllerLogin);
        Parent loginSceneRoot = loaderLogin.load();

        //load lobby scene
        FXMLLoader loaderLobby = new FXMLLoader(getClass().getResource("Resources/game_lobby.fxml"));
        loaderLobby.setController(controllerLobby);
        Parent lobbySceneRoot = loaderLobby.load();

        //load game scene
        FXMLLoader loaderGame = new FXMLLoader(getClass().getResource("Resources/game.fxml"));
        loaderGame.setController(controllerGame);
        Parent gameSceneRoot = loaderGame.load();

        //show Loginscene
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(loginSceneRoot));
        primaryStage.show();


        //connect to server
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(hostName, port);

            // Init IO object streams
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            //thread for reading messages (objects) from server
            new Thread(() ->{

                while(true){
                    try {

                        //read message object
                        //@SuppressWarnings("unchecked")
                        //Object obj = objectInputStream.readObject();

                        

                        Message message = (Message) objectInputStream.readObject();


                        System.out.println(message.getMessage());

                        //get type of message in object

                        String typeOfMessage = message.getTypeOfMessage();

                        //  System.out.println(typeOfMessage);


                        //if message is of type 'greeting' (server has recieved a username)
                        if (typeOfMessage.equalsIgnoreCase("greeting")) {

                            //get the message of object, and write to Label
                            String messageFromServer = message.getMessage();

                            //write to Text object in game_lobby.fxml
                            Platform.runLater(() -> {
                                controllerLobby.getGreetingMessage().setText(messageFromServer);
                            });

                        }

                        //if message from server is update
                        if (typeOfMessage.equalsIgnoreCase("updatelobby")) {

                            //get the message of object, and write to Label
                            String messageFromServer = message.getMessage();

                            //first player in, will see an empty lobby
                            if (!messageFromServer.isEmpty()) {
                                Platform.runLater(() -> {
                                    controllerLobby.loadData(messageFromServer);
                                });
                            }
                        }

                        //if message from server is to start game
                        //TODO ved ikke hvad der skal ske her
                        if (typeOfMessage.equalsIgnoreCase("startgame")) {

                            //get the message of object, and write to Label
                            //String messageFromServer = message.getMessage();

                            //objectOutputStream.writeObject(new Message("h", "guess"));

                            //continue;

                        }

                        //TODO lidt usikker på den her også.
                        if (typeOfMessage.equalsIgnoreCase("updatelives")) {

                            //get the message
                            String messageFromServer = message.getMessage();

                            drawHangman(imageToShow, controllerGame.getHangmanImage());

                        }

                        //if message from server is to update guessed words
                        if (typeOfMessage.equalsIgnoreCase("newguess")) {

                            //get the message
                            String newGuess = message.getMessage();

                            //add guess to guessedLettersBox Label
                            Platform.runLater(() -> {
                                controllerGame.setGuessedLettersBox(controllerGame.getGuessedLettersBox() + "\n" + newGuess);
                            });

                        }

                        //if message from is to update names of users list
                        if (typeOfMessage.equalsIgnoreCase("updateusers")) {

                            //get the message
                            String strOfUsers = message.getMessage();
                            System.out.println(strOfUsers);

                            //add username to Label
                            //controllerGame.setGuessedLettersBox(controllerGame.getGuessedLettersBox() + "\n" + newGuess);
                            Platform.runLater(() -> {
                                controllerGame.setUsersList(strOfUsers);
                            });
                        }


                            if (typeOfMessage.equalsIgnoreCase("playersturn")) {

                                //get the message
                                String messagePlayersTurn = message.getMessage();
                                System.out.println(messagePlayersTurn);
                                System.out.println(controllerGame.getPlayersTurn() + "\n" + messagePlayersTurn);

                                //add username to Label
                                Platform.runLater(() -> {
                                    controllerGame.setPlayersTurn(controllerGame.getPlayersTurn() + "\n" + messagePlayersTurn);
                                });
                            }

                        if (typeOfMessage.equalsIgnoreCase("updateword")) {

                            //get the message
                            String messageUpdateWord = message.getMessage();

                            //add username to Label
                            Platform.runLater(() -> {
                                controllerGame.setGameWord(messageUpdateWord);
                            });
                        }

                        //if update image
                        if (typeOfMessage.equalsIgnoreCase("updateimage")) {

                            //get the message
                            String messageUpdateImage = message.getMessage();

                            //update imageToShow
                            imageToShow++;

                            //update image
                            drawHangman(imageToShow, controllerGame.getHangmanImage());

                        }


                    }catch(IOException | ClassNotFoundException ex){
                        ex.printStackTrace();

                    }

                    try {
                        objectOutputStream.flush();

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }//end while true

            }).start(); //start listening to server messages

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Server connection failed");
        }


        //handle login button event
        controllerLogin.getLoginButton().setOnAction((event) -> {

            String userName = controllerLogin.getUserNameField().getText();
            Label loginCheck = controllerLogin.getLoginCheck();

            //validate user entered name
            if (userName == null || userName.trim().isEmpty()) {
                loginCheck.setText("Fejl: Indtast venligst et brugernavn for at spille.");

            }else {

                try {
                    //compose and send message object to server
                    Message messageUserName = new Message(userName, "username");
                    objectOutputStream.writeObject(messageUserName);

                    //switch to Lobby, if username field is okay
                    //loadLobbyScene(primaryStage, controllerLobby);
                    //TODO load Lobby scene
                    //show Lobby scene
                    primaryStage.setTitle("Hangman Lobby");
                    primaryStage.setScene(new Scene(lobbySceneRoot));
                    primaryStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); //end handle button login

        //handle Join Game button event
        controllerLobby.getJoinButton().setOnAction((event) -> {
            System.out.println("Join button pressed");

            Observable lobbyChoosen = controllerLobby.getListViewItem();


            String strLobby = lobbyChoosen.toString();

            strLobby = strLobby.substring(1, strLobby.length() - 1);
            System.out.println(strLobby);

            //create message object with choosen gameroom index and send to server
            try {
                Message messageChoosenGameRoom = new Message(strLobby, "join");
                objectOutputStream.writeObject(messageChoosenGameRoom);

                //show Game scene
                primaryStage.setTitle("Hangman Game");
                primaryStage.setScene(new Scene(gameSceneRoot));
                primaryStage.show();

                //set first image
                drawHangman(1, controllerGame.getHangmanImage());


            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }); //end handle button join

        //handle New Game button event
        controllerLobby.getNewGameButton().setOnAction((event) -> {
            System.out.println("New game button pressed");

            //create message object that a new game room should be created
            try {
                Message messageChoosenGameRoom = new Message("newgame", "newgame");
                objectOutputStream.writeObject(messageChoosenGameRoom);

                //show Game scene
                primaryStage.setTitle("Hangman Game");
                primaryStage.setScene(new Scene(gameSceneRoot));
                primaryStage.show();


                //set first image
                drawHangman(1, controllerGame.getHangmanImage());

            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }); //end handle button create

        //handle Guess word Button
        controllerGame.getSubmitGuessButton().setOnAction((event) -> {

            //get guess
            String guess = controllerGame.getGuessingField().getText();

            //add guess to guessedLettersBox Label
            //controllerGame.setGuessedLettersBox(controllerGame.getGuessedLettersBox() + "\n" + guess);

            //create message object to server with guess
            try {
                Message messageWithGuess = new Message(guess, "guess");
                objectOutputStream.writeObject(messageWithGuess);


            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }); //end handle button guess word

    }//end start

    public void drawHangman(int lives, ImageView imageView) throws FileNotFoundException {
        Image hm01 = new Image(new FileInputStream("graphics/hm01.jpg"));
        Image hm02 = new Image(new FileInputStream("graphics/hm02.jpg"));
        Image hm03 = new Image(new FileInputStream("graphics/hm03.jpg"));
        Image hm04 = new Image(new FileInputStream("graphics/hm04.jpg"));
        Image hm05 = new Image(new FileInputStream("graphics/hm05.jpg"));
        Image hm06 = new Image(new FileInputStream("graphics/hm06.jpg"));
        Image hm07 = new Image(new FileInputStream("graphics/hm07.jpg"));
        Image hm08 = new Image(new FileInputStream("graphics/hm08.jpg"));
        Image hm09 = new Image(new FileInputStream("graphics/hm09.jpg"));
        Image hm10 = new Image(new FileInputStream("graphics/hm10.jpg"));

        switch (lives) {
            case 1:
                Platform.runLater(() -> {
                    imageView.setImage(hm01);
                });
                break;
            case 2:
                Platform.runLater(() -> {
                    imageView.setImage(hm02);
                });
                break;
            case 3:
                Platform.runLater(() -> {
                    imageView.setImage(hm03);
                });
                break;
            case 4:
                Platform.runLater(() -> {
                    imageView.setImage(hm04);
                });
                break;
            case 5:
                Platform.runLater(() -> {
                    imageView.setImage(hm05);
                });
                break;
            case 6:
                Platform.runLater(() -> {
                    imageView.setImage(hm06);
                });
                break;
            case 7:
                Platform.runLater(() -> {
                    imageView.setImage(hm07);
                });
                break;
            case 8:
                Platform.runLater(() -> {
                    imageView.setImage(hm08);
                });
                break;
            case 9:
                Platform.runLater(() -> {
                    imageView.setImage(hm09);
                });
                break;
            case 10:
                Platform.runLater(() -> {
                    imageView.setImage(hm10);
                });
                break;
        }
    }

 /*   public void loadLoginScene(Stage stage, ControllerLogin controllerLogin) throws IOException{
        //LoginScene loginScene = new LoginScene();
        //loginScene.start(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Resources/login.fxml"));

        loader.setController(controllerLogin);

        Parent loginSceneRoot = loader.load();

        stage.setTitle("Login");
        stage.setScene(new Scene(loginSceneRoot));
        stage.show();

    }

    public void loadLobbyScene(Stage stage, ControllerLobby controllerLobby) throws IOException{
        //LoginScene loginScene = new LoginScene();
        //loginScene.start(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Resources/game_lobby.fxml"));

        loader.setController(controllerLobby);

        Parent loginSceneRoot = loader.load();

        stage.setTitle("Game Lobby");
        stage.setScene(new Scene(loginSceneRoot));
        stage.show();

    }*/
}


