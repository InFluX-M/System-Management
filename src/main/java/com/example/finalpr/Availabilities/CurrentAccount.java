package com.example.finalpr.Availabilities;

import java.time.LocalDate;
import java.util.ArrayList;

public class CurrentAccount extends BankAccount{

    private BankCard bankCard;
    private ArrayList<BankCheck> checksReceived;
    private ArrayList<BankCheck> checksSent;

    public CurrentAccount(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point, BankCard bankCard) {
        super(accountNumber, ownerID, balance, dateCreate, point);
        this.bankCard = bankCard;
        this.checksSent = new ArrayList<>();
        this.checksReceived = new ArrayList<>();
    }

    public BankCard getBankCard() {
        return bankCard;
    }
    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public ArrayList<BankCheck> getChecksReceived() {
        return checksReceived;
    }

    public void setChecksReceived(ArrayList<BankCheck> checksReceived) {
        this.checksReceived = checksReceived;
    }

    public ArrayList<BankCheck> getChecksSent() {
        return checksSent;
    }

    public void setChecksSent(ArrayList<BankCheck> checksSent) {
        this.checksSent = checksSent;
    }
}
