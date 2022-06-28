package com.example.finalpr;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.civilRegistrationSystem;

public class EditCRSPageCLR implements Initializable {

    @FXML
    private TextField Age;

    @FXML
    private TextField ID;

    @FXML
    private TextField Name;

    @FXML
    private JFXComboBox<String> Sex;

    @FXML
    private Label statue;

    @FXML
    void Edit() {
        String mID = ID.getText();
        String name = Name.getText();
        int age = Integer.parseInt(Age.getText());
        String sex = Sex.getValue();

        if(civilRegistrationSystem.editPerson(mID, name, age, sex)){
            statue.setText("Person Edited Successfully... :)");
        }
    }

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CivilRegistrationSystemPage.fxml")));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sex.getItems().addAll("MAN","WOMAN");
    }
}
