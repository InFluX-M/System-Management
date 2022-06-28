package com.example.finalpr;

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
    public void start(Stage stage) throws IOException, SQLException {

//        File file1 = new File("Date.txt");
//        FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
//        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
//        objectOutputStream1.writeObject(LocalDate.of(2022, 6, 28));
//        objectOutputStream1.close();
//        fileOutputStream1.close();

        if(civilRegistrationSystem.LoadPeople() && documentRegistrationSystem.loadEstates() && bankSystem.loadBankAccount()) {

            Thread thread1 = new Thread(systems, "Management Date");
            thread1.start();


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 340, 450);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }

    }

    public static void main(String[] args) {launch();}
}
