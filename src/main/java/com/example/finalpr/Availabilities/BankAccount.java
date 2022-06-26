package com.example.finalpr.Availabilities;

import java.time.LocalDate;
import java.util.ArrayList;

abstract public class BankAccount {

    private String accountNumber;
    private String ownerID;
    private double balance;
    private LocalDate dateCreate;
    private int point;
    private ArrayList<Loan> loans;

    public BankAccount(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point) {
        this.accountNumber = accountNumber;
        this.ownerID = ownerID;
        this.balance = balance;
        this.dateCreate = dateCreate;
        this.point = point;
        this.loans = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }
    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }
}
