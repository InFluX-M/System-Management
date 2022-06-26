package com.example.finalpr.Exceptions;

import com.example.finalpr.Availabilities.BankInterestPercentage;

public class InvalidType extends Exception{

    public InvalidType(String message){
        super(message);
    }

    static void validateBankInterestPercentage(BankInterestPercentage bankInterestPercentage) throws InvalidType {
        if(bankInterestPercentage != BankInterestPercentage.SPECIAL &&
            bankInterestPercentage != BankInterestPercentage.LONG_TERM &&
            bankInterestPercentage != BankInterestPercentage.SHORT_TERM) {

            throw new InvalidType("Invalid Bank Interest Percentage Kind");
        }
    }

}
