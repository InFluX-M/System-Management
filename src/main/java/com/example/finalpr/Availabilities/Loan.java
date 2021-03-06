package com.example.finalpr.Availabilities;

public class Loan {

    private final String loanNumber;
    private double amount;
    private final int numberOfInstallments;
    private int numberOfInstallmentsPaid;
    private boolean active;

    public Loan(String loanNumber, double amount, int numberOfInstallments, boolean active) {
        this.loanNumber = loanNumber;
        this.amount = amount;
        this.numberOfInstallments = numberOfInstallments;
        this.numberOfInstallmentsPaid = 0;
        this.active = active;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public int getNumberOfInstallmentsPaid() {
        return numberOfInstallmentsPaid;
    }
    public void setNumberOfInstallmentsPaid(int numberOfInstallmentsPaid) {
        this.numberOfInstallmentsPaid = numberOfInstallmentsPaid;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLoanNumber() {
        return loanNumber;
    }
}
