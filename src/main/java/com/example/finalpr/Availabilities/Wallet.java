package com.example.finalpr.Availabilities;

import java.util.ArrayList;

public class Wallet {

    private ArrayList<BankCard> bankCards;
    private double money;

    public Wallet() {
        this.bankCards = new ArrayList<>();
        this.money = 0.0;
    }

    public void setBankCards(ArrayList<BankCard> bankCards) {
        this.bankCards = bankCards;
    }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

}
