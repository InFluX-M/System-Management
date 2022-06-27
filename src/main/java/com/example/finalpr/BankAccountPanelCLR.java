package com.example.finalpr;

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
import java.util.ResourceBundle;

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
    void CardRegistration(MouseEvent event) {

    }

    @FXML
    void Deposit(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DepositPanel.fxml"));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }
    }

    @FXML
    void GetCheck(MouseEvent event) {

    }

    @FXML
    void PassCheck(MouseEvent event) {

    }

    @FXML
    void Transfer(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TransferPanel.fxml"));
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("WithdrawalPanel.fxml"));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }
    }

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img1 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Deposit.png");
        Image img2 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Withdrawal.png");
        Image img3 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Transfer.png");
        Image img4 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\CreditCard.png");
        Image img5 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Check.png");
        Image img6 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\Check.png");

        imgD.setImage(img1);
        imgW.setImage(img2);
        imgT.setImage(img3);
        imgC.setImage(img4);
        imgG.setImage(img6);
        imgP.setImage(img5);

    }
}
