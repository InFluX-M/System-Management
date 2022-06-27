package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.*;
import com.example.finalpr.MYSQL.BankCards;
import com.example.finalpr.MYSQL.CurrentBankAccounts;
import com.example.finalpr.MYSQL.GoodLoanBankAccounts;
import com.example.finalpr.MYSQL.SavingBankAccounts;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class BankSystem implements Runnable{

    public static LocalDate localDate = LocalDate.now();

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

    public boolean loadBankAccount() throws SQLException, IOException, ClassNotFoundException {

        Boolean valid1 = CurrentBankAccounts.loadCurrentBankAccounts();
        Boolean valid2 = SavingBankAccounts.loadSavingBankAccounts();
        Boolean valid3 = GoodLoanBankAccounts.loadGoodLoanBankAccounts();

        File file = new File("DateBS.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        localDate = (LocalDate) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

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

    public boolean changeDay() throws IOException {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth()+1;

        if(day == localDate.lengthOfMonth()+1){
            month++;
            day = 1;
        }
        localDate = LocalDate.of(year, month, day);

        File file = new File("DateBS.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream dataOutputStream = new ObjectOutputStream(fileOutputStream);
        dataOutputStream.writeObject(BankSystem.localDate);
        dataOutputStream.close();
        fileOutputStream.close();

        return true;
    }

    @Override
    public void run() {

        while(true){
            try {

                Thread.sleep(300000);
                changeDay();

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

    }
}
