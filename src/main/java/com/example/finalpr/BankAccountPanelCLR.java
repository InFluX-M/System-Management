package com.example.finalpr;

import com.example.finalpr.Availabilities.GoodLoanAccount;
import com.example.finalpr.Availabilities.SavingAccount;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.bankSystem;

public class BankAccountPanelCLR implements Initializable {

    @FXML
    private ImageView imgC;

    @FXML
    private ImageView imgD;

    @FXML
    private ImageView imgG;

    @FXML
    private ImageView imgP;

    @FXML
    private ImageView imgT;

    @FXML
    private ImageView imgW;

    @FXML
    private ImageView imgL;

    @FXML
    private ImageView imgS;

    @FXML
    private JFXButton CR;

    @FXML
    private JFXButton GC;

    @FXML
    private JFXButton PC;


    @FXML
    void CardRegistration(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CardRegistrationPanel.fxml")));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException ignored) {

        }
    }

    @FXML
    void Deposit(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DepositPanel.fxml")));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException ignored) {

        }
    }

    @FXML
    void GetCheck(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PassCheckPanel.fxml")));
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
    void GiveCheck(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GiveCheckPanel.fxml")));
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
    void Transfer(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TransferPanel.fxml")));
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
    void Withdrawal(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WithdrawalPanel.fxml")));
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
    void Loans(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoanPage.fxml")));
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
    void Statues(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StatusPage.fxml")));
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
    void back(MouseEvent event) {
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

    @FXML
    void exit() {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        Image img1 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Deposit.png");
        Image img2 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Withdrawal.png");
        Image img3 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Transfer.png");
        Image img4 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\CreditCard.png");
        Image img5 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Check.png");
        Image img6 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Check.png");
        Image img7 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\loan.png");
        Image img8 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\check-list.png");

        imgD.setImage(img1);
        imgW.setImage(img2);
        imgT.setImage(img3);
        imgC.setImage(img4);
        imgG.setImage(img6);
        imgP.setImage(img5);
        imgL.setImage(img7);
        imgS.setImage(img8);

        if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount){
            GC.setDisable(true);
            PC.setDisable(true);
        }
        else if(bankSystem.getNowBankAccount() instanceof SavingAccount){
            CR.setDisable(true);
            GC.setDisable(true);
            PC.setDisable(true);
        }

    }
}
