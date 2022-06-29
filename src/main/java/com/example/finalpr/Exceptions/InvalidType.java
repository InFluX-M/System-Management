package com.example.finalpr.Exceptions;

import com.example.finalpr.Availabilities.BankInterestPercentage;

public class InvalidType extends Exception{

    public InvalidType(String message){
        super(message);
    }

    public static void validateMoneyAmount(String amount) throws InvalidType{
        try {
            Double.parseDouble(amount);
        }
        catch (NumberFormatException e){
            throw new InvalidType("Number Format Exception");
        }
    }
    public static void validateAge(String age) throws InvalidType{
        try {
            Integer.parseInt(age);
        }
        catch (NumberFormatException e){
            throw new InvalidType("Number Format Exception");
        }
    }


}
