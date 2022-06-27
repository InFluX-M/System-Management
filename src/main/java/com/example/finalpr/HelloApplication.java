package com.example.finalpr;

import com.example.finalpr.Systems.BankSystem;
import com.example.finalpr.Systems.CivilRegistrationSystem;
import com.example.finalpr.Systems.DocumentRegistrationSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;


public class HelloApplication extends Application {

    static public BankSystem bankSystem = BankSystem.getInstanceBankSystem();
    static public CivilRegistrationSystem civilRegistrationSystem = CivilRegistrationSystem.getInstanceCivilRegistrationSystem();
    static public DocumentRegistrationSystem documentRegistrationSystem = DocumentRegistrationSystem.getInstanceDocumentRegistrationSystem();

    @Override
    public void start(Stage stage) throws IOException, SQLException, InterruptedException, ClassNotFoundException {

        civilRegistrationSystem.LoadPeople();
        documentRegistrationSystem.loadEstates();
        bankSystem.loadBankAccount();

        Thread thread1 = new Thread(bankSystem, "Management Day BankSystem");
        Thread thread2 = new Thread(civilRegistrationSystem, "Management Day CivilRegistrationSystem");
        Thread thread3 = new Thread(documentRegistrationSystem, "Management Day DocumentRegistrationSystem");
        thread1.start();;
        thread2.start();
        thread3.start();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 450);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch();}

}
