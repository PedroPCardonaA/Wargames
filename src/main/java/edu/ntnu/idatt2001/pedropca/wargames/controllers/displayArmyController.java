package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import com.sun.glass.ui.CommonDialogs;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import edu.ntnu.idatt2001.pedropca.wargames.util.UnitFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class displayArmyController extends GUIController{

    @FXML
    private Label armyNameDisplayUnits;

    @FXML
    private TableView<Unit> tableView;

    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    Army army1 = singletonArmies.getArmy(0);
    Army army2 = singletonArmies.getArmy(1);

    private void createTable(){
        if(tableView.getColumns().size()<4) {

            TableColumn<Unit, String> column1 = new TableColumn<>("Name");
            column1.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<Unit, String> column2 = new TableColumn<>("Health");
            column2.setCellValueFactory(new PropertyValueFactory<>("health"));

            TableColumn<Unit, String> column3 = new TableColumn<>("Attack");
            column3.setCellValueFactory(new PropertyValueFactory<>("attack"));

            TableColumn<Unit, String> column4 = new TableColumn<>("Armor");
            column4.setCellValueFactory(new PropertyValueFactory<>("armor"));

            TableColumn<Unit, String> column5 = new TableColumn<>("Attack speed");
            column5.setCellValueFactory(new PropertyValueFactory<>("attackSpeedPerSecond"));

            TableColumn<Unit, String> column6 = new TableColumn<>("Attack type");
            column6.setCellValueFactory(new PropertyValueFactory<>("attackType"));

            TableColumn<Unit, String> column7 = new TableColumn<>("Hit rate");
            column7.setCellValueFactory(new PropertyValueFactory<>("hitRate"));

            TableColumn<Unit, String> column8 = new TableColumn<>("Critic rate");
            column8.setCellValueFactory(new PropertyValueFactory<>("criticRate"));

            TableColumn<Unit, String> column9 = new TableColumn<>("Critic damage");
            column9.setCellValueFactory(new PropertyValueFactory<>("criticDamage"));

            tableView.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9);
        }
    }

    @FXML
    private void setXd(){
        armyNameDisplayUnits.setText(army1.getName());
        tableView.setItems(this.getAllUnitsFromArmy1());
        this.createTable();
    }

    private ObservableList<Unit> getAllUnitsFromArmy1(){
        ObservableList<Unit> units = FXCollections.observableArrayList();
        units.addAll(army1.getAllUnits());
        return units;
    }
}
