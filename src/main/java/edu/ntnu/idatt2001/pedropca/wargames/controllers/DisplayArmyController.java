package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * DisplayArmyController class that is a part of the Controller hierarchy, extends
 * the class Controller and controls over the FXML file DisplayArmy.fxml
 * by defining all relevant JavaFx object in the FXML and all methods
 * that user can call them by interacting with the JavaFX objects.
 * This class has two fields, one SingletonArmies instance and one Army instance,
 * and several JavaFx fields.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class DisplayArmyController extends Controller implements Initializable{

    @FXML
    private Label armyNameDisplayUnits;

    @FXML
    private TableView<Unit> armyTableView;

    @FXML
    private Button closeButton;

    @FXML
    private TextField searchingField;

    @FXML
    private Menu Editing;

    final private SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    private Army army = singletonArmies.getArmy(singletonArmies.getArmyNumber());


    /**
     * Initialize method that is called after its root element is loaded.
     * Method that add all the needed columns to the TableView armyTableView and
     * add all the units of army to the table by calling the help method createTable and UpdateTable;
     *
     * @param url url - The location of the fxml file.
     * @param resourceBundle ResourceBundle - The resource used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Editing.setDisable(Controller.getActualPage()==Page.EDITING_ARMY);
        this.createTable();
        this.updateTable();
    }

    /**
     * Help method that adds the columns that represents the fields of the class unit
     * into the TableView armyTableView.
     */
    private void createTable(){
        if(armyTableView.getColumns().size()<4) {

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

            armyTableView.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9);
        }
    }

    /**
     * Help method that change the text in the label armyNameDisplayUnits to
     * the current name of the army and update the units into the TableView armyTableView
     * by calling the help method getAllUnitsFromArmy.
     */
    private void updateTable(){
        armyNameDisplayUnits.setText(army.getName());
        armyTableView.setItems(FXCollections.observableList(army.getAllUnits()));
        searchingField.clear();
        searchingField.setPromptText("Search field");
    }


    /**
     * Method that save the current army by calling the help method saveArmyAsAFile.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void saveArmyAsFileFromDisplayArmyController(){
        try {
            this.saveArmyAsAFile(singletonArmies.getArmyNumber());
        } catch (IOException e) {
            this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                    , e.getMessage());
        }
    }

    /**
     * Method that open a file from the local system by calling the method openFileChooser, defines file's path,
     * runs method readAFileArmy from FileArmyHandler class and define army as the result of it,
     * and calls help checkName.
     * This method calls help method updateArmyOneInBothListInTheSingleton to update
     * the both list of armies in SingletonArmies class correctly.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void loadFromAFileFromDisplayArmyController(){
        try {
            File selectedFile = this.openFileChooser("Open a army file").showOpenDialog(null);
            if(selectedFile!=null){
                army = FileArmyHandler.readArmy(selectedFile.getAbsolutePath());
                this.checkNameAndUpdateSingleton(army);
                this.updateTable();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    /**
     * Method that defined army stored in the field army as a pre-defined army.
     * The user has the possibility to change the name of the pre-define army.
     * This method gets help from methods generateArmy, stringInputWindow, updateArmyInBothListInTheSingleton
     * and update view.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void generatedArmyDisplayArmyController(){
        try {
            String name = this.stringInputWindow(armyNameDisplayUnits.getScene().getWindow());
            if(!name.isEmpty()){
                army = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army);
                this.updateTable();
            }
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }

    /**
     * JavaFX's method that open a new window where is possible editing
     * the army contained in the field army by calling the help method openEditArmyWindow.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void displayEditingDisplayArmyController(){
        try {
            this.openANewScene("/Views/EditingArmy.fxml","Editing army",armyNameDisplayUnits);
            army = new Army(singletonArmies.getArmy(singletonArmies.getArmyNumber()));
            this.updateTable();
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    /**
     * Method for close the current stage.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * JavaFx's method that searches units and displays them in the table view of units
     * by reading the input written in the text field searchingField.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void searchTable(){
        // I am very proud of this method/Line :D
        armyTableView.setItems(FXCollections.observableList(army.getAllUnits().stream()
                .filter(unit -> unit.getName().contains(searchingField.getText()))
                .collect(Collectors.toList())));
    }
}
