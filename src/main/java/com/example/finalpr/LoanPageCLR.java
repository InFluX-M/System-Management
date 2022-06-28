package com.example.finalpr;

import com.example.finalpr.Availabilities.Loan;
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
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;
import static com.example.finalpr.HelloApplication.systems;

public class LoanPageCLR implements Initializable {


    @FXML
    private TextField Amount;

    @FXML
    private Label LoanNumber;

    @FXML
    private JFXComboBox<Integer> InstallmentsPaid;

    @FXML
    private Label labelRegister;

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
    void register(MouseEvent event) {

        String loanNumber = bankSystem.getLoanNumber();
        double amount = Double.parseDouble(Amount.getText());
        int numberOfInstallments = InstallmentsPaid.getValue();
        int numberOfInstallmentsPaid = 0;
        boolean active = true;

        Loan loan = new Loan(loanNumber, amount, numberOfInstallments, active);
        systems.addLoan(loan);
        labelRegister.setText("Loan Registered Successfully... :)");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InstallmentsPaid.getItems().addAll(8,16);
        LoanNumber.setText(bankSystem.getLoanNumber());
    }
}
