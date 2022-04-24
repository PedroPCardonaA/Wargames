package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditingArmyController extends Controller implements Initializable {

    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    Army army = new Army(singletonArmies.getArmy(singletonArmies.getArmyNumber()));
    @FXML
    private TextField nameOfTheArmy;

    @FXML
    private TextField name;

    @FXML
    private TextField health;

    @FXML
    private TextField attack;

    @FXML
    private TextField armor;

    @FXML
    private TextField attackSpeed;

    @FXML
    private TextField accuracy;

    @FXML
    private TextField criticalRate;

    @FXML
    private TextField criticalDamage;

    @FXML
    private TextField numberToAdd;

    @FXML
    private TextField numberToDelete;

    @FXML
    private ComboBox<String> unitType;

    @FXML
    private ListView<String> unitListView;

    @FXML
    private Label title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unitType.getItems().addAll("Infantry","Cavalry","Ranged","Magician","Commander");
        this.updateView();
    }

    private void updateView(){
        nameOfTheArmy.setPromptText(army.getName());
    }


    @FXML
    private void generatedArmyEditingArmyController(){
        try {
            army = this.generateArmy(this.stringInputWindow(nameOfTheArmy.getScene().getWindow()));
            this.checkNameAndUpdateSingleton(army);
            this.updateView();
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }

    @FXML
    private void loadFromAFileEditingArmyController(){
        try {
            File selectedFile = this.openFileChooser("Open a army file").showOpenDialog(null);
            if(selectedFile!=null){
                army = FileArmyHandler.readArmy(selectedFile.getAbsolutePath());
                this.checkNameAndUpdateSingleton(army);
                this.updateView();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    @FXML
    private void saveArmyAsFileFromEditingArmyController(){
        try {
            this.saveArmyAsAFile(singletonArmies.getArmyNumber());
        } catch (IOException e) {
            this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                    , e.getMessage());
        }
    }

    @FXML
    private void displayAllUnitsEditingArmyController(){
        try {
            this.openANewScene("/Views/DisplayArmy.fxml","Editing army",title);
            army = new Army(singletonArmies.getArmy(singletonArmies.getArmyNumber()));
            this.updateView();
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    @FXML
    private void close(){
        Stage stage = (Stage) nameOfTheArmy.getScene().getWindow();
        stage.close();
    }
}
