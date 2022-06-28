package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.*;
import com.example.finalpr.MYSQL.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.bankSystem;

public class Systems implements Runnable{

    public static LocalDate localDate;

    private BankSystem bankSystem;
    private CivilRegistrationSystem civilRegistrationSystem;
    private DocumentRegistrationSystem documentRegistrationSystem;
    private static Systems singletonSystems;

    public Systems(BankSystem bankSystem, CivilRegistrationSystem civilRegistrationSystem, DocumentRegistrationSystem documentRegistrationSystem) throws IOException, ClassNotFoundException {
        this.bankSystem = bankSystem;
        this.civilRegistrationSystem = civilRegistrationSystem;
        this.documentRegistrationSystem = documentRegistrationSystem;

        File file = new File("Date.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        localDate = (LocalDate) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

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

    public boolean withdrawal(double amount){

        double money = civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().getMoney();
        money += amount;
        civilRegistrationSystem.searchPerson(bankSystem.getNowBankAccount().getOwnerID()).getWallet().setMoney(money);
        Wallets.updateWallets(bankSystem.getNowBankAccount().getOwnerID(), money);

        if(bankSystem.getNowBankAccount() instanceof CurrentAccount){
            CurrentAccount currentAccount = (CurrentAccount) bankSystem.getNowBankAccount();
            currentAccount.setBalance(currentAccount.getBalance()-amount);
            return CurrentBankAccounts.updateCurrentBankAccounts(currentAccount.getAccountNumber(), currentAccount.getOwnerID(),
                    currentAccount.getBalance(), currentAccount.getDateCreate(), currentAccount.getPoint());
        }
        else if(bankSystem.getNowBankAccount() instanceof SavingAccount){
            SavingAccount savingAccount = (SavingAccount) bankSystem.getNowBankAccount();
            savingAccount.setBalance(savingAccount.getBalance()-amount);
            return SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    savingAccount.getBalance(), savingAccount.getDateCreate(), savingAccount.getPoint(),
                    savingAccount.getBankInterestPercentage(), savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else if(bankSystem.getNowBankAccount() instanceof GoodLoanAccount){
            GoodLoanAccount goodLoanAccount = (GoodLoanAccount) bankSystem.getNowBankAccount();
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

                double installment = (loan.getAmount() * (1.0+0.1)) / (1.0*loan.getNumberOfInstallments());
                double money = civilRegistrationSystem.searchPerson(bankAccount.getOwnerID()).getWallet().getMoney() - installment;
                civilRegistrationSystem.searchPerson(bankAccount.getOwnerID()).getWallet().setMoney(money);
                Wallets.updateWallets(bankAccount.getOwnerID(), money);
                boolean active;
                if(loan.getNumberOfInstallments() > loan.getNumberOfInstallmentsPaid()) active = true;
                else active = false;
                loan.setActive(active);
                loan.setNumberOfInstallmentsPaid(loan.getNumberOfInstallmentsPaid()+1);
                Loans.updateLoan(loan.getLoanNumber(), loan.getAmount(), loan.getNumberOfInstallments(),
                        loan.getNumberOfInstallmentsPaid(), loan.isActive(), bankAccount.getAccountNumber());
            }
        }

        return true;
    }

    public boolean getSavingMoney(){

        SavingAccount savingAccount = (SavingAccount) bankSystem.getNowBankAccount();
        LocalDate DateDesignated = savingAccount.getDateCreate().plusDays(savingAccount.getDesignatedTime());

        if(localDate.compareTo(DateDesignated) > 0){
            double money = civilRegistrationSystem.searchPerson(savingAccount.getOwnerID()).getWallet().getMoney();
            money += savingAccount.getBalance()+(savingAccount.getBalance()*savingAccount.getBankInterestPercentage()/100);

            civilRegistrationSystem.searchPerson(savingAccount.getOwnerID()).getWallet().setMoney(money);
            Wallets.updateWallets(savingAccount.getOwnerID(), money);
            SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    0.0, savingAccount.getDateCreate(), savingAccount.getPoint(), savingAccount.getBankInterestPercentage(),
                    savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }
        else{
            double money = civilRegistrationSystem.searchPerson(savingAccount.getOwnerID()).getWallet().getMoney();
            civilRegistrationSystem.searchPerson(savingAccount.getOwnerID()).getWallet().setMoney(money);
            Wallets.updateWallets(savingAccount.getOwnerID(), money);
            SavingBankAccounts.updateSavingBankAccounts(savingAccount.getAccountNumber(), savingAccount.getOwnerID(),
                    0.0, savingAccount.getDateCreate(), savingAccount.getPoint(), savingAccount.getBankInterestPercentage(),
                    savingAccount.getKindBankInterestPercentage(), savingAccount.getDesignatedTime());
        }

        return true;

    }

    @Override
    public void run() {

        try {
            Thread.sleep(3000000);
            changeDay();
            paidInstallmentsBankAccount();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean changeDay() throws IOException {
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

        File file = new File("Date.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream dataOutputStream = new ObjectOutputStream(fileOutputStream);
        dataOutputStream.writeObject(BankSystem.localDate);
        dataOutputStream.close();
        fileOutputStream.close();

        return true;
    }
}
