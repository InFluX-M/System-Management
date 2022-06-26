package com.example.finalpr.Availabilities;

import java.time.LocalDate;

public class BankCard {

    private String cardNumber;
    private LocalDate expirationDate;
    private String CVV2;

    public BankCard(String cardNumber, LocalDate expirationDate, String CVV2) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVV2 = CVV2;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCVV2() {
        return CVV2;
    }
    public void setCVV2(String CVV2) {
        this.CVV2 = CVV2;
    }
}
