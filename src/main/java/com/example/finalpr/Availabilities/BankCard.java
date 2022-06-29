package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class BankCard {

    private final String cardNumber;
    private final LocalDate expirationDate;
    private final String CVV2;

    public BankCard(String cardNumber, LocalDate expirationDate, String CVV2) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVV2 = CVV2;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCVV2() {
        return CVV2;
    }
}
