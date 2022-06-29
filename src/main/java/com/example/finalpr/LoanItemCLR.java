package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCheck;
import com.example.finalpr.Availabilities.Loan;
import com.example.finalpr.Systems.Systems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LoanItemCLR {

    @FXML
    private Label NoInstallments;

    @FXML
    private Label NoInstallmentsPaid;

    @FXML
    private Label Amount;

    @FXML
    private AnchorPane LoanItem;

    @FXML
    private Label LoanNumber;

    @FXML
    private Label Active;

    public LoanItemCLR() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoanItem.fxml"));
            loader.setController(this);
            LoanItem = loader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoan(Loan loan) {

        LoanNumber.setText(loan.getLoanNumber());
        Amount.setText(loan.getAmount()+"$");
        NoInstallments.setText(loan.getNumberOfInstallments()+"");
        NoInstallmentsPaid.setText(loan.getNumberOfInstallmentsPaid()+"");
        Active.setText(loan.isActive()+"");

    }

    public Node getView() {
        return LoanItem;
    }

}
