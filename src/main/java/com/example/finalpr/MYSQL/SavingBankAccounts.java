package com.example.finalpr.MYSQL;

import com.example.finalpr.Availabilities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.bankSystem;

public class SavingBankAccounts {

    static public boolean insertSavingBankAccounts(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point, double bankInterestPercentage, BankInterestPercentage kindBankInterestPercentage, int designatedTime){

        int kind = 0;
        if(kindBankInterestPercentage == BankInterestPercentage.SHORT_TERM) kind = 0;
        else if(kindBankInterestPercentage == BankInterestPercentage.LONG_TERM) kind = 1;
        else if(kindBankInterestPercentage == BankInterestPercentage.SPECIAL) kind = 2;

        String sqlCMD = String.format("INSERT INTO savingbankaccount (accountNumber, ownerID, balance, dateCreate, point, bankInterestPercentage, kindBankInterestPercentage, designatedTime) VALUES ('%s', '%s', %f, '"+dateCreate+"', %d, %f, %d, %d)",accountNumber, ownerID, balance, point, bankInterestPercentage, kind, designatedTime);
        return MySQL.executeSQL(sqlCMD);

    }

    static public boolean loadSavingBankAccounts() throws SQLException {

        String sqlCMD = "SELECT accountNumber, ownerID, balance, dateCreate, point, kindBankInterestPercentage FROM savingbankaccount";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet!=null;
        while(resultSet.next()){

            String accountNumber = resultSet.getString("accountNumber");
            String ownerID = resultSet.getString("ownerID");
            double balance = resultSet.getDouble("balance");
            LocalDate dateCreate = resultSet.getDate("dateCreate").toLocalDate();
            int point = resultSet.getInt("point");
            int kind = resultSet.getInt("kindBankInterestPercentage");
            BankInterestPercentage kindBankInterestPercentage = null;
            if(kind == 0) kindBankInterestPercentage = BankInterestPercentage.SHORT_TERM;
            else if(kind == 1) kindBankInterestPercentage = BankInterestPercentage.LONG_TERM;
            else if(kind == 2) kindBankInterestPercentage = BankInterestPercentage.SPECIAL;

            ArrayList<Loan> loans = new ArrayList<>();
            String sqlCMD2 = String.format("SELECT loanNumber, amount, numberOfInstallments, numberOfInstallmentsPaid, active FROM loans WHERE accountNumber = '%s'", accountNumber);
            ResultSet resultSet2 = MySQL.executeQuery(sqlCMD2);

            assert resultSet2!=null;
            while(resultSet2.next()){

                String loanNumber = resultSet2.getString("loanNumber");
                double amount = resultSet2.getDouble("amount");
                int numberOfInstallments = resultSet2.getInt("numberOfInstallments");
                int numberOfInstallmentsPaid = resultSet2.getInt("numberOfInstallmentsPaid");
                boolean active = resultSet2.getBoolean("active");

                Loan loan = new Loan(loanNumber, amount, numberOfInstallments, active);
                loan.setNumberOfInstallmentsPaid(numberOfInstallmentsPaid);
                loans.add(loan);
            }

            SavingAccount savingAccount = new SavingAccount(accountNumber, ownerID, balance, dateCreate, point, kindBankInterestPercentage);
            savingAccount.setLoans(loans);

            return bankSystem.getSavingAccounts().add(savingAccount);
        }

        return true;
    }

    static public boolean updateSavingBankAccounts(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point, double bankInterestPercentage, BankInterestPercentage kindBankInterestPercentage, int designatedTime){

        int kind = 0;
        if(kindBankInterestPercentage == BankInterestPercentage.LONG_TERM) kind = 1;
        else if(kindBankInterestPercentage == BankInterestPercentage.SPECIAL) kind = 2;

        String sqlCMD = String.format("UPDATE savingbankaccount SET ownerID='%s', balance=%f, point=%d, dateCreate='"+dateCreate+"', bankInterestPercentage=%f, kindBankInterestPercentage=%d, designatedTime=%d WHERE accountNumber='%s'", ownerID, balance, point, bankInterestPercentage, kind, designatedTime, accountNumber);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteSavingBankAccount(String accountNumber){
        String sqlCMD = String.format("DELETE FROM savingbankaccount WHERE accountNumber  = '%s'", accountNumber );
        return MySQL.executeSQL(sqlCMD);
    }
}
