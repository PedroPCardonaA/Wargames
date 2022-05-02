package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.Exceptions.EmptyInputException;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import edu.ntnu.idatt2001.pedropca.wargames.util.UnitFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
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
        unitListView.getItems().addAll(this.getUnitsName(""));
        this.resetTextFields(searchField,"Search field");
        this.resetTextFields(nameOfTheArmy,army.getName());
        this.resetTextFields(name,"Name");
        this.resetTextFields(health,"100");
        this.resetTextFields(attack,"25");
        this.resetTextFields(armor,"12");
        this.resetTextFields(attackSpeed,"2");
        this.resetTextFields(accuracy,"70");
        this.resetTextFields(criticalDamage,"145");
        this.resetTextFields(criticalRate,"25");
        this.resetTextFields(numberToAdd,"25");
        this.resetTextFields(numberToDelete,"15");
        unitType.getSelectionModel().clearSelection();
        unitType.setPromptText("Unit type");

    }

    private void resetTextFields(TextField field,String newText){
        field.clear();
        field.setPromptText(newText);
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
                this.updateArmyInBothListInTheSingleton(army,singletonArmies.getArmyNumber());
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

    private int checkHealth() throws EmptyInputException {
        try {
            if(health.getText().isEmpty()) return 100;
            else return Integer.parseInt(health.getText());
        }catch (Exception e){
            throw new EmptyInputException
                    ("The health of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkAttack() throws EmptyInputException{
        try {
            if(attack.getText().isEmpty()) return 25;
            else return Integer.parseInt(attack.getText());
        }catch (Exception e){
            throw new EmptyInputException
                    ("The attack of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkArmor() throws EmptyInputException{
        try {
            if(armor.getText().isEmpty()) return 12;
            else return Integer.parseInt(armor.getText());
        }catch (Exception e){
            throw new EmptyInputException
                    ("The armor of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkAttackSpeed() throws EmptyInputException{
        try {
            if(attackSpeed.getText().isEmpty()) return 2;
            else return Integer.parseInt(attackSpeed.getText());
        }catch (Exception e){
            throw new EmptyInputException
                    ("The attack speed of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkAccuracy() throws EmptyInputException{
        try {
            if(accuracy.getText().isEmpty()) return 70;
            else return Integer.parseInt(accuracy.getText());
        }catch (Exception e){
            throw new EmptyInputException
                    ("The accuracy of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkCriticalRate() throws EmptyInputException{
        try {
            if(criticalRate.getText().isEmpty()) return 25;
            else return Integer.parseInt(criticalRate.getText());
        }catch (Exception e){
            throw new EmptyInputException
                    ("The critical of the unit rate must be a integer number. Define it as integer number");
        }
    }
    private int checkCriticalDamage() throws EmptyInputException{
        try {
            if(criticalDamage.getText().isEmpty()) return 145;
            else return Integer.parseInt(criticalDamage.getText());
        }catch (Exception e){
            throw new EmptyInputException("The critical damage of the unit must be a integer number. Define it as integer number");
        }
    }
    private int checkNumberOfUnits() throws EmptyInputException{
        try {
            if(numberToAdd.getText().isEmpty()) return 25;
            else return Integer.parseInt(numberToAdd.getText());
        }catch (Exception e){
            throw new EmptyInputException("The number of units to add must be a integer number. Define it as integer number");
        }
    }

    @FXML
    private void editName(){
        if(nameOfTheArmy.getText().isEmpty()) this.showAlert("Editing name of the army", "You have not changed the name of the army yet!","");
        else{
            army.setName(nameOfTheArmy.getText());
            this.checkNameAndUpdateSingleton(army);
            this.resetTextFields(nameOfTheArmy,army.getName());
            this.showAlert("Editing name of the army","The name of the army has been correctly edited!", "The name of the army has been changed to " + army.getName()+"!");
        }
    }

    @FXML
    private void deleteUnit(){
        try {
            if(!unitListView.getFocusModel().getFocusedItem().isEmpty()){
                int sum = 0;
                String name = this.getRealNameOfTheUnit();
                System.out.println(name);
                for(int i = 0; i<this.checkNumberToDelete();i++){
                    if(army.getAllUnits().contains(army.returnAUnitByName(name))) sum++;
                    army.removeUnit(army.returnAUnitByName(name));
                }
                this.updateArmyInBothListInTheSingleton(army,singletonArmies.getArmyNumber());
                this.updateView();
                this.showAlert("Success by deleting units","You have delete the selected unit correctly","You have deleted " + sum + " of unit: " + name + " correctly!!" + "\nThey are " + this.getNumberOfTheSameUnitIntTheArmy(name) + " of them in the army now.");

            }
            else this.showAlert("Unit was not selected", "You must select a unit to delete them","");
        }catch (Exception e){
            this.showError("Error by deleting units", "It was an error by deleting the units",e.getMessage());
        }
    }

    private String getRealNameOfTheUnit(){
        String selectedUnitName = unitListView.getSelectionModel().getSelectedItem();
        List<Integer> indexes = new ArrayList<>();
        for (int i=0;i<selectedUnitName.length()-1;i++){
            if(selectedUnitName.charAt(i)== ' ') indexes.add(i);
        }
        return selectedUnitName.substring(0,indexes.get(indexes.size()-1));
    }

    private int checkNumberToDelete() throws EmptyInputException{
        try {
            if(numberToDelete.getText().isEmpty()) return 15;
            else return Integer.parseInt(numberToDelete.getText());
        }catch (Exception e){
            throw new EmptyInputException("The number of units to delete must be a integer number. Define it as integer number");
        }
    }


    @FXML
    private void searchInTableView(){
        unitListView.getItems().clear();
        unitListView.getItems().addAll(this.getUnitsName(searchField.getText()));
    }

    private List<String> getUnitsName(String input){
        List<String> names= new ArrayList<>();
        army.getAllUnits().forEach(unit->{
            if(!names.contains(unit.getName() + " X" + this.getNumberOfTheSameUnitIntTheArmy(unit.getName()))
                    && unit.getName().contains(input)) {
                names.add(unit.getName() +  " X" + this.getNumberOfTheSameUnitIntTheArmy(unit.getName()));
            }
        });
        return names;
    }

    private int getNumberOfTheSameUnitIntTheArmy(String name){
        return  Collections.frequency(army.getAllUnits()
                .stream().map(Unit::getName).collect(Collectors.toList()), name);
    }


}
