package com.example.finalpr;

import com.example.finalpr.Systems.BankSystem;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;

public class LoginPageCLR implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboRules.getItems().addAll("Admin", "User");
    }

    @FXML
    private Circle circle1;

    @FXML
    private JFXComboBox<String> comboRules;

    @FXML
    private TextField inputAccountNumber;

    @FXML
    private PasswordField inputOwnerID;

    @FXML
    private Label statueLogin;

    @FXML
    void createNewProfile(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateNewBankAccountPage.fxml"));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }

    }

    @FXML
    void login(MouseEvent event) throws InterruptedException {

        String accountNumber = inputAccountNumber.getText();
        String ownerID = inputOwnerID.getText();
        String rule = comboRules.getValue();

        if(accountNumber.isEmpty() || ownerID.isEmpty() || rule.isEmpty()){
            statueLogin.setText("Input The Required Fields... :(");
        }else{

            if(rule.equals("Admin")){

                if(accountNumber.equals("admin") && ownerID.equals("admin")){

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("MainPanelAdmin.fxml"));
                        Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        s1.setScene(scene);
                        s1.show();
                    }
                    catch (IOException e) {

                    }

                }else{
                    statueLogin.setText("Account Is Not Valid... :(");
                }

            }else if(rule.equals("User")){

                if(bankSystem.loginBankAccount(accountNumber, ownerID)){


                }else{
                    statueLogin.setText("Account Is Not Valid... :(");
                }

            }else{
                statueLogin.setText("Input Invalid Rule... :(");
            }
        }

    }

}
