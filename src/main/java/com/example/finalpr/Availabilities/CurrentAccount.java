package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class CurrentAccount extends BankAccount{

    private BankCard bankCard;
    private BatchTheCheck batchTheCheck;

    public CurrentAccount(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point, BankCard bankCard, BatchTheCheck batchTheCheck) {
        super(accountNumber, ownerID, balance, dateCreate, point);
        this.bankCard = bankCard;
        this.batchTheCheck = batchTheCheck;
    }

    public BankCard getBankCard() {
        return bankCard;
    }
    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public BatchTheCheck getBatchTheCheck() {
        return batchTheCheck;
    }
    public void setBatchTheCheck(BatchTheCheck batchTheCheck) {
        this.batchTheCheck = batchTheCheck;
    }

}
