package com.example.finalpr;

import com.example.finalpr.Exceptions.InputRequiredFields;
import com.example.finalpr.Exceptions.InvalidProcess;
import com.example.finalpr.Exceptions.InvalidType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.finalpr.HelloApplication.systems;

public class WithdrawalPanelCLR {

    @FXML
    private TextField Amount;

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
    void Withdrawal() {

        try {
            InputRequiredFields.validateAmount(Amount.getText());
            InvalidType.validateMoneyAmount(Amount.getText());

            double amount = Double.parseDouble(Amount.getText());
            InvalidProcess.validateWithdrawal(amount);

            if(systems.withdrawal(amount)){
                status.setText("Withdrawal Successfully... :)");
            }

        } catch (InvalidType e) {

            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Invalid Type... :(");
            errorAlert1.setContentText("Amount Must be Number Type.");
            errorAlert1.showAndWait();

        } catch (InputRequiredFields e) {
            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("Amount Must Be Input.");
            errorAlert1.showAndWait();

        } catch (InvalidProcess e) {

            e.printStackTrace();

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Invalid Withdrawal... :(");
            errorAlert1.setContentText("Account Balance is Less Than the Amount Entered");
            errorAlert1.showAndWait();
        }
    }

}
