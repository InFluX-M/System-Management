package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.*;
import com.example.finalpr.MYSQL.CurrentBankAccounts;
import com.example.finalpr.MYSQL.GoodLoanBankAccounts;
import com.example.finalpr.MYSQL.People;
import com.example.finalpr.MYSQL.SavingBankAccounts;
import javafx.fxml.Initializable;

import java.sql.SQLException;
import java.util.ArrayList;

public class BankSystem {

    private static BankSystem singletonBankSystem;
    private BankAccount nowBankAccount;
    private ArrayList<CurrentAccount> currentBankAccounts;
    private ArrayList<SavingAccount> savingAccounts;
    private ArrayList<GoodLoanAccount> goodLoanAccounts;

    private BankSystem(){
        this.savingAccounts = new ArrayList<>();
        this.goodLoanAccounts = new ArrayList<>();
        this.currentBankAccounts = new ArrayList<>();
        singletonBankSystem = this;
    }

    public boolean loadBankAccount() throws SQLException {

        Boolean valid1 = CurrentBankAccounts.loadCurrentBankAccounts();
        Boolean valid2 = SavingBankAccounts.loadSavingBankAccounts();
        Boolean valid3 = GoodLoanBankAccounts.loadGoodLoanBankAccounts();

        return valid1 && valid2 && valid3;
    }

    public static BankSystem getInstanceBankSystem(){

        if(singletonBankSystem == null){
            singletonBankSystem = new BankSystem();
        }

        return singletonBankSystem;
    }

    public static BankSystem getSingletonBankSystem() {
        return singletonBankSystem;
    }

    public static void setSingletonBankSystem(BankSystem singletonBankSystem) {
        BankSystem.singletonBankSystem = singletonBankSystem;
    }

    public BankAccount getNowBankAccount() {
        return nowBankAccount;
    }

    public void setNowBankAccount(BankAccount nowBankAccount) {
        this.nowBankAccount = nowBankAccount;
    }

    public ArrayList<CurrentAccount> getCurrentBankAccounts() {
        return currentBankAccounts;
    }

    public void setCurrentBankAccounts(ArrayList<CurrentAccount> currentBankAccounts) {
        this.currentBankAccounts = currentBankAccounts;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(ArrayList<SavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public ArrayList<GoodLoanAccount> getGoodLoanAccounts() {
        return goodLoanAccounts;
    }

    public void setGoodLoanAccounts(ArrayList<GoodLoanAccount> goodLoanAccounts) {
        this.goodLoanAccounts = goodLoanAccounts;
    }

    public boolean loginBankAccount(String accountNumber, String ownerID){

        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.addAll(currentBankAccounts);
        bankAccounts.addAll(savingAccounts);
        bankAccounts.addAll(goodLoanAccounts);

        for(BankAccount bankAccount : bankAccounts){
            if(accountNumber.equals(bankAccount.getAccountNumber()) && ownerID.equals(bankAccount.getOwnerID())){
                this.nowBankAccount = bankAccount;
                return true;
            }
        }
        return false;
    }

    public String getAccountNumber(){

        int i=1;
        i+=currentBankAccounts.size();
        i+=savingAccounts.size();
        i+=goodLoanAccounts.size();

        return Integer.toString(i);
    }

    public boolean addCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccounts.insertCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(), currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        return currentBankAccounts.add(currentAccount);
    }
    public boolean addSavingAccount(SavingAccount savingAccount){
        SavingBankAccounts.insertSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(), savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(), savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        return savingAccounts.add(savingAccount);
    }
    public boolean addGoodLoanAccount(GoodLoanAccount goodLoanAccount){
        GoodLoanBankAccounts.insertGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(), goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
        return goodLoanAccounts.add(goodLoanAccount);
    }

}
