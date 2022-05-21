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

/**
 * LoaderController class that is a part of the Controller hierarchy, extends
 * the class Controller and controls over the FXML file LoadingView.fxml
 * by defining all relevant JavaFx object in the FXML and all methods
 * that user can call them by interacting with the JavaFX objects.
 * LoaderController has as goal to display a load window with an image and an audio clip
 * before the main page.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class LoaderController extends Controller implements Initializable{

    @FXML
    private Pane pane;

    /**
     * Initialize method that is called after its root element is loaded.
     * This method define a new audio clip loading it from mp3 file
     * and play it. This also calls the help method start from the class LoadingView.
     *
     * @param url url - The location of the fxml file.
     * @param resourceBundle ResourceBundle - The resource used to localize the root object.
     */
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

    /**
     * Class LoadingView that extends the class Thread. This class is only used to have a timer
     * that define when the loading screen is closed.
     */
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

        /**
         * Method that check if the user want to close the main page.
         */
        private void closeProgram(){
            Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
            closeAlert.setTitle("Close window");
            closeAlert.setHeaderText("Exit Wargames application?");
            closeAlert.setContentText("Are you sure you want to exit this application?");
            Optional<ButtonType> result = closeAlert.showAndWait();
            if(result.get()==ButtonType.OK) Platform.exit();
        }
    }

    /**
     * LoaderController extends from the Controller abstract class,
     * but the abstract method updateView is not used here.
     */
    @Override
    protected void updateView() {
    }
}
