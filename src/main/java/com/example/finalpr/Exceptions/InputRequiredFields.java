package com.example.finalpr.Exceptions;

import java.time.LocalDate;

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

    public static void validateAmount(String amount) throws InputRequiredFields {
        if(amount.isEmpty()) throw new InputRequiredFields("Input Required Fields");
    }

    public static void validateRegisterPerson(String name, String age, String sex) throws InputRequiredFields {

        if(name.isEmpty() || age.isEmpty() || sex == null) throw new InputRequiredFields("Input Required Fields");
    }

    public static void validateEditPerson(String ID, String name, String age, String sex) throws InputRequiredFields {
        if(ID.isEmpty() || name.isEmpty() || age.isEmpty() || sex == null) throw new InputRequiredFields("Input Required Fields");
    }


    public static void validateRegisterEstate(String ownerID, String address, String cost) throws InputRequiredFields {
        if(ownerID.isEmpty() || address.isEmpty() || cost.isEmpty()) throw new InputRequiredFields("Input Required Fields");
    }


    public static void validateEditEstate(String documentRegistrationCode, String ownerID, String address, String cost) throws InputRequiredFields {
        if(documentRegistrationCode.isEmpty() || ownerID.isEmpty() || address.isEmpty() || cost.isEmpty()) throw new InputRequiredFields("Input Required Fields");
    }

    public static void validateGiveCheck(String accountNumberReceiver, String amount, LocalDate localDate) throws InputRequiredFields {

        if(accountNumberReceiver.isEmpty() || amount.isEmpty() || localDate==null) throw new InputRequiredFields("Input Required Fields");

    }
}
