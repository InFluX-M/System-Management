package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCheck;
import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.Exceptions.InvalidIInput;
import com.example.finalpr.Exceptions.InvalidType;
import com.example.finalpr.MYSQL.No;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
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
    private DatePicker Date;

    @FXML
    void Give() {

        try {
            InputRequiredFields.validateGiveCheck(AccountNumber.getText(), Amount.getText(), Date.getValue());
            InvalidIInput.validateAccountNumber(AccountNumber.getText());
            InvalidType.validateMoneyAmount(Amount.getText());

            String checkNumber = No.checkNumber;
            String accountNumberSender = bankSystem.getNowBankAccount().getAccountNumber();
            String accountNumberReceiver = AccountNumber.getText();
            double amount = Double.parseDouble(Amount.getText());
            LocalDate dateRegister = Date.getValue();

            BankCheck bankCheck = new BankCheck(checkNumber, accountNumberSender, accountNumberReceiver, amount, dateRegister);

            if(bankSystem.giveCheck(bankCheck)){
                statue.setText("Check Registered Successfully... :)");
            }

        } catch (InputRequiredFields e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("AccountNumberReceiver, Amount, Date Must Be Input.");
            errorAlert1.showAndWait();

        } catch (InvalidType e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Type... :(");
            errorAlert1.setContentText("Amount Must be Number Type.");
            errorAlert1.showAndWait();

        } catch (InvalidIInput e) {

            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Information... :(");
            errorAlert1.setContentText("Input AccountNumberReceiver is not Valid.");
            errorAlert1.showAndWait();
        }


    }

    @FXML
    void back(MouseEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CheckNumber.setText(No.getCheckNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
