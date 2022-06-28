package com.example.finalpr;

import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.MYSQL.No;
import com.jfoenix.controls.JFXTextArea;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

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

        String documentRegistrationCode = No.documentRegistrationCode;
        String ownerID = OwnerID.getText();
        String address = Address.getText();
        LocalDate date = LocalDate.now();
        double cost = Double.parseDouble(Cost.getText());

        Estate estates = new Estate(documentRegistrationCode, ownerID, address, date, cost);

        if(documentRegistrationSystem.addEstate(estates)){
            status.setText("Estate Registered Successfully... :)");
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
