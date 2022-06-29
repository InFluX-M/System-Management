package com.example.finalpr.Exceptions;

import javafx.scene.control.Alert;

public class NotEnoughMoney extends Exception{
    public NotEnoughMoney(){
        super("Not Enough Money");
    }

    public static void validate() throws NotEnoughMoney {


        throw new NotEnoughMoney();
    }
}
