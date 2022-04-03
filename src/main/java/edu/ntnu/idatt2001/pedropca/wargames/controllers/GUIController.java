package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import com.sun.glass.ui.CommonDialogs;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.UnitFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.collections.Factory;

import java.io.File;
import java.io.IOException;

public class GUIController {
    Army army1 = new Army("Army#1");
    Army army2 = new Army("Army#2");
    Army armyOneBackUp = new Army("Army#1");
    Army armyTwoBackUP = new Army("Army#2");

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
    private Label armyNameDisplayUnits;

    @FXML
    private TableView<Unit> tableView;

    @FXML
    protected void simulateBattle(){
        Battle battle = new Battle(army1,army2,"FOREST");
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

    private void updateArmies(Army lastArmy){
        if(lastArmy!=null){
            if(army1.equals(lastArmy)){
                army1 = new Army(lastArmy);
                army2 = new Army(army2.getName());
            } else{
                army2 = new Army(lastArmy);
                army1 = new Army(army1.getName());
            }
        } else {
            army1 = new Army(army1.getName());
            army2 = new Army(army2.getName());
        }
    }

    /**
     * Method that "Resets" the main armies by defined them as copies of the buck up armies.
     * After the restarting of the armies method updates the GUI by calling help method updateView().
     */
    @FXML
    private void resetArmies(){
        army1 = new Army(armyOneBackUp);
        army2 = new Army(armyTwoBackUP);
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
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile !=null){
                //This if sentence avoids a possible bug tha happens when the both armies have the same name
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army2.getName())){
                    army1 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army1.setName(army2.getName()+"-2");
                }else army1 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                armyOneBackUp = new Army(army1);
                this.updateView();}
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if(selectedFile!=null){
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army1.getName())){
                    army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army2.setName(army2.getName()+"-2");
                }else army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                armyTwoBackUP =new Army(army2);
                System.out.println(armyTwoBackUP.getAllUnits().size());
                this.updateView();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by reading the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void saveArmyOneAsAFile(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save army as a file.");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File file = fileChooser.showSaveDialog(null);
            if(file !=null){
                FileArmyHandler.WriteAFile(army1,file.getParent(),file.getName());
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save army as a file.");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File file = fileChooser.showSaveDialog(null);
            if(file !=null){
                FileArmyHandler.WriteAFile(army2,file.getParent(),file.getName());
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by saving the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void displayWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/displayArmy.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("It was a error by saving the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void displayAllUnitsFromArmyOne() throws IOException {
        armyNameDisplayUnits.setText(army1.getName());
        this.createTable();
        UnitFactory unitFactory = new UnitFactory();
        army1.getAllUnits().forEach(unit ->{
            String[] names= unit.getClass().toString().split("\\.");
            tableView.getItems().add(unitFactory.createUnit(names[names.length-1],unit.getName(),unit.getHealth(),
                    unit.getAttack(),unit.getArmor(),unit.getAttackSpeedPerSecond(),
                    unit.getHitRate(),unit.getCriticRate(),unit.getCriticDamage()));});
        this.displayUnitsStage();
    }

    private void createTable(){
        TableColumn<Unit,String> column1 = new TableColumn<>("Unit type");
        column1.setCellValueFactory(new PropertyValueFactory<>("Unit type"));

        TableColumn<Unit,String> column2 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Unit,String> column3 = new TableColumn<>("Attack");
        column1.setCellValueFactory(new PropertyValueFactory<>("Attack"));

        TableColumn<Unit,String> column4 = new TableColumn<>("Health");
        column1.setCellValueFactory(new PropertyValueFactory<>("Health"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
    }

    private void displayUnitsStage() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("All units from the army");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/displayArmy.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Method for a button in the menu bar that close the program.
     */
    @FXML
    private void closeTheProgramButton(){
        Platform.exit();
    }


}
