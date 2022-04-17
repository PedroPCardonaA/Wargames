package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    Army army1 = new Army(singletonArmies.getArmy(0));
    Army army2 = new Army(singletonArmies.getArmy(1));


    @FXML
    private TextField totalArmy1;

    @FXML
    private TextField totalArmy2;

    @FXML
    private TextField infantryArmy1;

    @FXML
    private TextField infantryArmy2;

    @FXML
    private TextField rangedArmy1;

    @FXML
    private TextField rangedArmy2;

    @FXML
    private TextField cavalryArmy1;

    @FXML
    private TextField cavalryArmy2;

    @FXML
    private TextField commanderArmy1;

    @FXML
    private TextField commanderArmy2;

    @FXML
    private Label armyOneName;

    @FXML
    private Label armyTwoName;

    @FXML
    private ComboBox<String> terrainComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrainComboBox.getItems().addAll("Forest","Hills","Plains","Volcano");
    }

    @FXML
    protected void simulateBattle(){
        this.updateSingletonTerrain();
        Battle battle = new Battle(army1,army2);
        Army winner = battle.simulate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result of the battle.");
        alert.setHeaderText("The result of the battle!");
        if(winner==null) alert.setContentText("It was a draw!");
        else alert.setContentText("The winner was: " +winner.getName() + " !");
        this.updateArmies(winner);
        this.updateView();
        alert.showAndWait();
    }

    private void updateSingletonTerrain(){
        SingletonTerrain singletonTerrain = SingletonTerrain.getSingletonTerrain();
        if(!(terrainComboBox.getValue() ==null)) {
            switch (terrainComboBox.getValue()) {
                case "Forest":
                    singletonTerrain.setForestAsTerrain();
                    break;
                case "Hills":
                    singletonTerrain.setHillsAsTerrain();
                    break;
                case "Plains":
                    singletonTerrain.setPlainsAsTerrain();
                    break;
                case "Volcano":
                    singletonTerrain.setVolcanoAsTerrain();
                    break;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Terrain was not defined.");
            alert.setHeaderText("Terrain was not defined!");
            alert.setContentText("Forest will be defined instead.");
            alert.setResizable(true);
            alert.showAndWait();
            singletonTerrain.setForestAsTerrain();
        }
    }

    private void updateArmies(Army lastArmy){
        if(lastArmy!=null){
            if(army1.equals(lastArmy)){
                army1 = new Army(lastArmy);
                army2 = new Army(army2.getName());
                this.updateArmiesInSingleton(lastArmy,army2);
            } else{
                army2 = new Army(lastArmy);
                army1 = new Army(army1.getName());
                this.updateArmiesInSingleton(army1,lastArmy);
            }
        } else {
            army1 = new Army(army1.getName());
            army2 = new Army(army2.getName());
            this.updateArmiesInSingleton(army1,army2);
        }
    }

    private void updateArmiesInSingleton(Army army1, Army army2){
        singletonArmies.setEmptySingletonArmy();
        singletonArmies.putArmy(new Army(army1));
        singletonArmies.putArmy(new Army(army2));
    }

    /**
     * Method that "Resets" the main armies by defined them as copies of the buck up armies.
     * After the restarting of the armies' method updates the GUI by calling help method updateView().
     */
    @FXML
    private void resetArmies(){
        army1 = new Army(singletonArmies.getArmyFromBackUp(0));
        army2 = new Army(singletonArmies.getArmyFromBackUp(1));
        this.updateView();
    }

    /**
     * Help method that update the visible information from the army to the GUI.
     */
    private void updateView(){
        armyOneName.setText(army1.getName());
        totalArmy1.setText(army1.getAllUnits().size()+"");
        infantryArmy1.setText(army1.getInfantryUnits().size()+"");
        rangedArmy1.setText(army1.getRangedUnits().size()+"");
        cavalryArmy1.setText(army1.getCavalryUnits().size()+"");
        commanderArmy1.setText(army1.getCommanderUnits().size()+"");
        armyTwoName.setText(army2.getName());
        totalArmy2.setText(army2.getAllUnits().size()+"");
        infantryArmy2.setText(army2.getInfantryUnits().size()+"");
        rangedArmy2.setText(army2.getRangedUnits().size()+"");
        cavalryArmy2.setText(army2.getCavalryUnits().size()+"");
        commanderArmy2.setText(army2.getCommanderUnits().size()+"");
    }

    /**
     * Method that open a file form the local system, defines file's path,
     * run method readAFileArmy from the army1 object and calls help method updateView.
     */
    @FXML
    private void loadFromAFileArmyOne(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a army file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                    new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile !=null){
                //This if sentence avoids a possible bug tha happens when the both armies have the same name
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army2.getName())){
                    army1 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army1.setName(army2.getName()+"-2");
                }else army1 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                Army backUp = singletonArmies.getArmy(1);
                this.updateBothArmies(army1,backUp);
                this.updateView();}
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by reading the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    /**
     * Method that open a file form the local system, defines file's path,
     * run method readAFileArmy from the army2 object and calls help method updateView.
     */
    @FXML
    private void loadFromAFileArmyTwo(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a army file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                    new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if(selectedFile!=null){
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army1.getName())){
                    army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army2.setName(army2.getName()+"-2");
                }else army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                Army backUp = singletonArmies.getArmy(0);
                this.updateBothArmies(backUp,army2);
                this.updateView();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by reading the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }
    private void updateBothArmies(Army army1,Army army2){
        singletonArmies.setEmptySingletonArmy();
        singletonArmies.SetEmptyArmyBackUp();
        singletonArmies.putArmy(new Army(army1));
        singletonArmies.putArmy(new Army(army2));
        singletonArmies.putArmyInBackUp(new Army(army1));
        singletonArmies.putArmyInBackUp(new Army(army2));
    }

    private void loadFromAFileArmy(){

    }

    @FXML
    private void saveArmyOneAsAFile(){
        try {
            this.saveArmyAsAFile(0);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by saving the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void saveArmyTwoAsAFile(){
        try {
            this.saveArmyAsAFile(1);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by saving the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    private void saveArmyAsAFile(int index) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save army as a file.");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if(file !=null){
            FileArmyHandler.writeAFile(new Army(singletonArmies.getArmy(index)),file.getParent(),file.getName());
        }
    }

    @FXML
    private void displayAllUnitsFromArmyOne(){
        try {
            SingletonArmies.getSingletonArmies().setArmyNumber(0);
            this.displayAllUnits();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by saving the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void displayAllUnitsFromArmyTwo(){
        try {
            SingletonArmies.getSingletonArmies().setArmyNumber(1);
            this.displayAllUnits();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by saving the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    private void displayAllUnits() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/displayArmy.fxml")));
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setTitle("Display Units");
        stage.setScene(new Scene(root));
        stage.initOwner((Stage) armyOneName.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    /**
     * Method for a button in the menu bar that close the program.
     */
    @FXML
    private void closeTheProgramButton(){
        Platform.exit();
    }

}
