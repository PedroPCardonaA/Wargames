package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.util.EnumTerrain;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * MainPageController class that is a part of the Controller hierarchy, extends
 * the class Controller and that controls over the FXML file MainPage.fxml
 * by defining all relevant JavaFx object in the FXML and all methods
 * that user can call them by interacting with the JavaFX objects.
 * This class has four normal java fields, one SingletonArmies instance, one singletonTerrain instance
 * and two armies instances and several JavaFX fields.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */

//TODO: ADD SPEED SELECTOR FOR THE SIMULATION
public class MainPageController extends Controller implements Initializable {
    private final SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    private Army army1 = new Army(singletonArmies.getArmy(0));
    private Army army2 = new Army(singletonArmies.getArmy(1));

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
    private Label armyOneName;

    @FXML
    private Label armyTwoName;

    @FXML
    private ComboBox<String> terrainComboBox;

    @FXML
    private ImageView terrainImageView;

    /**
     * Initialize method that is called after its root element is loaded.
     * Method defines all elements in the combo box terrain and define the size and
     * the picture of the image view terrain.
     * @param url url - The location of the fxml file.
     * @param resourceBundle ResourceBundle - The resource used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrainComboBox.getItems().addAll("Forest","Hills","Plains","Volcano");
        terrainImageView.setFitWidth(550);
        terrainImageView.setFitHeight(150);
        try {
            this.updateImageView("src/main/resources/Images/Start.jpg");
        } catch (Exception e) {
            this.showError("Error by initialization","It was an error by initialization the GUI.",e.getMessage());
        }
    }

    /**
     * simulateBattle method that makes an instance of Battle class with
     * the two armies of the class and simulates a battle between them.
     * Method gets help from the help methods checkContainOfSingletonTerrain,
     * updateArmies,updateView and showAlert.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void simulateBattle(){
        if(this.checkIfArmiesHaveUnits()){
            this.checkContainOfSingletonTerrain();
            Battle battle = new Battle(army1,army2);
            Army winner = battle.simulate();
            this.updateArmies(winner);
            this.updateView();
            if(winner==null) this.showAlert("Result of the battle.","The result of the battle!", "It was a Draw!");
            else this.showAlert("Result of the battle.","The result of the battle!", "The winner was: " +winner.getName() + " !");
        }
    }

    private boolean checkIfArmiesHaveUnits(){
        if(!army1.hasUnit()){
            this.showAlert("No simulation","The first army does not have unit!", "The first army does not have any unit. Add units, generate a new army or load a old army with units to simulate a battle!");
            return false;
        }
        if(!army2.hasUnit()){
            this.showAlert("No simulation","The second army does not have unit!", "The second army does not have any unit. Add units, generate a new army or load a old army with units to simulate a battle!");
            return false;
        }
        return true;
    }

    /**
     * Help method checkContainOfSingletonTerrain that checks if the terrain
     * for the battle, that is saved in the unique instance of SingletonTerrain class
     * is not defined yet. If is not defined, method defines forest as terrain.
     */
    private void checkContainOfSingletonTerrain() {
        if((terrainComboBox.getValue() ==null)) {
            this.showAlert("Terrain was not defined.","Terrain was not defined!","This battle will not have special bonus from terrain.");
        }
    }

    /**
     * Help method that update the fields armies and the armies saved in the unique
     * instance of SingletonArmies Class, based on the result of the battle.
     * @param lastArmy Army - Winner of the battle.
     */
    private void updateArmies(Army lastArmy){
        if(lastArmy!=null){
            if(army1.equals(lastArmy)){
                army1 = new Army(lastArmy);
                army2 = new Army(army2.getName());
                this.updateArmiesInSingleton(lastArmy,army2);
            } else{
                army2 = new Army(lastArmy);
                army1 = new Army(army1.getName());
                this.updateArmiesInSingleton(army1,lastArmy);
            }
        } else {
            army1 = new Army(army1.getName());
            army2 = new Army(army2.getName());
            this.updateArmiesInSingleton(army1,army2);
        }
    }

