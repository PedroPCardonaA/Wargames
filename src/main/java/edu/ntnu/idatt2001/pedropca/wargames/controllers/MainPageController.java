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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.awt.*;
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
public class MainPageController extends Controller implements Initializable{
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
    private TextField healerArmy1;

    @FXML
    private TextField healerArmy2;

    @FXML
    private Label armyOneName;

    @FXML
    private Label armyTwoName;

    @FXML
    private Button simulationButton;

    @FXML
    private ComboBox<EnumTerrain> terrainComboBox;

    @FXML
    private ImageView terrainImageView;

    @FXML
    private ComboBox<String> speedSimulationComboBox;

    @FXML
    private Button editing1;

    @FXML
    private Button editing2;

    @FXML
    private Button resetArmies;

    @FXML
    private MenuButton options1;

    @FXML
    private MenuButton options2;

    /**
     * Initialize method that is called after its root element is loaded.
     * Method defines all elements in the combo box terrain and define the size and
     * the picture of the image view terrain.
     * @param url url - The location of the fxml file.
     * @param resourceBundle ResourceBundle - The resource used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrainComboBox.getItems().addAll(EnumTerrain.FOREST,EnumTerrain.HILL,EnumTerrain.PLAINS,EnumTerrain.VOLCANO);
        speedSimulationComboBox.getItems().addAll("Skip","Normal","Slow");
        terrainImageView.setFitWidth(550);
        terrainImageView.setFitHeight(150);
        try {
            this.updateImageView("src/main/resources/Images/Start.jpg");
            this.playTheSong();
        } catch (Exception e) {
            this.showError("Error by initialization","It was an error by initialization the WarGamesApp.",e.getMessage());
        }
    }


    /**
     * Method that start the running of the correct simulation that depends on the simulation speed.
     * The simulation speed is defined by the content
     * of the comboBox speedSimulation. This method get helps of the help methods
     * showAlert, checkIfArmiesHaveUnits, CheckContainsOfTheSingletonTerrain
     * simulateBattleSkip, simulateBattleNormal and simulateBattleSlow.
     */
    @FXML
    private void simulationBattle(){
        if(this.checkIfArmiesHaveUnits()){
            if(speedSimulationComboBox.getValue() == null){
                this.showAlert("Speed was not defined!", "The speed of the simulation was not defined!", "It simulation will be skipped");
                this.simulateBattleSkip();
            } else {
                this.checkContainOfSingletonTerrain();
                switch (speedSimulationComboBox.getValue()){
                    case "Skip": this.simulateBattleSkip(); break;
                    case "Normal": this.simulateBattleNormal(); break;
                    case "Slow": this.simulateBattleSlow();break;
                }
            }
        }
    }

