package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumUnitType;
import edu.ntnu.idatt2001.pedropca.wargames.util.exceptions.NotIntegerException;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import edu.ntnu.idatt2001.pedropca.wargames.util.UnitFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
/**
 * DisplayArmyController class that is a part of the Controller hierarchy, extends
 * the class Controller and controls over the FXML file EditingArmy.fxml
 * by defining all relevant JavaFx object in the FXML and all methods
 * that user can call them by interacting with the JavaFX objects.
 * This class has two fields, one SingletonArmies instance and one Army instance,
 * and several JavaFx fields. Editing army has the goal to record the information from the user
 * to edit the current armies.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class EditingArmyController extends Controller implements Initializable {

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

    @FXML
    private MenuItem displayAllUnits;

    /**
     * Initialize method that is called after its root element is loaded.
     * Method adds all the different unit types into the comboBox unitType and
     * calls the help method updateView.
     *
     * @param url url - The location of the fxml file.
     * @param resourceBundle ResourceBundle - The resource used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(unitListView.getSelectionModel().getSelectedItem());
        displayAllUnits.setDisable(Controller.getActualPage() == Page.DISPLAY_ARMY);
        unitType.getItems().addAll("Infantry","Cavalry","Ranged","Magician","Healer","Commander");
        this.updateView();
    }

    /**
     * Method that update the WarGamesApp by resetting the content of the text field "nameOfTheArmy"
     * setting the current name of the army as prompt text, setting the rest of the text field
     * with standard values by using the help method resetTextFields
     * and updating the table view of the units by calling the help method getUnitsName.
     */
    @Override
    protected void updateView(){
        nameOfTheArmy.setPromptText(army.getName());
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
    }

    /**
     * Help method that gets a TextField in the signature, cleans all the content in the
     * text field and adding a new prompt text also defined in the signature.
     * @param field TextField - the text field to be edited
     * @param newText String - the new content of the field.
     */
    private void resetTextFields(TextField field,String newText){
        field.clear();
        field.setPromptText(newText);
    }

    /**
     * Method that defined army stored in the field army as a pre-defined army.
     * The user has the possibility to change the name of the pre-define army.
     * This method gets help from methods generateArmy, stringInputWindow, updateArmyInBothListInTheSingleton
     * and update view.
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
    @FXML
    private void generatedArmyEditingArmyController(){
        try {
            String name = this.stringInputWindow(nameOfTheArmy.getScene().getWindow());
            if(!name.isBlank()){
                army = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army);
                this.updateView();}
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }

    /**
     * Method that open a file from the local system by calling the method openFileChooser, defines file's path,
     * runs method readAFileArmy from FileArmyHandler class and define army as the result of it,
     * and calls help checkName.
     * This method calls help method updateArmyOneInBothListInTheSingleton to update
     * the both list of armies in SingletonArmies class correctly.
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
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

    /**
     * Method that save the current army by calling the help method saveArmyAsAFile.
     * It can be called by the javaFx object of the FXML file DisplayArmy.
     */
    @FXML
    private void saveArmyAsFileFromEditingArmyController(){
        try {
            this.saveArmyAsAFile(singletonArmies.getArmyNumber());
        } catch (IOException e) {
            this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                    , e.getMessage());
        }
    }

    /**
     * JavaFX's method that open a new window where the army contained in the filed army is displayed
     * by calling the help method openANewScene and updating the info by calling the help method updateView
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
    @FXML
    private void displayAllUnitsEditingArmyController(){
        try {
            this.openANewScene("/views/DisplayArmy.fxml","Editing army",title);
            army = new Army(singletonArmies.getArmy(singletonArmies.getArmyNumber()));
            this.updateView();
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }
    /**
     * Method for close the current stage.
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
    @FXML
    private void close(){
        Stage stage = (Stage) nameOfTheArmy.getScene().getWindow();
        stage.close();
    }

    /**
     * JavaFx's method that add a number of new units to the army contained in the field army
     * based on the information given by the user. The  information is read by the use
     * of the help method checkName and checkIfParsable. Method will return an alert
     * with the result of adding. The new army will be saved globally by calling the method
     * updateArmyInTheBothListInTheSingleton.
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
    @FXML
    private void addUnit() {
        if(unitType.getValue() == null){
            this.showAlert("Not unit type selected!", "Unit type has not been selected!", "To add a unit it is necessary to define a unit type");
        }  else{
            try {
                army.addAll(new UnitFactory().createAListOfUnits(Objects.requireNonNull(EnumUnitType.getUnitType(unitType.getValue()+"Unit")),
                        this.checkName(),this.checkIfParsable(health.getText(),100,"The health of the unit"),this.checkIfParsable(attack.getText(),25,"The attack of the unit"),this.checkIfParsable(armor.getText(),12,"The armor of the unit"),this.checkIfParsable(attackSpeed.getText(),2,"The attack speed of the unit"),
                        this.checkIfParsable(accuracy.getText(),70,"The accuracy of the unit"),this.checkIfParsable(criticalRate.getText(),25,"The critical rate of the unit"),this.checkIfParsable(criticalDamage.getText(),145,"The critical damage of the unit"),this.checkIfParsable(numberToAdd.getText(),25,"The number of units to add")));
                this.showAlert("Success by adding units","The units was successfully added to the army", "");
                this.updateArmyInBothListInTheSingleton(army,singletonArmies.getArmyNumber());
                this.updateView();
            }catch (Exception e){
                this.showError("Error by adding a Units","It was an error by adding the units", e.getMessage());
            }
        }
    }

    /**
     * Help method that check if the user have defined a name or not.
     * @return String - A default name or the name gave by the user
     */
    private String checkName(){
        if(name.getText().isBlank()) return "Name";
        else return name.getText();
    }

    /**
     * Help method that check if a string is blank or not. If not the method try to parse it as an integer.
     * @param intToParse String - String to be parsed as an integer
     * @param defaultValue int - default value if the string to parse is empty
     * @param failMessage string - fail message if pars is not possible.
     * @return int - the parsed integer.
     */
    private int checkIfParsable(String intToParse,int defaultValue, String failMessage) throws IllegalArgumentException{
        try {
            if(intToParse.isBlank()) return defaultValue;
            else return Integer.parseInt(intToParse);
        }catch (Exception e){
            throw new NotIntegerException( failMessage +" must be a integer number. Define it as integer number");
        }
    }

    /**
     * JavaFx's method that change the name of the army contained in the field army based on the
     * information given by the user. After the proses the application show an alert with the result.
     * c
     */
    @FXML
    private void editName(){
        if(nameOfTheArmy.getText().isBlank()) this.showAlert("Editing name of the army", "You have not changed the name of the army yet!","");
        else{
            army.setName(nameOfTheArmy.getText());
            this.checkNameAndUpdateSingleton(army);
            this.resetTextFields(nameOfTheArmy,army.getName());
            this.showAlert("Editing name of the army","The name of the army has been correctly edited!", "The name of the army has been changed to " + army.getName()+"!");
        }
    }

    /**
     * JavaFx's method that delete a number of a selected unit based on the information given by
     * the user and update the army globally. To complete it, the method calls different help method as
     * UpdateArmyInBothListInTheSingleton, updateView showAlert.
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
    @FXML
    private void deleteUnit(){
        try {
            if(unitListView.getSelectionModel().getSelectedItem() !=null){
                int sum = 0;
                String name = this.getRealNameOfTheUnit();
                for(int i = 0; i<this.checkIfParsable(numberToDelete.getText(),15,"The number of units to delete");i++){
                    if(army.getAllUnits().contains(army.returnAUnitByName(name))){
                        army.removeUnit(army.returnAUnitByName(name));
                        sum++;
                    }

                }
                this.updateArmyInBothListInTheSingleton(army,singletonArmies.getArmyNumber());
                this.updateView();
                this.showAlert("Success by deleting units","You have delete the selected unit correctly","You have deleted " + sum + " of unit: " + name + " correctly!!" + "\nThey are " + this.getNumberOfTheSameUnitIntTheArmy(name) + " of them in the army now.");
            } else this.showAlert("Unit was not selected", "You must select a unit to delete them","");
        }catch (Exception e){
            this.showError("Error by deleting units", "It was an error by deleting the units",e.getMessage());
        }
    }

    /**
     * Help method that returns the current name of the unit selected in the table view.
     * @return String - the real name of the selected unit.
     */
    private String getRealNameOfTheUnit(){
        String selectedUnitName = unitListView.getSelectionModel().getSelectedItem();
        List<Integer> indexes = new ArrayList<>();
        for (int i=0;i<selectedUnitName.length()-1;i++){
            if(selectedUnitName.charAt(i)== ' ') indexes.add(i);
        }
        return selectedUnitName.substring(0,indexes.get(indexes.size()-2));
    }


    /**
     * Method that search i the table list the unit that match with the string given by the user
     * by calling the help method getUnitsName.
     * It can be called by the javaFx object of the FXML file EditingArmy.
     */
    @FXML
    private void searchInTableView(){
        unitListView.getItems().clear();
        unitListView.getItems().addAll(this.getUnitsName(searchField.getText()));
    }

    /**
     * Help method that return the names of all units that matches with the input from the user
     * plus the sum of the same unit in the army in the form of a Lis.
     * @param input String - input
     * @return List<String> - all the names of the units
     */
    private List<String> getUnitsName(String input){
        List<String> names= new ArrayList<>();
        army.getAllUnits().forEach(unit->{
            if(!names.contains(unit.getName()+ " (" +unit.getClass().getSimpleName()+")" + " X" + this.getNumberOfTheSameUnitIntTheArmy(unit.getName()))
                    && unit.getName().contains(input)) {
                names.add(unit.getName() + " (" +unit.getClass().getSimpleName()+")" + " X" + this.getNumberOfTheSameUnitIntTheArmy(unit.getName()));
            }
        });
        return names;
    }

    /**
     * Help method that return the number of times that a units is in the army.
     * @param name String - name of the unit.
     * @return int - number of the same unit in the army.
     */
    private int getNumberOfTheSameUnitIntTheArmy(String name){
        return  Collections.frequency(army.getAllUnits()
                .stream().map(Unit::getName).collect(Collectors.toList()), name);
    }


}
