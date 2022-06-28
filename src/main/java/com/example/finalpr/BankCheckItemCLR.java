package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCheck;
import com.example.finalpr.Systems.BankSystem;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class BankCheckItemCLR {

    @FXML
    private Label AccountNumber;

    @FXML
    private Label Amount;

    @FXML
    private AnchorPane CheckItem;

    @FXML
    private Label CheckNumber;

    @FXML
    private Label DateRegister;

    @FXML
    private JFXButton Pass;

    @FXML
    private Label Passed;

    @FXML
    void Passe() {
        bankCheck.setPassed(true);
    }

    private BankCheck bankCheck;

    public BankCheckItemCLR() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BankCheckItem.fxml"));
            loader.setController(this);
            CheckItem = loader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCheck(BankCheck bankCheck) {

        this.bankCheck = bankCheck;
        CheckNumber.setText(bankCheck.getCheckNumber());
        AccountNumber.setText(bankCheck.getAccountNumberSender());
        Amount.setText(bankCheck.getAmount()+"$");
        Passed.setText(bankCheck.isPassed()+"");
        DateRegister.setText(bankCheck.getDateRegister().toString());
        if(bankCheck.isPassed() || bankCheck.getDateRegister().compareTo(BankSystem.localDate)<0) Pass.setDisable(true);

    }

    public Node getView() {
        return CheckItem;
    }

}
