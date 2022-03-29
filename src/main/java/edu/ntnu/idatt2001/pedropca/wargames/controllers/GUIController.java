package edu.ntnu.idatt2001.pedropca.wargames.controllers;

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
import org.w3c.dom.Text;

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
    protected void simulateBattle() throws IOException {
        for(int i =0;i<50;i++){
            army1.add(new CavalryUnit("Raider",100));
            army1.add(new RangedUnit("Ranged",100));
            army1.add(new InfantryUnit("Footman",100));
        }
        for(int i =0;i<50;i++){
            army2.add(new CavalryUnit("Raider",100));
            army2.add(new RangedUnit("Ranged",100));
            army2.add(new InfantryUnit("Footman",100));
        }
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
            }
            else{
                army2 = lastArmy;
                army1 = new Army(army1.getName());
            }
        }
        else {
            army1 = new Army(army1.getName());
            army2 = new Army(army2.getName());
        }
    }

    private void updateView(){
        totalArmy1.setText(army1.getAllUnits().size()+"");
        infantryArmy1.setText(army1.getInfantryUnits().size()+"");
        rangedArmy1.setText(army1.getRangedUnits().size()+"");
        cavalryArmy1.setText(army1.getCavalryUnits().size()+"");
        commanderArmy1.setText(army1.getCommanderUnits().size()+"");
        totalArmy2.setText(army2.getAllUnits().size()+"");
        infantryArmy2.setText(army2.getInfantryUnits().size()+"");
        rangedArmy2.setText(army2.getRangedUnits().size()+"");
        cavalryArmy2.setText(army2.getCavalryUnits().size()+"");
        commanderArmy2.setText(army2.getCommanderUnits().size()+"");
    }
    //TODO: program button reset armies.
}
