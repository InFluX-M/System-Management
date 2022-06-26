package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class SavingAccount extends BankAccount{

    private double bankInterestPercentage;
    private BankInterestPercentage kindBankInterestPercentage;
    private int designatedTime;

    public SavingAccount(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point, BankInterestPercentage kindBankInterestPercentage) {
        super(accountNumber, ownerID, balance, dateCreate, point);

        this.kindBankInterestPercentage = kindBankInterestPercentage;

        if(this.kindBankInterestPercentage == BankInterestPercentage.LONG_TERM){
            this.bankInterestPercentage = 30;
            this.designatedTime = 30;
        }
        else if(this.kindBankInterestPercentage == BankInterestPercentage.SHORT_TERM){
            this.bankInterestPercentage = 10;
            this.designatedTime = 10;
        }
        else if(this.kindBankInterestPercentage == BankInterestPercentage.SPECIAL){
            this.bankInterestPercentage = 50;
            this.designatedTime = 50;
        }

    }

    public double getBankInterestPercentage() {
        return bankInterestPercentage;
    }
    public void setBankInterestPercentage(double bankInterestPercentage) {
        this.bankInterestPercentage = bankInterestPercentage;
    }

    public BankInterestPercentage getKindBankInterestPercentage() {
        return kindBankInterestPercentage;
    }
    public void setKindBankInterestPercentage(BankInterestPercentage kindBankInterestPercentage) {
        this.kindBankInterestPercentage = kindBankInterestPercentage;
    }

    public int getDesignatedTime() {
        return designatedTime;
    }
    public void setDesignatedTime(int designatedTime) {
        this.designatedTime = designatedTime;
    }
}
