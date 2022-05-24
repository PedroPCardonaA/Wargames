package edu.ntnu.idatt2001.pedropca.wargames;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Main class that has as goal to generate a unique instance of the SingletonArmies class
 * and open a JavaFX's window based on the FXML file loadingView.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class WarGamesApp extends Application {

    /**
     * Main method that starts the program, generate a unique instance of the SingletonArmies class
     * and calls the JavaFX's method launch.
     * @param args String Array - Arguments of the program.
     */
    public static void main(String[] args) {
        SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
        singletonArmies.putArmy(new Army("Army#1"),0);
        singletonArmies.putArmy(new Army("Army#2"),1);
        singletonArmies.putArmyInBackUp(new Army("Army#1"),0);
        singletonArmies.putArmyInBackUp(new Army("Army#2"),1);
        launch(args);
    }

    /**
     * JavaFX's method that generate a primary stage to display by loading
     * the FXML file LoadingPage.
     * @param primaryStage Stage - Main stage to display.
     */
    @Override
    public void start(Stage primaryStage){
        try {
            FXMLLoader loader = new FXMLLoader();
            primaryStage = new Stage();
            primaryStage.setTitle("Loading");
            primaryStage.setOnCloseRequest(windowEvent -> {
                windowEvent.consume();
                this.closeProgram();
            });
            loader.setLocation(getClass().getResource("/views/LoadingView.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Error by starting!");
            alert.setHeaderText("It was an error by starting the program!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Help method that check if the user want to close the application.
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