    /**
     * Method that "Resets" the main armies by defined them as copies of the back-up armies.
     * After the restarting of the armies' method updates the GUI by calling help method updateView().
     */
    @FXML
    private void resetArmies(){
        army1 = new Army(singletonArmies.getArmyFromBackUp(0));
        army2 = new Army(singletonArmies.getArmyFromBackUp(1));
        this.updateArmiesInSingleton(singletonArmies.getArmyFromBackUp(0), singletonArmies.getArmyFromBackUp(1));
        this.updateView();
    }

    /**
     * Help method that update the visible information from the army to the GUI.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @SuppressWarnings("DuplicatedCode")
    // I had to suppress the warnings of DuplicateCode because IntelliJ cannot
    // differentiate the JavaFx objects, and it understands that I repeat the same
    // lines of commands twice.
    @FXML
    private void updateView(){
        armyOneName.setText(army1.getName());
        totalArmy1.setText(army1.getAllUnits().size()+"");
        infantryArmy1.setText(army1.getInfantryUnits().size()+"");
        rangedArmy1.setText(army1.getRangedUnits().size()+"");
        cavalryArmy1.setText(army1.getCavalryUnits().size()+"");
        magicianArmy1.setText(army1.getMagicianUnits().size()+"");
        commanderArmy1.setText(army1.getCommanderUnits().size()+"");
        armyTwoName.setText(army2.getName());
        totalArmy2.setText(army2.getAllUnits().size()+"");
        infantryArmy2.setText(army2.getInfantryUnits().size()+"");
        rangedArmy2.setText(army2.getRangedUnits().size()+"");
        cavalryArmy2.setText(army2.getCavalryUnits().size()+"");
        magicianArmy2.setText(army2.getMagicianUnits().size()+"");
        commanderArmy2.setText(army2.getCommanderUnits().size()+"");
    }

    /**
     * Method that open a file from the local system by calling the method openFileChooser, defines file's path,
     * runs method readAFileArmy from FileArmyHandler class and define army1 as the result of it,
     * and calls help method updateView.
     * This method calls help method checkNameAndUpdateSingleton.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void loadFromAFileArmyOne(){
        try {
            File selectedFile = this.openFileChooser("Open a army file").showOpenDialog(null);
            if (selectedFile !=null){
                singletonArmies.setArmyNumber(0);
                this.checkNameAndUpdateSingleton(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                army1 = singletonArmies.getArmy(0);
                this.updateView();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    /**
     * Method that open a file from the local system by calling the method openFileChooser, defines file's path,
     * runs method readAFileArmy from FileArmyHandler class and define army2 as the result of it,
     * and calls help method updateView.
     * This method calls help method checkNameAndUpdateSingleton.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void loadFromAFileArmyTwo(){
        try {
            File selectedFile = this.openFileChooser("Open a army file").showOpenDialog(null);
            if(selectedFile!=null){
                singletonArmies.setArmyNumber(1);
                this.checkNameAndUpdateSingleton(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                army2 = singletonArmies.getArmy(1);
                this.updateView();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    /**
     * Method that save the army stored in the field army1 of this class as a file
     * by calling the help method saveArmyAsAFile().
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void saveArmyOneAsAFile(){
        try {
            this.saveArmyAsAFile(0);
        }catch (Exception e){
            this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                    , e.getMessage());
        }
    }

    /**
     * Method that save the army stored in the field army2 of this class as a file
     * by calling the help method saveArmyAsAFile().
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void saveArmyTwoAsAFile(){
        try {
            this.saveArmyAsAFile(1);
        }catch (Exception e){
            this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                    , e.getMessage());
        }
    }



    /**
     * Method that open a new window with all units of army 1 by calling the help method displayAllUnits.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void displayAllUnitsFromArmyOne(){
        try {
            SingletonArmies.getSingletonArmies().setArmyNumber(0);
            this.displayAllUnits();
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    /**
     * Method that open a new window with all units of army 2 by calling the help method displayAllUnits.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void displayAllUnitsFromArmyTwo(){
        try {
            SingletonArmies.getSingletonArmies().setArmyNumber(1);
            this.displayAllUnits();
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    /**
     * Help method that load the FXML file DisplayArmy and open it as a new window
     * by calling the help method openNewScene.
     * @throws IOException the help method openNewScene may throw IOExceptions
     */
    private void displayAllUnits() throws IOException{
        this.openNewScene("/Views/DisplayArmy.fxml", "Display Units");
    }

