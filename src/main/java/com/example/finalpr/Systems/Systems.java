package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.CurrentAccount;
import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.Availabilities.GoodLoanAccount;
import com.example.finalpr.Availabilities.SavingAccount;
import com.example.finalpr.MYSQL.*;

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

    public boolean deposit(double amount){

        double money = civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().getMoney();
        money -= amount;
        civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().setMoney(money);
        Wallets.updateWallets(bankSystem.getNowBankAccount().getOwnerID(), money);

        if(bankSystem.getNowBankAccount() instanceof CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) bankSystem.getNowBankAccount();
            currentAccount.setBalance(currentAccount.getBalance()+amount);
            return CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        }
        else if(bankSystem.getNowBankAccount() instanceof SavingAccount){
            SavingAccount savingAccount = (SavingAccount) bankSystem.getNowBankAccount();
            savingAccount.setBalance(savingAccount.getBalance()+amount);
            return SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(),
                    savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount){
            GoodLoanAccount goodLoanAccount = (GoodLoanAccount) bankSystem.getNowBankAccount();
            goodLoanAccount.setBalance(goodLoanAccount.getBalance()+amount);
            return GoodLoanBankAccounts.updateGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(),
                    goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
        }

        return false;
    }

}
