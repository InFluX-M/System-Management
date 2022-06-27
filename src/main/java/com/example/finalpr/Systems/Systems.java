package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.MYSQL.Estates;
import com.example.finalpr.MYSQL.Wallets;

import java.util.ArrayList;

public class Systems {

    private BankSystem bankSystem;
    private CivilRegistrationSystem civilRegistrationSystem;
    private DocumentRegistrationSystem documentRegistrationSystem;
    private static Systems singletonSystems;

    public Systems(BankSystem bankSystem, CivilRegistrationSystem civilRegistrationSystem, DocumentRegistrationSystem documentRegistrationSystem) {
        this.bankSystem = bankSystem;
        this.civilRegistrationSystem = civilRegistrationSystem;
        this.documentRegistrationSystem = documentRegistrationSystem;
    }

    public BankSystem getBankSystem() {
        return bankSystem;
    }
    public void setBankSystem(BankSystem bankSystem) {
        this.bankSystem = bankSystem;
    }

    public CivilRegistrationSystem getCivilRegistrationSystem() {
        return civilRegistrationSystem;
    }
    public void setCivilRegistrationSystem(CivilRegistrationSystem civilRegistrationSystem) {
        this.civilRegistrationSystem = civilRegistrationSystem;
    }

    public DocumentRegistrationSystem getDocumentRegistrationSystem() {
        return documentRegistrationSystem;
    }
    public void setDocumentRegistrationSystem(DocumentRegistrationSystem documentRegistrationSystem) {
        this.documentRegistrationSystem = documentRegistrationSystem;
    }

    public static Systems getInstanceSystems(BankSystem bankSystem, CivilRegistrationSystem civilRegistrationSystem, DocumentRegistrationSystem documentRegistrationSystem){

        if(singletonSystems == null){
            singletonSystems = new Systems(bankSystem, civilRegistrationSystem, documentRegistrationSystem);
        }
        return singletonSystems;
    }

    public boolean deleteEstate(Estate estate){

        double money = civilRegistrationSystem.searchPerson(estate.getOwnerID()).getWallet().getMoney();
        money += estate.getCost();
        civilRegistrationSystem.searchPerson(estate.getOwnerID()).getWallet().setMoney(money);
        return Wallets.updateWallets(estate.getOwnerID(), money);
    }
}
