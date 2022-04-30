package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import edu.ntnu.idatt2001.pedropca.wargames.util.UnitFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditingArmyController extends Controller implements Initializable {

    //TODO: ADD JavaDoc
    //TODO: ADD a search field to list of the units;
    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    Army army = new Army(singletonArmies.getArmy(singletonArmies.getArmyNumber()));
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

    @FXML
    private ListView<String> unitListView;

    @FXML
    private Label title;

    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unitType.getItems().addAll("Infantry","Cavalry","Ranged","Magician","Commander");
        this.updateView();
    }

    private void updateView(){
        nameOfTheArmy.setPromptText(army.getName());
        this.updateListView();
    }

    private void updateListView() {
        unitListView.getItems().clear();
        ArrayList<String> names= new ArrayList<>();
        army.getAllUnits().forEach(unit -> {
            if(!names.contains(unit.getName())) names.add(unit.getName());
        });
        names.forEach(name->{
            int sum = (int) army.getAllUnits().stream().filter(unit -> unit.getName().equals(name)).count();
            //names.add(String.valueOf(sum));
        });
        unitListView.getItems().addAll(names);
        searchField.clear();
        searchField.setPromptText("Search field");
    }


    @FXML
    private void generatedArmyEditingArmyController(){
        try {
            String name = this.stringInputWindow(nameOfTheArmy.getScene().getWindow());
            if(!name.isEmpty()){
                army = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army);
                this.updateView();}
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }

    @FXML
    private void loadFromAFileEditingArmyController(){
        try {
            File selectedFile = this.openFileChooser("Open a army file").showOpenDialog(null);
            if(selectedFile!=null){
                army = FileArmyHandler.readArmy(selectedFile.getAbsolutePath());
                this.checkNameAndUpdateSingleton(army);
                this.updateView();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    @FXML
    private void saveArmyAsFileFromEditingArmyController(){
        try {
            this.saveArmyAsAFile(singletonArmies.getArmyNumber());
        } catch (IOException e) {
            this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                    , e.getMessage());
        }
    }

    @FXML
    private void displayAllUnitsEditingArmyController(){
        try {
            this.openANewScene("/Views/DisplayArmy.fxml","Editing army",title);
            army = new Army(singletonArmies.getArmy(singletonArmies.getArmyNumber()));
            this.updateView();
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    @FXML
    private void close(){
        Stage stage = (Stage) nameOfTheArmy.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addUnit(){
        if(unitType.getValue() == null){
            this.showAlert("Not unit type selected!", "Unit type has not been selected!", "To add a unit it is necessary to define a unit type");
        }  else{
            try {
                army.addAll(new UnitFactory().createAListOfUnits(unitType.getValue()+"Unit",this.checkName(),this.checkHealth(),this.checkAttack(),this.checkArmor(),this.checkAttackSpeed(),this.checkAccuracy(),this.checkCriticalRate(),this.checkCriticalDamage(),this.checkNumberOfUnits()));
                this.showAlert("Success by adding units","The units was successfully added to the army", "");
                this.checkNameAndUpdateSingleton(army);
                this.updateListView();
            }catch (Exception e){
                this.showError("Error by adding a Units","It was an error by adding the units", e.getMessage());
            }
        }
    }

    private String checkName(){
        if(name.getText().isEmpty()) return "Name";
        else return name.getText();
    }

    private int checkHealth() throws IllegalArgumentException{
        try {
            if(health.getText().isEmpty()) return 100;
            else return Integer.parseInt(health.getText());
        }catch (Exception e){
            throw new IllegalArgumentException
                    ("The health of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkAttack(){
        try {
            if(attack.getText().isEmpty()) return 25;
            else return Integer.parseInt(attack.getText());
        }catch (Exception e){
            throw new IllegalArgumentException
                    ("The attack of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkArmor(){
        try {
            if(armor.getText().isEmpty()) return 12;
            else return Integer.parseInt(armor.getText());
        }catch (Exception e){
            throw new IllegalArgumentException
                    ("The armor of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkAttackSpeed(){
        try {
            if(attackSpeed.getText().isEmpty()) return 2;
            else return Integer.parseInt(attackSpeed.getText());
        }catch (Exception e){
            throw new IllegalArgumentException
                    ("The attack speed of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkAccuracy(){
        try {
            if(accuracy.getText().isEmpty()) return 70;
            else return Integer.parseInt(accuracy.getText());
        }catch (Exception e){
            throw new IllegalArgumentException
                    ("The accuracy of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkCriticalRate(){
        try {
            if(criticalRate.getText().isEmpty()) return 25;
            else return Integer.parseInt(criticalRate.getText());
        }catch (Exception e){
            throw new IllegalArgumentException
                    ("The critical of the unit rate must be a integer number. Define it as integer number");
        }
    }
    private int checkCriticalDamage(){
        try {
            if(criticalDamage.getText().isEmpty()) return 145;
            else return Integer.parseInt(criticalDamage.getText());
        }catch (Exception e){
            throw new IllegalArgumentException("The critical damage of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkNumberOfUnits(){
        try {
            if(numberToAdd.getText().isEmpty()) return 25;
            else return Integer.parseInt(numberToAdd.getText());
        }catch (Exception e){
            throw new IllegalArgumentException("The number of units to add must be a integer number. Define it as integer number");
        }
    }

    @FXML
    private void editName(){
        if(nameOfTheArmy.getText().isEmpty()) this.showAlert("Editing name of the army", "You have not changed the name of the army yet!","");
        else{
            army.setName(nameOfTheArmy.getText());
            this.checkNameAndUpdateSingleton(army);
            this.resetNameField();
            this.showAlert("Editing name of the army","The name of the army has been correctly edited!", "The name of the army has been changed to " + army.getName()+"!");
        }
    }

    private void resetNameField(){
        nameOfTheArmy.clear();
        nameOfTheArmy.setPromptText(army.getName());
    }

    @FXML
    private void deleteUnit(){
        try {
            System.out.println(unitListView.getSelectionModel().getSelectedItems());
        }catch (Exception e){
            this.showError("Error by deleting units", "It was an error by adding the units",e.getMessage());
        }
    }

    private int checkNumberToDelete(){
        try {
            if(numberToAdd.getText().isEmpty()) return 15;
            else return Integer.parseInt(numberToDelete.getText());
        }catch (Exception e){
            throw new IllegalArgumentException("The number of units to delete must be a integer number. Define it as integer number");
        }
    }

    @FXML
    private void searchInTableView(){
        unitListView.getItems().clear();
        ArrayList<String> names= new ArrayList<>();
        army.getAllUnits().forEach(unit->{if(!names.contains(unit.getName()) && unit.getName().contains(searchField.getText()) )names.add(unit.getName());
        });
        unitListView.getItems().addAll(names);
    }
}
