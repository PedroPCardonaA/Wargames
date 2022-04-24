package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Super class of the Controller hierarchy that contains all the help methods that
 * the other controller use. This class as a SingletonArmies instance as a field.
 */
public abstract class Controller {

    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();

    /**
     * Method that update the current field armies into the unique instance of
     * SingletonArmies class.
     * @param armyOne Army - Army saved on the field army1 of this class
     * @param armyTwo Army - Army saved on the field army2 of this class
     */
    protected void updateArmiesInSingleton(Army armyOne, Army armyTwo){
        singletonArmies.setEmptySingletonArmy();
        singletonArmies.putArmy(new Army(armyOne));
        singletonArmies.putArmy(new Army(armyTwo));
    }

    /**
     * Help method that update one of the armies in both list in the unique instance of
     * SingletonArmies class without changing the other army saved on the lists.
     * @param army Army - the army to be updated.
     * @param index int - the index of the armies on the list.
     */
    protected void updateArmyInBothListInTheSingleton(Army army, int index){
        if(index == 0){
            Army save = singletonArmies.getArmy(1);
            Army saveBackUp = singletonArmies.getArmyFromBackUp(1);
            singletonArmies.setEmptySingletonArmy();
            singletonArmies.setEmptyArmyBackUp();
            singletonArmies.putArmy(new Army(army));
            singletonArmies.putArmy(new Army(save));
            singletonArmies.putArmyInBackUp(new Army(army));
            singletonArmies.putArmyInBackUp(new Army(saveBackUp));
        }
        else{
            Army save = singletonArmies.getArmy(0);
            Army saveBackUp = singletonArmies.getArmyFromBackUp(0);
            singletonArmies.setEmptySingletonArmy();
            singletonArmies.setEmptyArmyBackUp();
            singletonArmies.putArmy(new Army(save));
            singletonArmies.putArmy(new Army(army));
            singletonArmies.putArmyInBackUp(new Army(saveBackUp));
            singletonArmies.putArmyInBackUp(new Army(army));
        }
    }

    /**
     * Help method that save a defined army in with the help of help method
     * openFileChooser that allow this method to define a folder to store the ary.
     * @param index int - Index of the army to be stored.
     * @throws IOException Static method writeAFile mat throw an IOException
     */
    protected void saveArmyAsAFile(int index) throws IOException {
        File file = this.openFileChooser("Save army as a file.").showSaveDialog(null);
        if(file !=null){
            FileArmyHandler.writeAFile(new Army(singletonArmies.getArmy(index)),file.getParent(),file.getName());
        }
    }

    /**
     * Help method that returns an instance of the Class FIleChooser from javaFX.
     * @param title String - Tittle of the FileChooser instance.
     * @return FileChooser instance.
     */
    protected FileChooser openFileChooser(String title){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
        return fileChooser;
    }

    /**
     * Method that returns an army that gets name from the signature
     * adn with some pre-define units.
     * @param name String - Name of the army.
     * @return Army - The new generated army.
     * @throws IllegalArgumentException The constructor of class Army may throw an IllegalArgumentException
     */
    protected @NotNull Army generateArmy(String name) throws IllegalArgumentException{
        Army army = new Army( name);
        List<Unit> mixedList = new ArrayList<>();
        for(int i =0;i<50;i++){
            mixedList.add(new CavalryUnit("CAVALRY",100));
            mixedList.add(new RangedUnit("Ranged",100));
            mixedList.add(new InfantryUnit("Infantry",100));
            mixedList.add(new MagicianUnit("Magician",100));
        }
        mixedList.add(new CommanderUnit("Commander",250));
        army.addAll(mixedList);
        return army;
    }

    /**
     * Method that return a direct input string from the user by opening a new window
     * field a text field where the user can introduce the name of the new army.
     * @param parentWindow Window - Window where this method was called from.
     * @return String - Name of the army
     */
    protected String stringInputWindow(Window parentWindow){
        Stage stringInputWindow = new Stage();
        stringInputWindow.initOwner(parentWindow);
        stringInputWindow.initModality(Modality.WINDOW_MODAL);
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        TextField commentBox = new TextField();
        commentBox.setPromptText("Enter the name of the army.");
        commentBox.setFocusTraversable(false);
        commentBox.setAlignment(Pos.CENTER);
        commentBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        commentBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        Label commentLabel = new Label("Enter the name of the Army: ");
        commentLabel.setFont(Font.font("Papyrus", FontWeight.BOLD,24));
        commentLabel.setMinHeight(Control.USE_COMPUTED_SIZE);
        commentLabel.setMinWidth(Control.USE_COMPUTED_SIZE);
        Button button = new Button("Enter name.");
        button.setOnAction(actionEvent -> {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
            actionEvent.consume();
        });
        box.getChildren().addAll(commentLabel,commentBox,button);
        stringInputWindow.setScene(new Scene(box,375,150));
        stringInputWindow.showAndWait();
        return commentBox.getText();
    }

    /**
     * Method that generates and shows an error alert with a title, message and text defined in the signature.
     * @param title String - Title of the alert.
     * @param message String - message of the alert.
     * @param text String - text of the alert.
     */
    protected void showError(String title,String message,String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(text);
        alert.showAndWait();
    }

    protected void checkNameAndUpdateSingleton(Army army) {
        if(singletonArmies.getArmyNumber()==0){
            if (army.getName().equals(singletonArmies.getArmy(1).getName())){
                army.setName(army.getName()+"-2");
            }
            this.updateArmyInBothListInTheSingleton(army,0);
        }
        if(singletonArmies.getArmyNumber()==1){
            if (army.getName().equals(singletonArmies.getArmy(0).getName())){
                army.setName(army.getName()+"-2");
            }
            this.updateArmyInBothListInTheSingleton(army,1);
        }
    }
}