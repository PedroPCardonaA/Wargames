package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditingArmyController extends Controller implements Initializable {

    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    Army army = singletonArmies.getArmy(singletonArmies.getArmyNumber());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unitType.getItems().addAll("Infantry","Cavalry","Ranged","Magician","Commander");
        nameOfTheArmy.setPromptText(army.getName());
        this.
    }
}
