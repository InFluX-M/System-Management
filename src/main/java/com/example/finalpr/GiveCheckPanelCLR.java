package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCheck;
import com.example.finalpr.Systems.BankSystem;
import com.jfoenix.controls.JFXButton;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;

public class GiveCheckPanelCLR implements Initializable {


    @FXML
    private TextField Amount;

    @FXML
    private TextField AccountNumber;

    @FXML
    private Label CheckNumber;

    @FXML
    private Label statue;

    @FXML
    void Give(MouseEvent event) {
        String checkNumber = bankSystem.getCheckNumber();
        String accountNumberSender = bankSystem.getNowBankAccount().getAccountNumber();
        String accountNumberReceiver = AccountNumber.getText();
        double amount = Double.parseDouble(Amount.getText());
        LocalDate dateRegister = BankSystem.localDate;

        BankCheck bankCheck = new BankCheck(checkNumber, accountNumberSender, accountNumberReceiver, amount, dateRegister);
        bankSystem.giveCheck(bankCheck);
        statue.setText("Check Registered Successfully... :)");
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CheckNumber.setText(bankSystem.getCheckNumber()+"");
    }
}
