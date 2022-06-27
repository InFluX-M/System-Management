package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCheck;
import com.example.finalpr.Availabilities.CurrentAccount;
import com.example.finalpr.Systems.Systems;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;

public class PassCheckPanelCLR implements Initializable {

    @FXML
    private JFXListView<BankCheck> BankCheckList;

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BankAccountPanel.fxml"));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }
    }

    @FXML
    void exit(MouseEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<BankCheck> bankChecks = FXCollections.observableArrayList(
                ((CurrentAccount)bankSystem.getNowBankAccount()).getChecksReceived()
        );

        BankCheckList.setItems(bankChecks);
        BankCheckList.setCellFactory(param -> new BankCheckCell());
    }
}
