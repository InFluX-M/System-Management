package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class BankCheck {

    private final String checkNumber;
    private final String accountNumberSender;
    private final String accountNumberReceiver;
    private double amount;
    private final LocalDate dateRegister;
    private boolean passed;

    public BankCheck(String checkNumber, String accountNumberSender, String accountNumberReceiver, double amount, LocalDate dateRegister){
        this.checkNumber = checkNumber;
        this.accountNumberSender = accountNumberSender;
        this.accountNumberReceiver = accountNumberReceiver;
        this.amount = amount;
        this.dateRegister = dateRegister;
        this.passed = false;
    }

    public boolean isPassed() {
        return passed;
    }
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getAccountNumberSender() {
        return accountNumberSender;
    }

    public String getAccountNumberReceiver() {
        return accountNumberReceiver;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

}
