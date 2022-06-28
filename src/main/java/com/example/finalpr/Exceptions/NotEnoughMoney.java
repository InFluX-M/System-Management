package com.example.finalpr.Exceptions;

import javafx.scene.control.Alert;

public class NotEnoughMoney extends Exception{
    public NotEnoughMoney(){
        super("Not Enough Money");
    }

    public static void validate() throws NotEnoughMoney {

        Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);
        errorAlert1.setHeaderText("Not Enough Money... :(");
        errorAlert1.setContentText("Bank Account is not Enough Money for Paid Installments BankAccount.");
        errorAlert1.showAndWait();

        throw new NotEnoughMoney();
    }
}
