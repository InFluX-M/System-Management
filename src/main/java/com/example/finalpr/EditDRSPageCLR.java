package com.example.finalpr;

import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.Exceptions.InvalidIInput;
import com.example.finalpr.Exceptions.InvalidType;
import com.example.finalpr.Systems.Systems;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

public class EditDRSPageCLR {

    @FXML
    private JFXTextArea Address;

    @FXML
    private TextField Cost;

    @FXML
    private TextField DocumentRegistrationCode;

    @FXML
    private TextField OwnerID;

    @FXML
    private Label status;

    @FXML
    void Edit() {

        try {
            InputRequiredFields.validateEditEstate(DocumentRegistrationCode.getText(), OwnerID.getText(), Address.getText(), Cost.getText());
            InvalidIInput.validateOwnerID(OwnerID.getText());
            InvalidIInput.validateDocumentRegistrationCode(DocumentRegistrationCode.getText());
            InvalidType.validateMoneyAmount(Cost.getText());

            String documentRegistrationCode = DocumentRegistrationCode.getText();
            String ownerID = OwnerID.getText();
            String address = Address.getText();
            LocalDate date = Systems.localDate;
            double cost = Double.parseDouble(Cost.getText());

            if(documentRegistrationSystem.editEstate(documentRegistrationCode, ownerID, address, date, cost)) {
                status.setText("Estate Edited Successfully... :)");
            }

        } catch (InputRequiredFields e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("DocumentRegistrationCode, OwnerID, Address, Cost Must Be Input.");
            errorAlert1.showAndWait();

        } catch (InvalidType e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Type... :(");
            errorAlert1.setContentText("Cost Must be Number Type.");
            errorAlert1.showAndWait();

        } catch (InvalidIInput e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Information... :(");
            errorAlert1.setContentText("Input OwnerID, DocumentRegistrationCode is not Valid.");
            errorAlert1.showAndWait();
        }
    }

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

}
