package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import com.sun.glass.ui.CommonDialogs;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.CavalryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.InfantryUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.RangedUnit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private void resetArmies(){
        army1 = new Army(armyOneBackUp);
        army2 = new Army(armyTwoBackUP);
        this.updateView();
        //TODO: Delete this when bug with buckUpArmies gets fixed
        System.out.println(armyTwoBackUP.getAllUnits().size());
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
    private void readFromAFileArmyOne(){
        try {
            Stage newStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a army file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
            File selectedFile = fileChooser.showOpenDialog(newStage);
            army1.readAFileArmy(selectedFile.getAbsolutePath());
            armyOneBackUp = new Army(army1);
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
            armyTwoBackUP =new Army(army2);
            //TODO: Delete this when bug with buckUpArmies gets fixed
            System.out.println(armyTwoBackUP.getAllUnits().size());
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
    private void closeTheProgramButton(){
        Platform.exit();
    }

    //TODO: Fix BackUp Armies bug.
}