    //TODO: EDIT GENERATE SYSTEM
    /**
     * Method that defined army stored in the field army1 as a pre-defined army.
     * The user has the possibility to change the name of the pre-define army.
     * This method gets help from methods generateArmy, stringInputWindow, updateArmyInBothListInTheSingleton
     * and update view.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void generateArmy1(){
        try {
            singletonArmies.setArmyNumber(0);
            String name = stringInputWindow(armyOneName.getScene().getWindow());
            if (!name.isEmpty()){
                army1 = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army1);
                this.updateView();}
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }

    /**
     * Method that defined army stored in the field army2 as a pre-defined army.
     * The user has the possibility to change the name of the pre-define army.
     * This method gets help from methods generateArmy, stringInputWindow, updateArmyInBothListInTheSingleton
     * and update view.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void generateArmy2(){
        try {
            singletonArmies.setArmyNumber(1);
            String name = stringInputWindow(armyOneName.getScene().getWindow());
            if (!name.isEmpty()){
                army2 = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army2);
                this.updateView();}
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }



    /**
     * Method that open a new window where is possible editing the army contained in the field army1
     * by calling the help method openEditArmyWindow.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void editArmyOne(){
        try {
            singletonArmies.setArmyNumber(0);
            this.openEditArmyWindow();
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    /**
     * Method that open a new window where is possible editing the army contained in the field army2
     * by calling the help method openEditArmyWindow.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void editArmyTwo(){
        try {
            singletonArmies.setArmyNumber(1);
            this.openEditArmyWindow();
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    /**
     * Help method that load the FXML file EditingArmy and open it as a new window
     * by calling the help method openNewScene.
     * @throws IOException the help method openNewScene may throw IOExceptions
     */
    private void openEditArmyWindow() throws IOException {
        this.openNewScene("/Views/EditingArmy.fxml", "Editing army");
    }

    /**
     * Help method that open a new scene with a defined path and tittle.
     * @param path String - Path of the field to load.
     * @param title String - Tittle of the new window.
     */
    private void openNewScene(String path,String title) throws IOException {
        this.openANewScene(path,title,armyOneName);
        army1 = new Army(singletonArmies.getArmy(0));
        army2 = new Army(singletonArmies.getArmy(1));
        this.updateView();
    }

    /**
     * Method that update data in the unique instance of SingletonTerrain class and
     * change the images contained in the terrain image view according to new terrain.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void updateTerrain(){
        try {
            switch (terrainComboBox.getValue()) {
                case "Forest":
                    EnumTerrain.setForest();
                    this.updateImageView("src/main/resources/Images/forest.jpg");
                    break;
                case "Hills":
                    EnumTerrain.setHILL();
                    this.updateImageView("src/main/resources/Images/hills.jpg");
                    break;
                case "Plains":
                    EnumTerrain.setPLAINS();
                    this.updateImageView("src/main/resources/Images/plain.jpg");
                    break;
                case "Volcano":
                    EnumTerrain.setVolcano();
                    this.updateImageView("src/main/resources/Images/Volcano.jpg");
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            this.showError("Error by updating the image!", "It was an error by reading and updating the image view!" ,e.getMessage());
        }
    }

    private void updateImageView(String imagePath) throws FileNotFoundException {
        terrainImageView.setImage(new Image(new FileInputStream(imagePath)));
    }


    /**
     * Method for a button in the menu bar that close the program.
     */
    @FXML
    private void closeTheProgramButton(){
        Platform.exit();
    }

}
