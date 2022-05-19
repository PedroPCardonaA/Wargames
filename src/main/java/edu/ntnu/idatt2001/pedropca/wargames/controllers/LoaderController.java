package edu.ntnu.idatt2001.pedropca.wargames.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoaderController extends Controller implements Initializable{
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            imageView.setImage(new Image(new FileInputStream("src/main/resources/images/loadingImage.jpg")));
        }catch (Exception e){
            this.showError("Error by loading the file!","It was a fail by loading the fxml file from the next scene."
                    , e.getMessage());
        }
    }

    @Override
    protected void updateView() {

    }
}
