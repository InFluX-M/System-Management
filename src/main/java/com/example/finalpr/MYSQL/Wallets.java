package com.example.finalpr.MYSQL;

public class Wallets {

    static public boolean insertWallet(String ownerID){
        String sqlCMD = String.format("INSERT INTO wallets (ownerID) VALUES ('%s')",ownerID);
        return MySQL.executeSQL(sqlCMD);
    }

}
