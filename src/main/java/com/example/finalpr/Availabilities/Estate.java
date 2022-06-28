package com.example.finalpr.Availabilities;

import java.io.Serializable;
import java.time.LocalDate;

public class Estate implements Serializable {

    private String documentRegistrationCode;
    private String ownerID;
    private String address;
    private LocalDate date;
    private double cost;

    public Estate(String documentRegistrationCode, String ownerID, String addressEstate, LocalDate date, double cost) {
        this.documentRegistrationCode = documentRegistrationCode;
        this.ownerID = ownerID;
        this.address = addressEstate;
        this.date = date;
        this.cost = cost;
    }

    public String getDocumentRegistrationCode() {
        return documentRegistrationCode;
    }
    public void setDocumentRegistrationCode(String documentRegistrationCode) {
        this.documentRegistrationCode = documentRegistrationCode;
    }

    public String getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getAddressEstate() {
        return address;
    }
    public void setAddressEstate(String addressEstate) {
        this.address = addressEstate;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}
