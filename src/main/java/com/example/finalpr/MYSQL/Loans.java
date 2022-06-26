package com.example.finalpr.MYSQL;

public class Loans {

    static public boolean insertLoan(String loanNumber, double amount, int numberOfInstallments, int numberOfInstallmentsPaid, boolean active, String accountNumber){
        String sqlCMD = String.format("INSERT INTO loans (loanNumber, amount, numberOfInstallments, numberOfInstallmentsPaid, active, accountNumber) VALUES ('%s', %f, %d, %d, "+active+", '%s')",loanNumber,amount,numberOfInstallments,numberOfInstallmentsPaid,accountNumber);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean updateLoan(String loanNumber, double amount, int numberOfInstallments, int numberOfInstallmentsPaid, boolean active, String accountNumber){
        int ac;
        if(active) ac = 1;
        else ac = 0;

        String sqlCMD = String.format("UPDATE loans SET accountNumber='%s', amount=%f, numberOfInstallments=%d, numberOfInstallmentsPaid=%d, active='"+ac+"' WHERE loanNumber='%s'",accountNumber,amount,numberOfInstallments,numberOfInstallmentsPaid,loanNumber);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteLoan(String loanNumber){
        String sqlCMD = String.format("DELETE FROM loans WHERE loanNumber = '%s'", loanNumber);
        return MySQL.executeSQL(sqlCMD);
    }

}
