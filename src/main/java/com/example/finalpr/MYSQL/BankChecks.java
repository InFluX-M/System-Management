package com.example.finalpr.MYSQL;

import java.time.LocalDate;

public class BankChecks {

    static public boolean insertBankCheck(String checkNumber, String accountNumberSender, String accountNumberReceiver, double amount, LocalDate dateRegister, boolean passed){

        int bool=0;
        if(passed) bool=1;

        String sqlCMD = String.format("INSERT INTO bankchecks (checkNumber, accountNumberSender, accountNumberReceiver, amount, dateRegister, passed) VALUES ('%s', '%s', '%s', %f, '"+dateRegister+"', '"+bool+"')",checkNumber, accountNumberSender, accountNumberReceiver, amount);
        return MySQL.executeSQL(sqlCMD);

    }

    static public boolean updateBankCheck(String checkNumber, String accountNumberSender, String accountNumberReceiver, double amount, LocalDate dateRegister, boolean passed){

        String sqlCMD = String.format("UPDATE bankchecks SET accountNumberSender='%s', accountNumberReceiver='%s', amount=%f, dateRegister='"+dateRegister+"', passed='"+passed+"' WHERE checkNumber='%s'",accountNumberSender,accountNumberReceiver,amount,checkNumber);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteBankCheck(String checkNumber){
        String sqlCMD = String.format("DELETE FROM bankchecks WHERE checkNumber = '%s'", checkNumber);
        return MySQL.executeSQL(sqlCMD);
    }

}
