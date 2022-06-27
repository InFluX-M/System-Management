package com.example.finalpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CivilRegistrationSystemPageCLR implements Initializable {

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainPanelAdmin.fxml"));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }
    }

    @FXML
    void Register(MouseEvent event) {

    }

    @FXML
    void Edit(MouseEvent event) {

    }

    @FXML
    void Delete(MouseEvent event) {

    }


    @FXML
    void exit(MouseEvent event) {
        System.exit(1);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img11 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\verify.png");
        Image img22 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\edit.png");
        Image img33 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\delete (1).png");

        img1.setImage(img11);
        img2.setImage(img22);
        img3.setImage(img33);
    }
}
