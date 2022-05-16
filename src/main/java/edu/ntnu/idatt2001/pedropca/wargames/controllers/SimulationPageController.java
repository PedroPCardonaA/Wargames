package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationPageController extends Controller implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea textArea;

    private  String text = "Hello World";

    @Override
    protected void updateView() {
        textArea.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateView();
    }
}
