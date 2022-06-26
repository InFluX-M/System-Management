package com.example.finalpr.Availabilities;

public class Loan {

    private double amount;
    private int numberOfInstallments;
    private int numberOfInstallmentsPaid;
    private boolean active;

    public Loan(double amount, int numberOfInstallments, boolean active) {
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
    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
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
}
