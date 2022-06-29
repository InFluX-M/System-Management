package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.*;
import com.example.finalpr.Exceptions.NotEnoughMoney;
import com.example.finalpr.MYSQL.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Systems implements Runnable{

    public static LocalDate localDate;

    private final BankSystem bankSystem;
    private final CivilRegistrationSystem civilRegistrationSystem;
    private final DocumentRegistrationSystem documentRegistrationSystem;
    private static Systems singletonSystems;

    public Systems(BankSystem bankSystem, CivilRegistrationSystem civilRegistrationSystem, DocumentRegistrationSystem documentRegistrationSystem) throws IOException, ClassNotFoundException {

        this.bankSystem = bankSystem;
        this.civilRegistrationSystem = civilRegistrationSystem;
        this.documentRegistrationSystem = documentRegistrationSystem;

        try {
            localDate = No.getDate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public BankSystem getBankSystem() {
        return bankSystem;
    }

    public static Systems getInstanceSystems(BankSystem bankSystem, CivilRegistrationSystem civilRegistrationSystem, DocumentRegistrationSystem documentRegistrationSystem) throws IOException, ClassNotFoundException {

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

        if(bankSystem.getNowBankAccount() instanceof CurrentAccount currentAccount){
            currentAccount.setBalance(currentAccount.getBalance()+amount);
            return CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        }
        else if(bankSystem.getNowBankAccount() instanceof SavingAccount savingAccount){
            savingAccount.setBalance(savingAccount.getBalance()+amount);
            return SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(),
                    savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount goodLoanAccount){
            goodLoanAccount.setBalance(goodLoanAccount.getBalance()+amount);
            return GoodLoanBankAccounts.updateGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(),
                    goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
        }

        return false;
    }

    public boolean withdrawal(double amount){

        double money = civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().getMoney();
        money += amount;
        civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().setMoney(money);
        Wallets.updateWallets(bankSystem.getNowBankAccount().getOwnerID(), money);

        if(bankSystem.getNowBankAccount() instanceof CurrentAccount currentAccount){
            currentAccount.setBalance(currentAccount.getBalance()-amount);
            return CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        }
        else if(bankSystem.getNowBankAccount() instanceof SavingAccount savingAccount){
            savingAccount.setBalance(savingAccount.getBalance()-amount);
            return SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(),
                    savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount goodLoanAccount){
            goodLoanAccount.setBalance(goodLoanAccount.getBalance()-amount);
            return GoodLoanBankAccounts.updateGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(),
                    goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
        }

        return false;
    }

    public boolean transfer(double amount, String accountNumberR){

        if(bankSystem.getNowBankAccount() instanceof CurrentAccount currentAccount){
            currentAccount.setBalance(currentAccount.getBalance()-amount);
            CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        }
        else if(bankSystem.getNowBankAccount() instanceof SavingAccount savingAccount){
            savingAccount.setBalance(savingAccount.getBalance()-amount);
            SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(),
                    savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount goodLoanAccount){
            goodLoanAccount.setBalance(goodLoanAccount.getBalance()-amount);
            GoodLoanBankAccounts.updateGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(),
                    goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
        }

        BankAccount bankAccountR = bankSystem.searchBankAccount(accountNumberR);

        if(bankAccountR instanceof CurrentAccount currentAccount){
            currentAccount.setBalance(currentAccount.getBalance()+amount);
            return CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        }
        else if(bankAccountR instanceof SavingAccount savingAccount){
            savingAccount.setBalance(savingAccount.getBalance()+amount);
            return SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(),
                    savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else if(bankAccountR instanceof GoodLoanAccount goodLoanAccount){
            goodLoanAccount.setBalance(goodLoanAccount.getBalance()+amount);
            return GoodLoanBankAccounts.updateGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(),
                    goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
        }

        return false;
    }

    public boolean addLoan(Loan loan){
        double money = civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().getMoney();
        money += loan.getAmount();
        civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().setMoney(money);
        Wallets.updateWallets(bankSystem.getNowBankAccount().getOwnerID(), money);

        bankSystem.getNowBankAccount().getLoans().add(loan);
        return Loans.insertLoan(loan.getLoanNumber(), loan.getAmount(), loan.getNumberOfInstallments(), loan.getNumberOfInstallmentsPaid(),
                loan.isActive(), bankSystem.getNowBankAccount().getAccountNumber());

    }

    public boolean paidInstallmentsBankAccount(){

        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.addAll(bankSystem.getCurrentBankAccounts());
        bankAccounts.addAll(bankSystem.getSavingAccounts());
        bankAccounts.addAll(bankSystem.getGoodLoanAccounts());

        for(BankAccount bankAccount : bankAccounts){
            for(Loan loan : bankAccount.getLoans()){

                if(loan.isActive()){
                    double installment = (loan.getAmount() * (1.0+0.1)) / (1.0*loan.getNumberOfInstallments());
                    double money = civilRegistrationSystem.searchPerson(bankAccount.getOwnerID()).getWallet().getMoney() - installment;

                    if(money >= 0){
                        civilRegistrationSystem.searchPerson(bankAccount.getOwnerID()).getWallet().setMoney(money);
                        Wallets.updateWallets(bankAccount.getOwnerID(), money);
                        loan.setNumberOfInstallmentsPaid(loan.getNumberOfInstallmentsPaid()+1);
                        boolean active = loan.getNumberOfInstallments() > loan.getNumberOfInstallmentsPaid();
                        loan.setActive(active);
                        Loans.updateLoan(loan.getLoanNumber(), loan.getAmount(), loan.getNumberOfInstallments(),
                                loan.getNumberOfInstallmentsPaid(), loan.isActive(), bankAccount.getAccountNumber());
                    }
                    else{
                        bankAccount.setPoint(bankAccount.getPoint()+1);

                        if(bankAccount instanceof CurrentAccount currentAccount){
                            CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
                        }
                        else if(bankAccount instanceof SavingAccount savingAccount){
                            SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(), savingAccount.getBankInterestPercentage(),
                                    savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
                        }
                        else if(bankAccount instanceof GoodLoanAccount goodLoanAccount){
                            GoodLoanBankAccounts.updateGoodLoanBankAccounts(goodLoanAccount.getAccountNumber(), goodLoanAccount.getOwnerID(),
                                    goodLoanAccount.getBalance(), goodLoanAccount.getDateCreate(), goodLoanAccount.getPoint());
                        }

                        try {
                            NotEnoughMoney.validate();
                        } catch (NotEnoughMoney e) {

                            e.printStackTrace();
                        }

                    }
                }
            }
        }

        return true;
    }

    public boolean getSavingMoney(){

        SavingAccount savingAccount = (SavingAccount) bankSystem.getNowBankAccount();
        LocalDate DateDesignated = savingAccount.getDateCreate().plusDays(savingAccount.getDesignatedTime());

        double money = civilRegistrationSystem.searchPerson(savingAccount.getOwnerID()).getWallet().getMoney()+savingAccount.getBalance();
        if(localDate.compareTo(DateDesignated) > 0){
            money += (1.0*savingAccount.getBalance()*savingAccount.getBankInterestPercentage()/100);

        }
        civilRegistrationSystem.searchPerson(savingAccount.getOwnerID()).getWallet().setMoney(money);
        boolean v = Wallets.updateWallets(savingAccount.getOwnerID(), money);
        return v && SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                0.0, savingAccount.getDateCreate(), savingAccount.getPoint(), savingAccount.getBankInterestPercentage(),
                savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());


    }

    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(1000);
                changeDay();
                paidInstallmentsBankAccount();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void changeDay() throws IOException {

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth()+1;

        if(day == localDate.lengthOfMonth()+1){
            month++;
            day = 1;
            if(month == 13){
                year++;
                month = 1;
            }
        }
        localDate = LocalDate.of(year, month, day);

        BankSystem.localDate = localDate;
        CivilRegistrationSystem.localDate = localDate;
        DocumentRegistrationSystem.localDate = localDate;

        No.changeDate(localDate);

    }
}
