package com.example.finalpr;

import com.example.finalpr.Availabilities.BankInterestPercentage;
import com.example.finalpr.Availabilities.CurrentAccount;
import com.example.finalpr.Availabilities.GoodLoanAccount;
import com.example.finalpr.Availabilities.SavingAccount;
import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.MYSQL.No;
import com.jfoenix.controls.JFXComboBox;
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

import static com.example.finalpr.HelloApplication.bankSystem;

public class CreateNewBankAccountPageCLR implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KindBankAccount.getItems().addAll("CurrentAccount", "SavingAccount", "GoodLoanAccount");
        KindBankInterestPercentage.getItems().addAll("SHORT_TERM", "LONG_TERM", "SPECIAL");
        try {
            AccountNumber.setText("AccountNumber: " + No.getAccountNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private Label AccountNumber;

    @FXML
    private JFXComboBox<String> KindBankAccount;

    @FXML
    private JFXComboBox<String> KindBankInterestPercentage;

    @FXML
    private TextField OwnerID;

    @FXML
    private Label Register;

    @FXML
    void Register() {

        String accountNumber = No.accountNumber;
        String ownerID = OwnerID.getText();
        double balance = 0.0;
        LocalDate dateCreate = LocalDate.now();
        int point = 0;
        String kind = KindBankAccount.getValue();

        try {
            InputRequiredFields.validateCreateNewBankAcc(ownerID, kind);

            switch (kind) {

                case "CurrentAccount" -> {
                    CurrentAccount currentAccount = new CurrentAccount(accountNumber, ownerID, balance, dateCreate, point, null);
                    if (bankSystem.addCurrentBankAccount(currentAccount))
                        Register.setText("BankAccount Registered Successfully... :)");
                }

                case "SavingAccount" -> {
                    BankInterestPercentage kindBankInterestPercentage = null;
                    if (KindBankInterestPercentage.getValue().equals("SHORT_TERM")) {
                        kindBankInterestPercentage = BankInterestPercentage.SHORT_TERM;
                    } else if (KindBankInterestPercentage.getValue().equals("LONG_TERM")) {
                        kindBankInterestPercentage = BankInterestPercentage.LONG_TERM;
                    } else if (KindBankInterestPercentage.getValue().equals("SPECIAL")) {
                        kindBankInterestPercentage = BankInterestPercentage.SPECIAL;
                    }
                    SavingAccount savingAccount = new SavingAccount(accountNumber, ownerID, balance, dateCreate, point, kindBankInterestPercentage);
                    if (bankSystem.addSavingAccount(savingAccount))
                        Register.setText("BankAccount Registered Successfully... :)");

                }

                case "GoodLoanAccount" -> {
                    GoodLoanAccount goodLoanAccount = new GoodLoanAccount(accountNumber, ownerID, balance, dateCreate, point, null);
                    if (bankSystem.addGoodLoanAccount(goodLoanAccount))
                        Register.setText("BankAccount Registered Successfully... :)");
                }
            }

        } catch (InputRequiredFields e) {

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("OwnerID and Kind Field Must Be Input.");
            errorAlert1.showAndWait();

            e.printStackTrace();
        }

    }

    @FXML
    void SignIn(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
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

