package com.example.finalpr.Exceptions;

public class InputRequiredFields extends Exception{

    public InputRequiredFields(String error){
        super(error);
    }

    public static void validateLogin(String accountNumber, String ownerID, String rule) throws InputRequiredFields {

        if(accountNumber.isEmpty() || ownerID.isEmpty() || rule == null) throw new InputRequiredFields("Input Required Fields");

    }

    public static void validateCreateNewBankAcc(String ownerID, String kind) throws InputRequiredFields {

        if(ownerID.isEmpty() || kind == null) throw new InputRequiredFields("Input Required Fields");

    }

}
