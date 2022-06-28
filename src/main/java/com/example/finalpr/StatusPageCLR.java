package com.example.finalpr;

import com.example.finalpr.Availabilities.SavingAccount;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;
import static com.example.finalpr.HelloApplication.systems;

public class StatusPageCLR implements Initializable {

    @FXML
    private JFXButton GetSavingMoney;

    @FXML
    private Label status;

    @FXML
    void Back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankAccountPanel.fxml")));
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
    void SavingMoney() {

        if(systems.getSavingMoney()){
            status.setText("Get Saving Money... :)");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!(bankSystem.getNowBankAccount() instanceof SavingAccount)){
            GetSavingMoney.setDisable(true);
        }

    }
}
