package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import edu.ntnu.idatt2001.pedropca.wargames.models.Army;
import edu.ntnu.idatt2001.pedropca.wargames.models.units.Unit;
import edu.ntnu.idatt2001.pedropca.wargames.util.FileArmyHandler;
import edu.ntnu.idatt2001.pedropca.wargames.util.SingletonArmies;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayArmyController implements Initializable{

    //TODO: Add JavaDoc for this class
    @FXML
    private Label armyNameDisplayUnits;

    @FXML
    private TableView<Unit> tableView;

    @FXML
    private Button closeButton;

    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();

    Army army = singletonArmies.getArmy(SingletonArmies.getSingletonArmies().getArmyNumber());

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

    private ObservableList<Unit> getAllUnitsFromArmy1(){
        ObservableList<Unit> units = FXCollections.observableArrayList();
        units.addAll(army.getAllUnits());
        return units;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        armyNameDisplayUnits.setText(army.getName());
        tableView.setItems(this.getAllUnitsFromArmy1());
        this.createTable();
    }


    @FXML
    private void saveArmyAsFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save army as a file.");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if(file !=null){
            try {
                FileArmyHandler.writeAFile(new Army(army),file.getParent(),file.getName());
            } catch (IOException e) {
                this.showError("Error by saving the Army!","It was a error by saving the army as a file."
                        , e.getMessage());
            }
        }
    }
    private void showError(String tittle,String message,String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(tittle);
        alert.setHeaderText(message);
        alert.setContentText(text);
        alert.setResizable(true);
        alert.showAndWait();
    }

    // TODO: Apply method loadFromAFile in DisplayArmyController.

    @FXML
  /*  private void loadFromAFile(){
        try {
            File selectedFile = this.openFileChooser();
            if(selectedFile!=null){
                if(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()).getName().equals(army1.getName())){
                    army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                    army2.setName(army2.getName()+"-2");
                }else army2 = new Army(FileArmyHandler.readArmy(selectedFile.getAbsolutePath()));
                Army backUp = singletonArmies.getArmy(0);
                this.updateBothArmies(backUp,army2);
                this.updateView();
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }*/

    private void checkName(){

    }

    private void updateBothArmies(Army army1,Army army2){
        singletonArmies.setEmptySingletonArmy();
        singletonArmies.setEmptyArmyBackUp();
        singletonArmies.putArmy(new Army(army1));
        singletonArmies.putArmy(new Army(army2));
        singletonArmies.putArmyInBackUp(new Army(army1));
        singletonArmies.putArmyInBackUp(new Army(army2));
    }

    private File openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a army file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("TXT - serializable","*.txt"));
        return fileChooser.showOpenDialog(null);
    }


    @FXML
    private void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
