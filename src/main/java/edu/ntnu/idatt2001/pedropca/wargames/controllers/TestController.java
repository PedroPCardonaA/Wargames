package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.magicUnits.MagicianUnit;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
//TODO: JavaDOC
//TODO: CODE SIMULATION.
public class TestController extends Controller implements Initializable {
    @FXML
    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    final NumberAxis yAxis = new NumberAxis(100,110,10);
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
    private final XYChart.Series<String,Number> infantrySeries= new XYChart.Series<String,Number>();
    @FXML
    private final XYChart.Series<String,Number> rangedSeries= new XYChart.Series<String,Number>();
    @FXML
    private final XYChart.Series<String,Number> cavalrySeries= new XYChart.Series<String,Number>();
    @FXML
    private final XYChart.Series<String,Number> magicianSeries= new XYChart.Series<String,Number>();
    @FXML
    private final XYChart.Series<String,Number> healerSeries= new XYChart.Series<String,Number>();
    @FXML
    private final XYChart.Series<String,Number> commanderSeries= new XYChart.Series<String,Number>();

    private Army army1= SingletonArmies.getSingletonArmies().getArmy(0);
    private Army army2= SingletonArmies.getSingletonArmies().getArmy(1);

    private final int startNumbersOfUnitsFromArmyOne = army1.getAllUnits().size();
    private final int startNumbersOfUnitsFromArmyTwo = army2.getAllUnits().size();





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barChart.getXAxis().setLabel("Armies");
        barChart.getYAxis().setLabel("Unit alive %");
        barChart.getData().addAll(infantrySeries,rangedSeries,cavalrySeries,magicianSeries,healerSeries,commanderSeries);
        this.updateView();

        yAxis.setAutoRanging(false);
        xAxis.setCategories(FXCollections.observableList(Arrays.asList(army1.getName(),army2.getName())));
        infantrySeries.setName("Infantry");
        cavalrySeries.setName("Cavalry");
        magicianSeries.setName("Magician");
        healerSeries.setName("Healer");
        commanderSeries.setName("Commander");

        Battle battle = new Battle(army1,army2);
        Thread thread = new Thread(()->{
            Runnable updater = ()->{

            };
            while (true){
                try {
                    battle.singularBattle();
                    army1 =battle.getArmy1();
                    army2 =battle.getArmy2();
                    this.updateArmyInBothListInTheSingleton(army1,0);
                    this.updateArmyInBothListInTheSingleton(army2,1);
                    Thread.sleep(2000);
                    if(!(army1.hasUnit() && army2.hasUnit())){
                        break;
                    }
                } catch (InterruptedException e) {
                    this.showError("Error by simulation","Error by medium simulation!",e.getMessage());
                }
                //Update information in the barChart was more complicated that expected
                // and the result was not smooth as expected but good enough I think :D.
                Platform.runLater(this::updateView);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

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

        infantrySeries.getData().removeAll(Collections.singleton(infantrySeries.getData().setAll()));
        rangedSeries.getData().removeAll(Collections.singleton(rangedSeries.getData().setAll()));
        cavalrySeries.getData().removeAll(Collections.singleton(cavalrySeries.getData().setAll()));
        commanderSeries.getData().removeAll(Collections.singleton(commanderSeries.getData().setAll()));
        healerSeries.getData().removeAll(Collections.singleton(healerSeries.getData().setAll()));
        magicianSeries.getData().removeAll(Collections.singleton(magicianSeries.getData().setAll()));


        infantrySeries.getData().add(new XYChart.Data<String,Number>(army1.getName(),getPercentOfAliveInfantryUnits(army1,startNumbersOfUnitsFromArmyOne)));
        infantrySeries.getData().add(new XYChart.Data<String,Number>(army2.getName(),getPercentOfAliveInfantryUnits(army2,startNumbersOfUnitsFromArmyTwo)));


        rangedSeries.getData().add(new XYChart.Data<String,Number>(army1.getName(),getPercentOfAliveRangedUnits(army1,startNumbersOfUnitsFromArmyOne)));
        rangedSeries.getData().add(new XYChart.Data<String,Number>(army2.getName(),getPercentOfAliveRangedUnits(army2,startNumbersOfUnitsFromArmyTwo)));

        cavalrySeries.getData().add(new XYChart.Data<String,Number>(army1.getName(),getPercentOfAliveCavalryUnits(army1,startNumbersOfUnitsFromArmyOne)));
        cavalrySeries.getData().add(new XYChart.Data<String,Number>(army2.getName(),getPercentOfAliveCavalryUnits(army2,startNumbersOfUnitsFromArmyTwo)));

        magicianSeries.getData().add(new XYChart.Data<String,Number>(army1.getName(),getPercentOfAliveMagicianUnits(army1,startNumbersOfUnitsFromArmyOne)));
        magicianSeries.getData().add(new XYChart.Data<String,Number>(army2.getName(),getPercentOfAliveMagicianUnits(army2,startNumbersOfUnitsFromArmyTwo)));


        healerSeries.getData().add(new XYChart.Data<String,Number>(army1.getName(),getPercentOfAliveHealerUnits(army1,startNumbersOfUnitsFromArmyOne)));
        healerSeries.getData().add(new XYChart.Data<String,Number>(army2.getName(),getPercentOfAliveHealerUnits(army2,startNumbersOfUnitsFromArmyTwo)));

        commanderSeries.getData().add(new XYChart.Data<String,Number>(army1.getName(),getPercentOfAliveCommanderUnits(army1,startNumbersOfUnitsFromArmyOne)));
        commanderSeries.getData().add(new XYChart.Data<String,Number>(army2.getName(),getPercentOfAliveCommanderUnits(army2,startNumbersOfUnitsFromArmyTwo)));
    }
    private double getPercentOfAliveInfantryUnits(@NotNull Army army, int startsUnits){
        return ((army.getInfantryUnits().size()* 100.0/startsUnits) );
    }
    private double getPercentOfAliveRangedUnits(@NotNull Army army, int startsUnits){
        return  (army.getRangedUnits().size()* 100.0/startsUnits);
    }
    private double getPercentOfAliveCavalryUnits(@NotNull Army army, int startsUnits){
        return ((army.getCavalryUnits().size()* 100.0/startsUnits));
    }
    private double getPercentOfAliveCommanderUnits(@NotNull Army army, int startsUnits){
        return ((army.getCommanderUnits().size()* 100.0/startsUnits));
    }
    private double getPercentOfAliveMagicianUnits(@NotNull Army army, int startsUnits){
        return ((army.getMagicianUnits().size()* 100.0/startsUnits));
    }
    private double getPercentOfAliveHealerUnits(@NotNull Army army, int startsUnits){
        return ((army.getHealerUnits().size()* 100.0/startsUnits) );
    }
}