    /**
     * Help method that makes an instance of Battle class with
     * the two armies of the class and simulates a battle between them.
     * The result is returned to the form of an information alert.
     * Method gets help from the help updateArmies,updateView and showAlert.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    private void simulateBattleSkip(){
        Battle battle = new Battle(army1,army2);
        Army winner = battle.simulate();
        this.updateArmies(winner);
        this.updateView();
        if(winner==null) this.showAlert("Result of the battle.","The result of the battle!", "It was a Draw!");
        else this.showAlert("Result of the battle.","The result of the battle!", "The winner was: " +winner.getName() + " !");
    }

    /**
     * Help method that simulate a battle with a fast animation that show the current number of unit from both armies.
     * The simulation stops when one of the armies loses every single unit. In every iteration the information
     * that is showed be is update. The other buttons are disabled to avoid possible errors while simulation.
     *
     */
    private void simulateBattleNormal(){
        Battle battle = new Battle(army1,army2);
        Thread thread = new Thread(()->{
            Runnable updater = ()->{
                battle.singularBattle();
                army1 =battle.getArmy1();
                army2 =battle.getArmy2();
                this.updateView();
            };
            while (true){
                try {
                    Thread.sleep(200);
                    if(!battle.bothArmiesHaveUnits()){
                        Army winner = battle.checkWinnerArmy();
                        this.updateArmies(winner);
                        this.updateView();
                        this.setDisableOfTheButtons(false);
                        break;
                    }
                } catch (InterruptedException e) {
                    this.showError("Error by simulation","Error by medium simulation!",e.getMessage());
                }
                Platform.runLater(updater);
                this.setDisableOfTheButtons(true);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Help method that open the FXML file SimulationView and its controller to proceed with a closer view of the simulation.
     */
    private void simulateBattleSlow(){
        try {
            this.openNewSceneFromMainPage("/views/SimulationView.fxml","Testing");
        } catch (IOException e) {
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    /**
     * Help method that open a
     * @return Boolean - true if both armies has units, false if one of them does not have units.
     */
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
     * After the restarting of the armies' method updates the WarGamesApp by calling help method updateView().
     */
    @FXML
    private void resetArmies(){
        army1 = new Army(singletonArmies.getArmyFromBackUp(0));
        army2 = new Army(singletonArmies.getArmyFromBackUp(1));
        this.updateArmiesInSingleton(singletonArmies.getArmyFromBackUp(0), singletonArmies.getArmyFromBackUp(1));
        this.updateView();
    }

    /**
     * Help method that update the visible information from the army to the WarGamesApp.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @SuppressWarnings("DuplicatedCode")
    // I had to suppress the warnings of DuplicateCode because IntelliJ cannot
    // differentiate the JavaFx objects, and it understands that I repeat the same
    // lines of commands twice.

    @Override
    protected void updateView(){
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
        Controller.setDisplayArmyAsActualPage();
        this.openNewSceneFromMainPage("/views/DisplayArmy.fxml", "Display Units");
        Controller.setMainPaigeAsActualPage();
    }

    /**
     * Method that defined army stored in the field army1 as a pre-defined army.
     * The user has the possibility to change the name of the pre-define army.
     * This method gets help from methods generateArmy1
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void generateArmy1(){
        this.generateArmyFromMainPage(0);
    }

    /**
     * Method that defined army stored in the field army2 as a pre-defined army.
     * The user has the possibility to change the name of the pre-define army.
     * This method gets help from methods generateArmy1
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void generateArmy2(){
        this.generateArmyFromMainPage(1);
    }

    /**
     * Method that generate a new army for one of the armies field of the class.
     * The signature fo the method contains the number of the army to be generated.
     *
     * @param armyNumber int - index of the army in the singletonArmy class
     */
    private void generateArmyFromMainPage(int armyNumber){
        singletonArmies.setArmyNumber(armyNumber);
        String name = stringInputWindow(armyOneName.getScene().getWindow());
        if (!name.isBlank()){
            if(armyNumber == 0){
                army1 = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army1);
            } else {
                army2 = this.generateArmy(name);
                this.checkNameAndUpdateSingleton(army2);
            }
            this.updateView();}
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
        Controller.setEditingArmyAsActualPage();
        this.openNewSceneFromMainPage("/views/EditingArmy.fxml", "Editing army");
        Controller.setMainPaigeAsActualPage();
    }

    /**
     * Help method that open a new scene with a defined path and tittle.
     * @param path String - Path of the field to load.
     * @param title String - Tittle of the new window.
     */
    private void openNewSceneFromMainPage(String path, String title) throws IOException {
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
                case FOREST:
                    EnumTerrain.setForest();
                    this.updateImageView("src/main/resources/Images/forest.jpg");
                    break;
                case HILL:
                    EnumTerrain.setHill();
                    this.updateImageView("src/main/resources/Images/hills.jpg");
                    break;
                case PLAINS:
                    EnumTerrain.setPlains();
                    this.updateImageView("src/main/resources/Images/plain.jpg");
                    break;
                case VOLCANO:
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

    /**
     * Help method that ipdate the instance of ImageView terrainImageView by showing a new image.
     * @param imagePath String - path of the new image.
     * @throws FileNotFoundException If the path does not exist.
     */
    private void updateImageView(String imagePath) throws FileNotFoundException {
        terrainImageView.setImage(new Image(new FileInputStream(imagePath)));
    }


    /**
     * Method for a button in the menu bar that close the program.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void closeTheProgramButton(){
        armyOneName.getScene().getWindow().hide();
    }

    private void setDisableOfTheButtons(boolean state){
        simulationButton.setDisable(state);
        terrainComboBox.setDisable(state);
        speedSimulationComboBox.setDisable(state);
        editing1.setDisable(state);
        editing2.setDisable(state);
        resetArmies.setDisable(state);
        options1.setDisable(state);
        options2.setDisable(state);
    }

    /**
     * Help method that play a background song that
     * improves the atmosphere and immersion of the game.
     */
    private void playTheSong() {
        //I really wanted to implement MediaPlayer, but it was completely impossible. The song stops after 5 seconds.
        //I read on the internet that MediaPlayer object was very buggy when it is related to only play audio files.
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(this.getClass().getResource("/audio/alexander-nakarada-chase.mp3")).toExternalForm());
        audioClip.setCycleCount(AudioClip.INDEFINITE);
        audioClip.setVolume(0.25);
        audioClip.play();
    }

    /**
     * Method that open an extern pdf document that contains the user guide of the application.
     * Document is very relevant for a correct use of the application.
     */
    @FXML
    private void openUserManual(){
        try {
            File file = new File("src/main/resources/documents/User manual.pdf");
            if(file.exists()){
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(file);
                }
            }
        }catch (IOException e){
            this.showError("Error by loading hints","It happened an error by loading the pdf document with the user guide", "The error was : " +e.getMessage());
        }
    }
}
