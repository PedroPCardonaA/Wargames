package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.Battle;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.*;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonTerrain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * MainPageController class that controls over the FXML file MainPage.fxml
 * by defining all relevant JavaFx object in the FXML and all methods
 * that user can call from the FXML file.
 * This class has four fields, one SingletonArmies instance, one singletonTerrain instance
 * and two armies instances.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class MainPageController implements Initializable {
    private final SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    private final SingletonTerrain singletonTerrain =SingletonTerrain.getSingletonTerrain();
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
    protected void simulateBattle(){
        this.checkContainOfSingletonTerrain();
        Battle battle = new Battle(army1,army2);
        Army winner = battle.simulate();
        this.updateArmies(winner);
        this.updateView();
        if(winner==null) this.showAlert("Result of the battle.","The result of the battle!", "It was a Draw!");
        else this.showAlert("Result of the battle.","The result of the battle!", "The winner was: " +winner.getName() + " !");
    }

    /**
     * Help method checkContainOfSingletonTerrain that checks if the terrain
     * for the battle, that is saved in the unique instance of SingletonTerrain class
     * is not defined yet. If is not defined, method defines forest as terrain.
     */
    private void checkContainOfSingletonTerrain() {
        if((terrainComboBox.getValue() ==null)) {
            this.showAlert("Terrain was not defined.","Terrain was not defined!","Forest will be defined instead.");
            singletonTerrain.setForestAsTerrain();
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
     * Method that update the current field armies into the unique instance of
     * SingletonArmies class.
     * @param armyOne Army - Army saved on the field army1 of this class
     * @param armyTwo Army - Army saved on the field army2 of this class
     */
    protected void updateArmiesInSingleton(Army armyOne, Army armyTwo){
        singletonArmies.setEmptySingletonArmy();
        singletonArmies.putArmy(new Army(armyOne));
        singletonArmies.putArmy(new Army(armyTwo));
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
    // differentiate the JavaFx objects and understand that I repeat the same
    // lines of commands twice.
    @FXML
    protected void updateView(){
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
     * run method readAFileArmy from the army1 object and calls help method updateView.
     * This method calls help method updateArmyOneInBothListInTheSingleton to update
     * the both list of armies in SingletonArmies class correctly.
     * The list are the main list of armies and a backUp list of armies.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void loadFromAFileArmyOne(){
        try {
            File selectedFile = this.openFileChooser();
            if (selectedFile !=null){
                //This if sentence avoids a possible bug tha happens when the both armies have the same name
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army2.getName())){
                    army1 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army1.setName(army2.getName()+"-2");
                }else army1 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                this.updateArmyInBothListInTheSingleton(army1,0);
                this.updateView();}
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    /**
     * Method that open a file from the local system by calling the method openFileChooser, defines file's path,
     * run method readAFileArmy from the army2 object and calls help method updateView.
     * This method calls help method updateArmyOneInBothListInTheSingleton to update
     * the both list of armies in SingletonArmies class correctly.
     * The list are the main list of armies and a backUp list of armies.
     * It can be called by the javaFx object of the FXML file MainPage.
     */
    @FXML
    private void loadFromAFileArmyTwo(){
        try {
            File selectedFile = this.openFileChooser();
            if(selectedFile!=null){
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army1.getName())){
                    army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army2.setName(army2.getName()+"-2");
                }else army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                this.updateArmyInBothListInTheSingleton(army2,1);
                this.updateView();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    /**
     * Help method that returns an instance of class File by calling
     * the method showOpenDialog of an instance of class fileChooser that
     * allows the user to select a file from their local system.
     * @return File - The selected file.
     */
    protected File openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a army file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
        return fileChooser.showOpenDialog(null);
    }

    /**
     * Help method that update one of the armies in both list in the unique instance of
     * SingletonArmies class without changing the other army saved on the lists.
     * @param army Army - the army to be updated.
     * @param index int - the index of the armies on the list.
     */
    protected void updateArmyInBothListInTheSingleton(Army army, int index){
        if(index == 0){
            Army save = singletonArmies.getArmy(1);
            Army saveBackUp = singletonArmies.getArmyFromBackUp(1);
            singletonArmies.setEmptySingletonArmy();
            singletonArmies.setEmptyArmyBackUp();
            singletonArmies.putArmy(new Army(army));
            singletonArmies.putArmy(new Army(save));
            singletonArmies.putArmyInBackUp(new Army(army));
            singletonArmies.putArmyInBackUp(new Army(saveBackUp));
        }
        else{
            Army save = singletonArmies.getArmy(0);
            Army saveBackUp = singletonArmies.getArmyFromBackUp(0);
            singletonArmies.setEmptySingletonArmy();
            singletonArmies.setEmptyArmyBackUp();
            singletonArmies.putArmy(new Army(save));
            singletonArmies.putArmy(new Army(army));
            singletonArmies.putArmyInBackUp(new Army(saveBackUp));
            singletonArmies.putArmyInBackUp(new Army(army));
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

    //TODO: FINISH JAVADOC FROM HERE!!!!
    /**
     * Help method that
     * @param index
     * @throws IOException
     */
    protected void saveArmyAsAFile(int index) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save army as a file.");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if(file !=null){
            FileArmyHandler.writeAFile(new Army(singletonArmies.getArmy(index)),file.getParent(),file.getName());
        }
    }

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

    private void displayAllUnits() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/displayArmy.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Display Units");
        editSceneData(root, stage);
    }

    private void showError(String tittle,String message,String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle(tittle);
        alert.setHeaderText(message);
        alert.setContentText(text);
        alert.showAndWait();
    }
    private void showAlert(String tittle,String message,String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle(tittle);
        alert.setHeaderText(message);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void generateArmy1(){
        try {
            army1 = new Army(this.generateArmy(stringInputWindow()));
            if(army1.getName().equals(army2.getName())){
                army1.setName(army1.getName() +"-2");
            }
            this.updateArmyInBothListInTheSingleton(army1,0);
            this.updateView();
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }

    @FXML
    private void generateArmy2(){
        try {
            army2 = new Army(this.generateArmy(stringInputWindow()));
            if(army2.getName().equals(army1.getName())){
                army2.setName(army1.getName() +"-2");
            }
            this.updateArmyInBothListInTheSingleton(army2,1);
            this.updateView();
        }catch (Exception e){
            this.showError("Error by generating an Army!", "It was an error by generating the army: ", e.getMessage());
        }
    }


    private String stringInputWindow(){
        Stage stringInputWindow = new Stage();
        stringInputWindow.initOwner(armyOneName.getScene().getWindow());
        stringInputWindow.initModality(Modality.WINDOW_MODAL);
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        TextField commentBox = new TextField();
        commentBox.setPromptText("Enter the name of the army.");
        commentBox.setFocusTraversable(false);
        commentBox.setAlignment(Pos.CENTER);
        commentBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        commentBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        Label commentLabel = new Label("Enter the name of the Army: ");
        commentLabel.setFont(Font.font("Papyrus", FontWeight.BOLD,24));
        commentLabel.setMinHeight(Control.USE_COMPUTED_SIZE);
        commentLabel.setMinWidth(Control.USE_COMPUTED_SIZE);
        Button button = new Button("Enter name.");
        button.setOnAction(actionEvent -> {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
            actionEvent.consume();
        });
        box.getChildren().addAll(commentLabel,commentBox,button);
        stringInputWindow.setScene(new Scene(box,375,150));
        stringInputWindow.showAndWait();
        return commentBox.getText();
    }

    private Army generateArmy(String name) throws IllegalArgumentException{
        Army army = new Army( name);
        List<Unit> mixedList = new ArrayList<>();
        for(int i =0;i<50;i++){
            mixedList.add(new CavalryUnit("CAVALRY",100));
            mixedList.add(new RangedUnit("Ranged",100));
            mixedList.add(new InfantryUnit("Infantry",100));
            mixedList.add(new MagicianUnit("Magician",100));
        }
        mixedList.add(new CommanderUnit("Commander",250));
        army.addAll(mixedList);
        return army;
    }

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

    private void openEditArmyWindow() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/editingArmy.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Editing army");
        this.editSceneData(root, stage);
    }

    private void editSceneData(Parent root, Stage stage) {
        stage.setScene(new Scene(root));
        stage.initOwner(armyOneName.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        army1 = new Army(singletonArmies.getArmy(0));
        army2 = new Army(singletonArmies.getArmy(1));
        this.updateView();
    }

    /**
     * Method for a button in the menu bar that close the program.
     */
    @FXML
    private void closeTheProgramButton(){
        Platform.exit();
    }

    @FXML
    private void updateTerrain(){
        try {
            switch (terrainComboBox.getValue()) {
                case "Forest":
                    singletonTerrain.setForestAsTerrain();
                    this.updateImageView("src/main/resources/Images/forest.jpg");
                    break;
                case "Hills":
                    singletonTerrain.setForestAsTerrain();
                    this.updateImageView("src/main/resources/Images/hills.jpg");
                    break;
                case "Plains":
                    singletonTerrain.setForestAsTerrain();
                    this.updateImageView("src/main/resources/Images/plain.jpg");
                    break;
                case "Volcano":
                    singletonTerrain.setForestAsTerrain();
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
}
