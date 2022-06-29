package com.example.finalpr.Exceptions;

import static com.example.finalpr.HelloApplication.bankSystem;
import static com.example.finalpr.HelloApplication.civilRegistrationSystem;

public class InvalidProcess extends Exception{

    public InvalidProcess(String error){
        super(error);
    }

    public static void validateWithdrawal(double amount) throws InvalidProcess {
        if(bankSystem.getNowBankAccount().getBalance() < amount) throw new InvalidProcess("Invalid Withdrawal");
    }

    public static void validateDeposit(double amount) throws InvalidProcess {
        if(civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().getMoney() < amount) throw new InvalidProcess("Invalid Deposit");
    }

    public static void validateTransfer(double amount) throws InvalidProcess {
        if(bankSystem.getNowBankAccount().getBalance() < amount) throw new InvalidProcess("Invalid Transfer");
    }
}
