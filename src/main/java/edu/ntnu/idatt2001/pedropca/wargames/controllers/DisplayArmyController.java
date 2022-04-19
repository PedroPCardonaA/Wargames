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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayArmyController extends MainPageController implements Initializable{

    //TODO: Add JavaDoc for this class
    @FXML
    private Label armyNameDisplayUnits;

    @FXML
    private TableView<Unit> tableView;

    @FXML
    private Button closeButton;

    SingletonArmies singletonArmies = SingletonArmies.getSingletonArmies();
    Army army = singletonArmies.getArmy(singletonArmies.getArmyNumber());

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

    private ObservableList<Unit> getAllUnitsFromArmy(){
        ObservableList<Unit> units = FXCollections.observableArrayList();
        units.addAll(army.getAllUnits());
        return units;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateTable();
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
    @FXML
    private void loadFromAFile(){
        try {
            File selectedFile = this.openFileChooser();
            if(selectedFile!=null){

                army = FileArmyHandler.readArmy(selectedFile.getAbsolutePath());
                if(singletonArmies.getArmyNumber()==0){
                    if (army.getName().equals(singletonArmies.getArmy(1).getName())){
                        army.setName(army.getName()+"-2");
                    }
                    this.updateArmyOneInBothListInTheSingleton(army,0);
                    this.updateTable();
                }
                if(singletonArmies.getArmyNumber()==1){
                    if (army.getName().equals(singletonArmies.getArmy(0).getName())){
                        army.setName(army.getName()+"-2");
                    }
                    this.updateArmyOneInBothListInTheSingleton(army,1);
                    this.updateTable();
                }
            }
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a error by reading the file."
                    , e.getMessage());
        }
    }

    @FXML
    private void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    private void updateTable(){
        armyNameDisplayUnits.setText(army.getName());
        tableView.setItems(this.getAllUnitsFromArmy());
        this.createTable();
    }
}
