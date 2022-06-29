package com.example.finalpr;

import com.example.finalpr.Availabilities.Person;

import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.Exceptions.InvalidType;
import com.example.finalpr.MYSQL.No;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.civilRegistrationSystem;

public class RegisterCRSPageCLR implements Initializable {
    @FXML
    private TextField Age;

    @FXML
    private Label ID;

    @FXML
    private TextField Name;

    @FXML
    private JFXComboBox<String> Sex;

    @FXML
    private Label labelRegister;

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
            e.printStackTrace();
        }

    }

    @FXML
    void register(){

        try {
            InputRequiredFields.validateRegisterPerson(Name.getText(), Age.getText(), Sex.getValue());
            InvalidType.validateAge(Age.getText());

            String ID = No.ID;
            String name = Name.getText();
            int age = Integer.parseInt(Age.getText());
            String sex = Sex.getValue();

            if(civilRegistrationSystem.addPerson(new Person(ID, name, age, sex))){

                labelRegister.setTextAlignment(TextAlignment.CENTER);
                labelRegister.setText("Person Registered Successfully... :)");
            }

        } catch (InvalidType e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Type... :(");
            errorAlert1.setContentText("Age Must be Integer Number Type.");
            errorAlert1.showAndWait();

        } catch (InputRequiredFields e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("ID, Name, Age, Sex Must Be Input.");
            errorAlert1.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sex.getItems().addAll("MAN","WOMAN");
        try {
            ID.setText("ID: "+ No.getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
