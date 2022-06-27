package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCard;
import com.example.finalpr.Availabilities.CurrentAccount;
import com.example.finalpr.Availabilities.GoodLoanAccount;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;

public class CardRegistrationPanelCLR implements Initializable {

    @FXML
    private Label CVV2;

    @FXML
    private Label CardNumber;

    @FXML
    private Label ExpirationDate;

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

        if(bankSystem.getNowBankAccount() instanceof CurrentAccount currentAccount){

            if(currentAccount.getBankCard()==null){
                String[] inf = bankSystem.getBankCardInformation().split(",");
                String cardNumber = inf[0];
                LocalDate expirationDate = LocalDate.now();
                String CVV2 = inf[1];

                BankCard bankCard = new BankCard(cardNumber, expirationDate, CVV2);
                bankSystem.addBankCard(bankCard);
            }

            CardNumber.setText("Card Number: "+currentAccount.getBankCard().getCardNumber());
            CVV2.setText("CVV2: " + currentAccount.getBankCard().getCVV2());
            ExpirationDate.setText("EX-Date: "+currentAccount.getBankCard().getExpirationDate()+"");

        }
        else if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount goodLoanAccount) {

            if (goodLoanAccount.getBankCard() == null) {
                String[] inf = bankSystem.getBankCardInformation().split(",");
                String cardNumber = inf[0];
                LocalDate expirationDate = LocalDate.now();
                String CVV2 = inf[1];

                BankCard bankCard = new BankCard(cardNumber, expirationDate, CVV2);
                bankSystem.addBankCard(bankCard);
            }

            CardNumber.setText(goodLoanAccount.getBankCard().getCardNumber());
            CVV2.setText(goodLoanAccount.getBankCard().getCVV2());
            ExpirationDate.setText(goodLoanAccount.getBankCard().getExpirationDate() + "");
        }
    }

}
