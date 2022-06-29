package com.example.finalpr.Exceptions;

public class NotEnoughMoney extends Exception{
    public NotEnoughMoney(){
        super("Not Enough Money");
    }

    public static void validate() throws NotEnoughMoney {
        throw new NotEnoughMoney();
    }
}
