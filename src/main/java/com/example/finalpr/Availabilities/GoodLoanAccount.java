package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class GoodLoanAccount extends BankAccount{

    private BankCard bankCard;

    public GoodLoanAccount(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point, BankCard bankCard) {
        super(accountNumber, ownerID, balance, dateCreate, point);
        this.bankCard = bankCard;
    }

    public BankCard getBankCard() {
        return bankCard;
    }
    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }
}
