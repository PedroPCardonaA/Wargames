package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

//TODO: ADD JavaDoc
public class LoaderController extends Controller implements Initializable{
    @FXML
    private ImageView imageView;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/you are not prepared.mp3")).toString());
            audioClip.setVolume(0.5);
            audioClip.play();
            new LoadingView().start();
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    class LoadingView extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3500);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/MainPage.fxml")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        stage.setTitle("War Games");
                        stage.setOnCloseRequest(windowEvent -> {
                            windowEvent.consume();
                            closeProgram();
                        });
                        stage.setScene(new Scene(Objects.requireNonNull(root)));
                        stage.show();
                        pane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException e) {
                showError("Error by loading", "It happen an error by loading the application","Take contact with the administrators");
            }
        }

        private void closeProgram(){
            Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
            closeAlert.setTitle("Close window");
            closeAlert.setHeaderText("Exit Wargames application?");
            closeAlert.setContentText("Are you sure you want to exit this application?");
            Optional<ButtonType> result = closeAlert.showAndWait();
            if(result.get()==ButtonType.OK) Platform.exit();
        }
    }

    @Override
    protected void updateView() {

    }
}
