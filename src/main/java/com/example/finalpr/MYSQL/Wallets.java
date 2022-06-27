package com.example.finalpr.MYSQL;

import java.time.LocalDate;

public class Wallets {

    static public boolean insertWallet(String ownerID){
        String sqlCMD = String.format("INSERT INTO wallets (ownerID) VALUES ('%s')",ownerID);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean updateWallets(String ownerID, double money){
        String sqlCMD = String.format("UPDATE wallets SET money=%f WHERE ownerID='%s'", money, ownerID);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteWallet(String ownerID){
        String sqlCMD = String.format("DELETE FROM wallets WHERE ownerID = '%s'", ownerID);
        return MySQL.executeSQL(sqlCMD);
    }
}
