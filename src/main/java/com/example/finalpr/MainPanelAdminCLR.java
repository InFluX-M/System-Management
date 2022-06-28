package com.example.finalpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.*;

public class MainPanelAdminCLR implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image img1 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\1.png");
        Image img2 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\2.jpg");
        Image img3 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\3.png");
        Image img4 = new Image("C:\\Users\\Laptopkaran\\Desktop\\Photoo\\4.png");

        icon1.setImage(img1);
        icon2.setImage(img2);
        icon3.setImage(img3);
        icon4.setImage(img4);

        label3.setText("Number of BankAccount Registered: "+bankSystem.getAccountNumber());
        label2.setText("Number of Estate Registered: "+documentRegistrationSystem.getEstates().size()+"");
        label1.setText("Number of Person Registered: "+civilRegistrationSystem.getPeople().size()+"");
        date.setText(LocalDate.now().toString());
        date.setTextAlignment(TextAlignment.CENTER);
    }

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label date;

    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView icon4;

    @FXML
    void BankSystem(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankSystemPage.fxml")));
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
    void CivilRegistrationSystem(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CivilRegistrationSystemPage.fxml")));
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
    void DocumentRegistrationSystem(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DocumentRegistrationSystemPage.fxml")));
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
    void LogOut(MouseEvent event) {

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
