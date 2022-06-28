package com.example.finalpr.MYSQL;

import java.time.LocalDate;

public class BankCards {

    static public boolean insertBankCard(String cardNumber, LocalDate expirationDate, String CVV2, String ownerID, String accountNumber){

        String sqlCMD = String.format("INSERT INTO bankcards (cardNumber, CVV2, ownerID, accountNumber, expirationDate) VALUES ('%s', '%s', '%s', '%s', '"+expirationDate+"')",cardNumber, CVV2, ownerID, accountNumber);
        return MySQL.executeSQL(sqlCMD);

    }

    static public boolean updateBankCard(String cardNumber, LocalDate expirationDate, String CVV2, String ownerID, String accountNumber){

        String sqlCMD = String.format("UPDATE bankcards SET ownerID='%s', cardNumber='%s', CVV2='%s', expirationDate='"+expirationDate+"' WHERE accountNumber='%s'",ownerID,cardNumber,CVV2,accountNumber);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteBankCard(String accountNumber){
        String sqlCMD = String.format("DELETE FROM bankcards WHERE accountNumber = '%s'", accountNumber);
        return MySQL.executeSQL(sqlCMD);
    }

}
