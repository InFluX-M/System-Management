package com.example.finalpr;

import com.example.finalpr.Availabilities.CurrentAccount;
import com.example.finalpr.Systems.BankSystem;
import com.example.finalpr.Systems.CivilRegistrationSystem;
import com.example.finalpr.Systems.DocumentRegistrationSystem;
import com.example.finalpr.Systems.Systems;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class HelloApplication extends Application {

    static public BankSystem bankSystem = BankSystem.getInstanceBankSystem();
    static public CivilRegistrationSystem civilRegistrationSystem = CivilRegistrationSystem.getInstanceCivilRegistrationSystem();
    static public DocumentRegistrationSystem documentRegistrationSystem = DocumentRegistrationSystem.getInstanceDocumentRegistrationSystem();
    static public Systems systems;

    static {
        try {
            systems = Systems.getInstanceSystems(bankSystem, civilRegistrationSystem, documentRegistrationSystem);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {


        File file = new File("Date.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream dataOutputStream = new ObjectOutputStream(fileOutputStream);
        dataOutputStream.writeObject(LocalDate.of(2022,8,20));
        dataOutputStream.close();
        fileOutputStream.close();

        Systems.localDate = LocalDate.of(2022,8,20);

        civilRegistrationSystem.LoadPeople();
        documentRegistrationSystem.loadEstates();
        bankSystem.loadBankAccount();


        System.out.println(Systems.localDate);

        Thread thread1 = new Thread(systems, "Management Date");
        thread1.start();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 450);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch();}

}
