package edu.ntnu.idatt2001.pedropca.wargames;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage = new Stage();
        primaryStage.setTitle("War Games");
        primaryStage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            this.closeProgram();
        });
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/GUI.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
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
