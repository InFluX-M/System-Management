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


        File file1 = new File("Date.txt");
        FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
        objectOutputStream1.writeObject(BankSystem.localDate);
        objectOutputStream1.close();
        fileOutputStream1.close();
//
//        File file2 = new File("DateBS.txt");
//        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
//        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
//        objectOutputStream2.writeObject(BankSystem.localDate);
//        objectOutputStream2.close();
//        fileOutputStream2.close();
//
//        File file3 = new File("DateDRS.txt");
//        FileOutputStream fileOutputStream3 = new FileOutputStream(file3);
//        ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(fileOutputStream3);
//        objectOutputStream3.writeObject(BankSystem.localDate);
//        objectOutputStream3.close();
//        fileOutputStream3.close();

        civilRegistrationSystem.LoadPeople();
        documentRegistrationSystem.loadEstates();
        bankSystem.loadBankAccount();

        ArrayList<CurrentAccount> b = bankSystem.getCurrentBankAccounts();
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
