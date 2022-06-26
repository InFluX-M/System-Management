package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class BankCheck {

    private String checkNumber;
    private String accountNumberSender;
    private String accountNumberReceiver;
    private double amount;
    private LocalDate dateRegister;
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

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getAccountNumberSender() {
        return accountNumberSender;
    }
    public void setAccountNumberSender(String accountNumberSender) {
        this.accountNumberSender = accountNumberSender;
    }

    public String getAccountNumberReceiver() {
        return accountNumberReceiver;
    }
    public void setAccountNumberReceiver(String accountNumberReceiver) {
        this.accountNumberReceiver = accountNumberReceiver;
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
    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

}
