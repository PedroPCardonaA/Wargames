package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import com.sun.glass.ui.CommonDialogs;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;
import org.w3c.dom.events.Event;

import java.io.File;
import java.io.IOException;

public class GUIController {
    Army army1 = new Army("Army#1");
    Army army2 = new Army("Army#2");
    Army armyOneBackUp = army1;
    Army armyTwoBackUP = army2;

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
    protected void simulateBattle(){
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


    private void updateArmies(Army lastArmy){
        if(lastArmy!=null){
            if(lastArmy.getName().equals(army1.getName())){
                army1 = lastArmy;
                army2 = new Army(army2.getName());
            } else{
                army2 = lastArmy;
                army1 = new Army(army1.getName());
            }
        } else {
            army1 = new Army(army1.getName());
            army2 = new Army(army2.getName());
        }
    }

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

    @FXML
    private void resetArmies(){
        armyOneBackUp.removeAllUnits();
        armyTwoBackUP.removeAllUnits();
        for(int i =0;i<50;i++){
            armyOneBackUp.add(new CavalryUnit("Raider",100));
            armyOneBackUp.add(new RangedUnit("Ranged",100));
            armyOneBackUp.add(new InfantryUnit("Footman",100));
            armyTwoBackUP.add(new CavalryUnit("Raider",100));
            armyTwoBackUP.add(new RangedUnit("Ranged",100));
            armyTwoBackUP.add(new InfantryUnit("Footman",100));
        }
        army1=armyOneBackUp;
        army2=armyTwoBackUP;
        this.updateView();
    }

    @FXML
    private void readFromAFileArmyOne(){
        try {
            Stage newStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a army file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File selectedFile = fileChooser.showOpenDialog(newStage);
            army1.readAFileArmy(selectedFile.getAbsolutePath());
            armyOneBackUp = army1;
            this.updateView();
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
    private void readFromAFileArmyTwo(){
        try {
            Stage newStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a army file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File selectedFile = fileChooser.showOpenDialog(newStage);
            army2.readAFileArmy(selectedFile.getAbsolutePath());
            armyTwoBackUP =army2;
            this.updateView();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error by loading the file!");
            alert.setHeaderText("It was a error by reading the file.");
            alert.setContentText(e.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
        }
    }
}
