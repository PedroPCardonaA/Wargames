package edu.ntnu.idatt2001.pedropca.wargames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage = new Stage();
        primaryStage.setTitle("War Games");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/hello-view.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
    }
}
