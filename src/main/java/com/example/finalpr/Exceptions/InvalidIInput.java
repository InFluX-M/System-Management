package com.example.finalpr.Exceptions;

import static com.example.finalpr.HelloApplication.*;

public class InvalidIInput extends Exception{

    public InvalidIInput(String message){
        super(message);
    }

    public static void validateAccountNumber(String accountNumber) throws InvalidIInput{
        if(bankSystem.searchBankAccount(accountNumber) == null) throw new InvalidIInput("Invalid Input AccountNumber");
    }

    public static void validateOwnerID(String ownerID) throws InvalidIInput{
        if(civilRegistrationSystem.searchPerson(ownerID) == null) throw new InvalidIInput("Invalid Input OwnerID");
    }

    public static void validateDocumentRegistrationCode(String documentRegistrationCode) throws InvalidIInput{
        if(documentRegistrationSystem.searchEstate(documentRegistrationCode) == null) throw new InvalidIInput("Invalid Input DocumentRegistrationCode");
    }

}
