package com.example.finalpr;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class BankSystemPageCLR implements Initializable {

    @FXML
    private Circle circle1;

    @FXML
    private JFXComboBox<String> comboRules;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUserName;

    @FXML
    private Label statueLogin;

    @FXML
    void createNewProfile(MouseEvent event) {

    }

    @FXML
    void login(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboRules.getItems().addAll(
                "Admin","User"
        );
    }
}
