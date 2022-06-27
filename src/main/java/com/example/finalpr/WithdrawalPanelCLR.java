package com.example.finalpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.finalpr.HelloApplication.systems;

public class WithdrawalPanelCLR {
    @FXML
    private TextField Amount;

    @FXML
    private Label status;

    @FXML
    void Back(MouseEvent event) {
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
    void Withdrawal(MouseEvent event) {
        double amount = Double.parseDouble(Amount.getText());
        systems.withdrawal(amount);
        status.setText("Withdrawal Successfully... :)");
    }

}
