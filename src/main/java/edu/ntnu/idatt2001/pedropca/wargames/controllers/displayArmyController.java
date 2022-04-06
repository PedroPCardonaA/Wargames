package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import com.sun.glass.ui.CommonDialogs;
import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
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

    Army army1 = new Army("Army#1");
    Army army2 = new Army("Army#2");

    private void createTable(){
        if(tableView.getColumns().size()<4) {
            TableColumn<Unit, String> column1 = new TableColumn<>("Unit type");
            column1.setCellValueFactory(new PropertyValueFactory<>("Unit type"));

            TableColumn<Unit, String> column2 = new TableColumn<>("Name");
            column1.setCellValueFactory(new PropertyValueFactory<>("Name"));

            TableColumn<Unit, String> column3 = new TableColumn<>("Attack");
            column1.setCellValueFactory(new PropertyValueFactory<>("Attack"));

            TableColumn<Unit, String> column4 = new TableColumn<>("Health");
            column1.setCellValueFactory(new PropertyValueFactory<>("Health"));

            tableView.getColumns().add(column1);
            tableView.getColumns().add(column2);
            tableView.getColumns().add(column3);
            tableView.getColumns().add(column4);
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
