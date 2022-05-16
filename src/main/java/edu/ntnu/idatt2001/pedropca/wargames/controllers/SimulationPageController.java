package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.HealerUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationPageController extends Controller implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea textArea;
    private final Army army1 = SingletonArmies.getSingletonArmies().getArmy(0);
    private final Army army2 = SingletonArmies.getSingletonArmies().getArmy(1);
    private  String text = "Battle between army: " + army1.getName() + " and army: " + army2.getName()+ " starts.";

    @Override
    protected void updateView() {
        textArea.setText(text);
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateView();
        Thread thread = new Thread(()->{
            Runnable updater = ()->{
                Unit unitFromArmy1 = army1.getRandom();
                Unit unitFromArmy2 = army2.getRandom();
                this.setText(unitFromArmy1.getName() +" from the army" + army1.getName() +"is fighting with " + unitFromArmy2.getName() + " from the army" + army2.getName());
                this.updateView();
                this.checkHealer(unitFromArmy1);
                this.checkHealer(unitFromArmy2);
                this.checkMagician(unitFromArmy1);
                this.checkMagician(unitFromArmy2);
                this.attack(unitFromArmy1,unitFromArmy2,this.checkAttackType(unitFromArmy1,unitFromArmy2));
                this.updateView();
                this.updateArmiesInSingleton(army1,army2);
            };
            while (true){
                try {
                    Thread.sleep(2000);
                    if(!(army1.hasUnit()&& army2.hasUnit())){
                        if(army1.hasUnit()) this.setText("Army: " +army1.getName() + " was the winner!");
                        if(army2.hasUnit()) this.setText("Army: " +army2.getName() + " was the winner!");
                        this.updateView();
                        this.updateArmiesInSingleton(army1,army2);
                        break;
                    }
                } catch (InterruptedException e) {
                    this.showError("Error by simulation","Error by slow simulation!",e.getMessage());
                }
                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void checkHealer(Unit unit){
        if(army1.getAllUnits().contains(unit)){
            if(unit instanceof HealerUnit){
                if ( ((HealerUnit) unit).getMana()>29)  ((HealerUnit) unit).magicAttack(army1.getAllUnits());
                this.setText(unit.getName() +  "from the army: " + army1.getName() +" casts a healing spell on his allies.");
            }
        }else {
            if(unit instanceof HealerUnit){
                if ( ((HealerUnit) unit).getMana()>29)  ((HealerUnit) unit).magicAttack(army2.getAllUnits());
                this.setText(unit.getName() +  "from the army: " + army2.getName() +" casts a healing spell on his allies.");
            }
        }
        this.updateView();
    }

    private void checkMagician(Unit unit){
        if(army1.getAllUnits().contains(unit)){
            if(unit instanceof MagicianUnit){
                if ( ((MagicianUnit) unit).getMana()>49)  ((MagicianUnit) unit).magicAttack(army2.getAllUnits());
                this.setText(unit.getName() +  "from the army: " + army1.getName() +" casts a fire spell on his enemies.");
            }
        }else {
            if(unit instanceof MagicianUnit){
                if ( ((MagicianUnit) unit).getMana()>49)  ((MagicianUnit) unit).magicAttack(army1.getAllUnits());
                this.setText(unit.getName() +  "from the army: " + army1.getName() +" casts a fire spell on his enemies.");
            }
        }
        this.updateView();
    }

    private boolean checkAttackType(Unit unit1, Unit unit2){
        return unit1.getAttackType().equals(unit2.getAttackType());
    }

    private void attack(Unit unit1, Unit unit2,boolean sameAttackType){
        if(sameAttackType) this.sameAttackBattle(unit1,unit2);
        else this.differentAttackBattle(unit1,unit2);
    }

    private void sameAttackBattle(Unit unit1, Unit unit2) {
        this.setText(unit1.getName()+ " and "+ unit2.getName() + " are fighting brutally each other!");
        this.updateView();
        checkWinnerOfTheFight(unit1, unit2);
    }

    private void differentAttackBattle(Unit unit1, Unit unit2){
        if(unit1.getAttackType().equals("Ranged")){
            this.setText(unit1.getName() + " From the army " + army1.getName() + " is taking advantage of his ranged to attack "+ unit2.getName() + " twice before a close combat.");
            for(int i=0; i<1;i++){
                unit1.attack(unit2);
            }
        }else {
            this.setText(unit2.getName() + " From the army " + army2.getName() + " is taking advantage of his ranged to attack "+ unit1.getName() + " twice before a close combat.");
            for(int i=0; i<1;i++){
                unit2.attack(unit1);
            }
        }
        checkWinnerOfTheFight(unit1, unit2);

    }

    private void checkWinnerOfTheFight(Unit unit1, Unit unit2) {
        while(unit1.getHealth()>0 &&unit2.getHealth()>0){
            unit1.attack(unit2);
            unit2.attack(unit1);
        }
        if(unit1.equals(this.checkWinner(unit1,unit2))) this.setText(unit2.getName()+ " from the army: " + army2.getName() + " has died as a hero!");
        if(unit2.equals(this.checkWinner(unit1,unit2))) this.setText(unit1.getName()+ " from the army: " + army1.getName() + " has died as a hero!");
    }

    private Unit checkWinner(Unit unit1, Unit unit2){
        if(unit1.getHealth()!=0){
            army2.removeUnit(unit2);
            return unit1;
        }else {
            army1.removeUnit(unit1);
            return unit2;
        }
    }

    @FXML
    private void onCancelButton(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void setText(String textToAdd){
        text += textToAdd +"\n \n";
    }


}
