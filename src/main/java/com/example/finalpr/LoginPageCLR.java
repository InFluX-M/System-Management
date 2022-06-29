package com.example.finalpr;

import com.example.finalpr.Exceptions.InputRequiredFields;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        Image img = new Image("F:\\FinalPr\\Photoo\\data-complexity.png");
        sys.setImage(img);
        comboRules.getItems().addAll("Admin", "User");
    }


    @FXML
    private ImageView sys;

    @FXML
    private JFXComboBox<String> comboRules;

    @FXML
    private TextField inputAccountNumber;

    @FXML
    private PasswordField inputOwnerID;

    @FXML
    void createNewProfile(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateNewBankAccountPage.fxml")));
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
    void login(MouseEvent event){

        String accountNumber = inputAccountNumber.getText();
        String ownerID = inputOwnerID.getText();
        String rule = comboRules.getValue();

        try {

            InputRequiredFields.validateLogin(accountNumber, ownerID, rule);

            if(rule.equals("Admin")){

                if(accountNumber.equals("admin") && ownerID.equals("admin")){

                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPanelAdmin.fxml")));
                        Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        s1.setScene(scene);
                        s1.show();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
                    errorAlert1.setHeaderText("Account Is Not Valid... :(");
                    errorAlert1.setContentText("Input Information May Be Wrong Or You are not Registered in the System.");
                    errorAlert1.showAndWait();
                }

            }
            else if(rule.equals("User")){

                if(bankSystem.loginBankAccount(accountNumber, ownerID)){

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
                else{
                    Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
                    errorAlert1.setHeaderText("Account Is Not Valid... :(");
                    errorAlert1.setContentText("Input Information May Be Wrong Or You are not Registered in the System.");
                    errorAlert1.showAndWait();
                }

            }


        } catch (InputRequiredFields e) {

            Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
            errorAlert1.setHeaderText("Input The Required Fields... :(");
            errorAlert1.setContentText("AccountNumber and OwnerID and Rule Field Must Be Input.");
            errorAlert1.showAndWait();

            e.printStackTrace();
        }

    }

}
