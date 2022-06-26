package com.example.finalpr.MYSQL;

import com.example.finalpr.Availabilities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.bankSystem;
import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

public class CurrentBankAccounts {

    static public boolean insertCurrentBankAccounts(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point){

        String sqlCMD = String.format("INSERT INTO currentbankaccounts (accountNumber, ownerID, balance, dateCreate, point) VALUES ('%s', '%s', %f, '"+dateCreate+"', %d)",accountNumber, ownerID, balance, point);
        return MySQL.executeSQL(sqlCMD);

    }

    static public boolean loadCurrentBankAccounts() throws SQLException {

        String sqlCMD = "SELECT accountNumber, ownerID, point, balance, dateCreate FROM currentbankaccounts";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        while(resultSet.next()){

            String accountNumber = resultSet.getString("accountNumber");
            String ownerID = resultSet.getString("ownerID");
            double balance = resultSet.getDouble("balance");
            LocalDate dateCreate = resultSet.getDate("dateCreate").toLocalDate();
            int point = resultSet.getInt("point");

            String sqlCMD1 = String.format("SELECT cardNumber, CVV2, expirationDate, ownerID FROM bankcards WHERE accountNumber = '%s'", accountNumber);
            ResultSet resultSet1 = MySQL.executeQuery(sqlCMD1);

            BankCard bankCard = null;
            if(resultSet1.next()){
                String cardNumber = resultSet1.getString("cardNumber");
                LocalDate expirationDate = resultSet1.getDate("expirationDate").toLocalDate();
                String CVV2 = resultSet1.getString("CVV2");
                bankCard = new BankCard(cardNumber, expirationDate, CVV2);
            }


            ArrayList<Loan> loans = new ArrayList<>();
            String sqlCMD2 = String.format("SELECT loanNumber, amount, numberOfInstallments, numberOfInstallmentsPaid, active FROM loans WHERE accountNumber = '%s'", accountNumber);
            ResultSet resultSet2 = MySQL.executeQuery(sqlCMD2);
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

            ArrayList<BankCheck> checksSent = new ArrayList<>();
            String sqlCMD3 = String.format("SELECT checkNumber, accountNumberReceiver, amount, dateRegister, passed FROM bankchecks WHERE accountNumberSender = '%s'", accountNumber);
            ResultSet resultSet3 = MySQL.executeQuery(sqlCMD3);
            while(resultSet3.next()){
                String checkNumber = resultSet3.getString("checkNumber");
                String accountNumberSender = resultSet3.getString("accountNumberSender");
                String accountNumberReceiver = resultSet3.getString("accountNumberReceiver");
                double amount = resultSet3.getDouble("amount");
                LocalDate dateRegister = resultSet3.getDate("dateRegister").toLocalDate();
                boolean passed = resultSet3.getBoolean("passed");

                BankCheck bankCheck = new BankCheck(checkNumber, accountNumberSender, accountNumberReceiver, amount, dateRegister);
                bankCheck.setPassed(passed);

                checksSent.add(bankCheck);
            }

            ArrayList<BankCheck> checksReceived = new ArrayList<>();
            String sqlCMD4 = String.format("SELECT checkNumber, accountNumberSender, amount, dateRegister, passed FROM bankchecks WHERE accountNumberReceiver = '%s'", accountNumber);
            ResultSet resultSet4 = MySQL.executeQuery(sqlCMD4);
            while(resultSet4.next()){
                String checkNumber = resultSet3.getString("checkNumber");
                String accountNumberSender = resultSet3.getString("accountNumberSender");
                String accountNumberReceiver = resultSet3.getString("accountNumberReceiver");
                double amount = resultSet3.getDouble("amount");
                LocalDate dateRegister = resultSet3.getDate("dateRegister").toLocalDate();
                boolean passed = resultSet3.getBoolean("passed");

                BankCheck bankCheck = new BankCheck(checkNumber, accountNumberSender, accountNumberReceiver, amount, dateRegister);
                bankCheck.setPassed(passed);

                checksReceived.add(bankCheck);
            }

            CurrentAccount currentAccount = new CurrentAccount(accountNumber, ownerID, balance, dateCreate, point, bankCard);
            currentAccount.setLoans(loans);
            currentAccount.setChecksSent(checksSent);
            currentAccount.setChecksReceived(checksReceived);

            bankSystem.getCurrentBankAccounts().add(currentAccount);
        }

        return true;
    }

    static public boolean updateCurrentBankAccounts(String accountNumber, String ownerID, double balance, LocalDate dateCreate, int point){

        String sqlCMD = String.format("UPDATE currentbankaccounts SET ownerID='%s', balance=%f, point=%d, dateCreate='"+dateCreate+"' WHERE accountNumber='%s'", ownerID, balance, point, accountNumber);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteCurrentBankAccounts(String accountNumber){

        String sqlCMD1 = String.format("DELETE FROM loans WHERE accountNumber = '%s'", accountNumber);
        boolean valid = MySQL.executeSQL(sqlCMD1);

        String sqlCMD = String.format("DELETE FROM currentbankaccounts WHERE accountNumber  = '%s'", accountNumber);

        return MySQL.executeSQL(sqlCMD) && valid;
    }
}
