package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.*;
import com.example.finalpr.MYSQL.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BankSystem{

    public static LocalDate localDate;

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

    public String getCheckNumber(){

        int i=0;
        for(CurrentAccount currentAccount : currentBankAccounts){
            i+=currentAccount.getChecksSent().size();
        }

        return i+"";
    }

    public BankAccount searchBankAccount(String accountNumber){
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.addAll(currentBankAccounts);
        bankAccounts.addAll(savingAccounts);
        bankAccounts.addAll(goodLoanAccounts);

        for(BankAccount bankAccount : bankAccounts){
            if(accountNumber.equals(bankAccount.getAccountNumber())) return bankAccount;
        }
        return null;
    }

    public String getLoanNumber(){
        int i=1;
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.addAll(currentBankAccounts);
        bankAccounts.addAll(savingAccounts);
        bankAccounts.addAll(goodLoanAccounts);
        for(BankAccount bankAccount : bankAccounts){
            i+=bankAccount.getLoans().size();
        }
        return i+"";
    }

    public String getBankCardInformation(){
        int cardNumber = 0;
        int CVV2 = 0;

        for(CurrentAccount currentAccount : currentBankAccounts){
            if(currentAccount.getBankCard() != null){
                cardNumber++;
                CVV2++;
            }
        }
        for(GoodLoanAccount goodLoanAccount : goodLoanAccounts){
            if(goodLoanAccount.getBankCard() != null){
                cardNumber++;
                CVV2++;
            }
        }

        return (6000000000000000L+cardNumber)+","+(500+CVV2);

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

    public BankAccount getNowBankAccount() {
        return nowBankAccount;
    }

    public ArrayList<CurrentAccount> getCurrentBankAccounts() {
        return currentBankAccounts;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public ArrayList<GoodLoanAccount> getGoodLoanAccounts() {
        return goodLoanAccounts;
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

        int i=0;
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

    public boolean addBankCard(BankCard bankCard){

        if(nowBankAccount instanceof CurrentAccount currentAccount){
            currentAccount.setBankCard(bankCard);
        }
        else if(nowBankAccount instanceof GoodLoanAccount goodLoanAccount){
            goodLoanAccount.setBankCard(bankCard);
        }

        return BankCards.insertBankCard(bankCard.getCardNumber(), bankCard.getExpirationDate(), bankCard.getCVV2(),
                nowBankAccount.getOwnerID(), nowBankAccount.getAccountNumber());

    }

    public boolean giveCheck(BankCheck bankCheck){
        ((CurrentAccount)searchBankAccount(bankCheck.getAccountNumberSender())).getChecksSent().add(bankCheck);
        ((CurrentAccount)searchBankAccount(bankCheck.getAccountNumberReceiver())).getChecksReceived().add(bankCheck);
        return BankChecks.insertBankCheck(bankCheck.getCheckNumber(), bankCheck.getAccountNumberSender(), bankCheck.getAccountNumberReceiver(),
                bankCheck.getAmount(), bankCheck.getDateRegister(), bankCheck.isPassed());
    }

}
