package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.HealerUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicUnit;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * SimulationController class that is a part of the Controller hierarchy, extends
 * the class Controller and controls over the FXML file DisplayArmy.fxml
 * by defining all relevant JavaFx object in the FXML and all methods
 * that user can call them by interacting with the JavaFX objects.
 * This class has two fields, two armies that will battle each other,
 * and several JavaFx fields.
 * The goal of this class is to simulate a battle between two armies
 * and show the progress of the battle to the user
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class SimulationController extends Controller implements Initializable {
    @FXML
    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    private StackedBarChart<String,Number> barChart = new StackedBarChart<String,Number>(xAxis,yAxis);

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
    private TextField magicianArmy1;

    @FXML
    private TextField magicianArmy2;

    @FXML
    private TextField healerArmy1;

    @FXML
    private TextField healerArmy2;

    @FXML
    private Label armyOneName;

    @FXML
    private Label armyTwoName;
    @FXML
    private TextField unitOne;
    @FXML
    private TextField unitTwo;
    @FXML
    private TextField magicSpellOne;
    @FXML
    private TextField magicSpellTwo;
    @FXML
    private TextField graveyard;
    @FXML
    private final XYChart.Series<String,Number> infantrySeries= new XYChart.Series<>();
    @FXML
    private final XYChart.Series<String,Number> rangedSeries= new XYChart.Series<>();
    @FXML
    private final XYChart.Series<String,Number> cavalrySeries= new XYChart.Series<>();
    @FXML
    private final XYChart.Series<String,Number> magicianSeries= new XYChart.Series<>();
    @FXML
    private final XYChart.Series<String,Number> healerSeries= new XYChart.Series<>();
    @FXML
    private final XYChart.Series<String,Number> commanderSeries= new XYChart.Series<>();

    private Army army1= SingletonArmies.getSingletonArmies().getArmy(0);
    private Army army2= SingletonArmies.getSingletonArmies().getArmy(1);

    /**
     * Initialize method that is called after its root element is loaded.
     * Method start running the simulation of the battle by define correctly the parameter of the bar chart with
     * the number of units alive and the text field. The view is updated every single iteration of the simulation.
     * This method gets help of the help method updateView and the class Thread that helps to
     * define how the simulations loop will work.
     *
     * @param url url - The location of the fxml file.
     * @param resourceBundle ResourceBundle - The resource used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barChart.getXAxis().setLabel("Armies");
        barChart.getYAxis().setLabel("# Units");
        barChart.getData().addAll(infantrySeries,rangedSeries,cavalrySeries,magicianSeries,healerSeries,commanderSeries);
        this.updateView();

        yAxis.setAutoRanging(false);
        xAxis.setCategories(FXCollections.observableList(Arrays.asList(army1.getName(),army2.getName())));
        infantrySeries.setName("Infantry");
        cavalrySeries.setName("Cavalry");
        magicianSeries.setName("Magician");
        healerSeries.setName("Healer");
        commanderSeries.setName("Commander");
        rangedSeries.setName("Ranged");

        Battle battle = new Battle(army1,army2);
        Thread thread = new Thread(()->{
            Runnable updater = ()->{

            };
            while (true){
                try {
                    List<Unit> units = battle.singularBattleForSlowAnimation();
                    army1 =battle.getArmy1();
                    army2 =battle.getArmy2();
                    this.updateArmiesInSingleton(army1,army2);
                    for (int i = 0; i<2;i++){
                        Unit unit = units.get(i);
                        if(i==0){
                            unitOne.setText(unit.getName());
                            if(unit instanceof MagicUnit){
                                if(unit instanceof HealerUnit) magicSpellOne.setText(unit.getName() + " cast a healing spell on his allies!");
                                if(unit instanceof MagicianUnit) magicSpellOne.setText(unit.getName() + " cast a fire spell on his enemies!");
                            } else magicSpellOne.setText("None magic spell!");
                        }else {
                            unitTwo.setText(unit.getName());
                            if(unit instanceof  MagicUnit){
                                if(unit instanceof HealerUnit) magicSpellTwo.setText(unit.getName() + " cast a healing spell on his allies!");
                                if(unit instanceof MagicianUnit) magicSpellTwo.setText(unit.getName() + " cast a fire spell on his enemies!");
                            } else magicSpellTwo.setText("None magic spell!");
                        }
                    }
                    if(army1.getAllUnits().contains(units.get(0))) graveyard.setText(units.get(1).getName() + " from the army: "+ army2.getName() + " has died as a hero!");
                    else graveyard.setText(units.get(0).getName() + " from the army: "+ army1.getName() + " has died as a hero!");
                    Thread.sleep(2500);
                    if(!(army1.hasUnit() && army2.hasUnit()) || !armyOneName.getScene().getWindow().isShowing()){
                        this.updateView();
                        break;
                    }
                } catch (InterruptedException e) {
                    this.showError("Error by simulation","Error by medium simulation!",e.getMessage());
                }
                //Update information in the barChart was more complicated that expected
                //and the result was not smooth as expected but good enough I think :D.
                Platform.runLater(this::updateView);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Help method that update the data of the text fields that contains the number of units and
     * the information of the bar chart by calling the help method updateBarChart.
     */
    @Override
    protected void updateView() {
        armyOneName.setText(army1.getName());
        totalArmy1.setText(army1.getAllUnits().size()+"");
        infantryArmy1.setText(army1.getInfantryUnits().size()+"");
        rangedArmy1.setText(army1.getRangedUnits().size()+"");
        cavalryArmy1.setText(army1.getCavalryUnits().size()+"");
        magicianArmy1.setText(army1.getMagicianUnits().size()+"");
        healerArmy1.setText(army1.getHealerUnits().size() +"");
        commanderArmy1.setText(army1.getCommanderUnits().size()+"");
        armyTwoName.setText(army2.getName());
        totalArmy2.setText(army2.getAllUnits().size()+"");
        infantryArmy2.setText(army2.getInfantryUnits().size()+"");
        rangedArmy2.setText(army2.getRangedUnits().size()+"");
        cavalryArmy2.setText(army2.getCavalryUnits().size()+"");
        magicianArmy2.setText(army2.getMagicianUnits().size()+"");
        healerArmy2.setText(army2.getHealerUnits().size() +"");
        commanderArmy2.setText(army2.getCommanderUnits().size()+"");
        this.updateBarChart();

    }

    /**
     * Help method that update the bar chart that contain the information of the number
     * of units alive from the both armies by clearing the all data and adding the new data.
     */
    private void updateBarChart(){
        infantrySeries.getData().removeAll(Collections.singleton(infantrySeries.getData().setAll()));
        rangedSeries.getData().removeAll(Collections.singleton(rangedSeries.getData().setAll()));
        cavalrySeries.getData().removeAll(Collections.singleton(cavalrySeries.getData().setAll()));
        commanderSeries.getData().removeAll(Collections.singleton(commanderSeries.getData().setAll()));
        healerSeries.getData().removeAll(Collections.singleton(healerSeries.getData().setAll()));
        magicianSeries.getData().removeAll(Collections.singleton(magicianSeries.getData().setAll()));


        infantrySeries.getData().add(new XYChart.Data<>(army1.getName(),army1.getInfantryUnits().size()));
        infantrySeries.getData().add(new XYChart.Data<>(army2.getName(),army2.getInfantryUnits().size()));


        rangedSeries.getData().add(new XYChart.Data<>(army1.getName(),army1.getRangedUnits().size()));
        rangedSeries.getData().add(new XYChart.Data<>(army2.getName(),army2.getRangedUnits().size()));

        cavalrySeries.getData().add(new XYChart.Data<>(army1.getName(),army1.getCavalryUnits().size()));
        cavalrySeries.getData().add(new XYChart.Data<>(army2.getName(),army2.getCavalryUnits().size()));

        magicianSeries.getData().add(new XYChart.Data<>(army1.getName(),army1.getMagicianUnits().size()));
        magicianSeries.getData().add(new XYChart.Data<>(army2.getName(),army2.getMagicianUnits().size()));


        healerSeries.getData().add(new XYChart.Data<>(army1.getName(),army1.getHealerUnits().size()));
        healerSeries.getData().add(new XYChart.Data<>(army2.getName(),army2.getHealerUnits().size()));

        commanderSeries.getData().add(new XYChart.Data<>(army1.getName(),army1.getCommanderUnits().size()));
        commanderSeries.getData().add(new XYChart.Data<>(army2.getName(),army2.getCommanderUnits().size()));
    }

}
