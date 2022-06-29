package com.example.finalpr;

import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.Exceptions.InvalidIInput;
import com.example.finalpr.Exceptions.InvalidType;
import com.jfoenix.controls.JFXButton;
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

        try {
            InputRequiredFields.validateEditPerson(ID.getText(), Name.getText(), Age.getText(), Sex.getValue());
            InvalidIInput.validateOwnerID(ID.getText());
            InvalidType.validateAge(Age.getText());


            String mID = ID.getText();
            String name = Name.getText();
            int age = Integer.parseInt(Age.getText());
            String sex = Sex.getValue();

            if(civilRegistrationSystem.editPerson(mID, name, age, sex)){
                statue.setText("Person Edited Successfully... :)");
            }

        } catch (InputRequiredFields e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("ID, Name, Age, Sex Must Be Input.");
            errorAlert1.showAndWait();

        } catch (InvalidType e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Type... :(");
            errorAlert1.setContentText("Age Must be Integer Number Type.");
            errorAlert1.showAndWait();

        } catch (InvalidIInput e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Information... :(");
            errorAlert1.setContentText("Input ID is not Valid.");
            errorAlert1.showAndWait();

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
