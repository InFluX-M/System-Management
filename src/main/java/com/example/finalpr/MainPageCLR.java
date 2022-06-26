package com.example.finalpr;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageCLR implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void BankSystem(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BankSystemPageLogin.fxml"));
            Stage s1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        } catch (IOException e) {

        }
    }

    @FXML
    void CivilRegistrationSystem(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CivilRegistrationSystemLogin.fxml"));
            Stage s1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        } catch (IOException e) {

        }
    }

    @FXML
    void DocumentRegistrationSystem(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DocumentRegistrationSystemLogin.fxml"));
            Stage s1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        } catch (IOException e) {

        }

    }

}
