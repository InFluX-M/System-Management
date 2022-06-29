package com.example.finalpr;

import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.Exceptions.InvalidIInput;
import com.example.finalpr.Exceptions.InvalidType;
import com.example.finalpr.MYSQL.No;
import com.example.finalpr.Systems.Systems;
import com.jfoenix.controls.JFXTextArea;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;
import static com.example.finalpr.HelloApplication.systems;

public class RegisterDRSPageCLR implements Initializable {
    @FXML
    private JFXTextArea Address;

    @FXML
    private TextField Cost;

    @FXML
    private Label DocumentRegistrationCode;

    @FXML
    private TextField OwnerID;

    @FXML
    private Label status;

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DocumentRegistrationSystemPage.fxml")));
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
    void register() {

        try {
            InputRequiredFields.validateRegisterEstate(OwnerID.getText(), Address.getText(), Cost.getText());
            InvalidIInput.validateOwnerID(OwnerID.getText());
            InvalidType.validateMoneyAmount(Cost.getText());

            String documentRegistrationCode = No.documentRegistrationCode;
            String ownerID = OwnerID.getText();
            String address = Address.getText();
            LocalDate date = Systems.localDate;
            double cost = Double.parseDouble(Cost.getText());

            Estate estates = new Estate(documentRegistrationCode, ownerID, address, date, cost);

            if(documentRegistrationSystem.addEstate(estates)){
                status.setText("Estate Registered Successfully... :)");
            }

        } catch (InvalidType e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Type... :(");
            errorAlert1.setContentText("Cost Must be Number Type.");
            errorAlert1.showAndWait();
        } catch (InputRequiredFields e) {

            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("OwnerID, Address, Cost Must Be Input.");
            errorAlert1.showAndWait();

        } catch (InvalidIInput e) {

            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Information... :(");
            errorAlert1.setContentText("Input OwnerID is not Valid.");
            errorAlert1.showAndWait();

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DocumentRegistrationCode.setText("DR Code: "+ No.getDocumentRegistrationCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
