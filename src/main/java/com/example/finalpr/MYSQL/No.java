package com.example.finalpr.MYSQL;

import com.example.finalpr.Systems.Systems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class No {

    static public String cardNumber;
    static public String CVV2;
    static public String ID;
    static public String accountNumber;
    static public String documentRegistrationCode;
    static public String checkNumber;
    static public String loanNumber;

    static public String getCardNumber() throws SQLException {

        String sqlCMD = "SELECT cardNumber FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            long cd = resultSet.getInt("cardNumber");
            String sqlCMD1 = String.format("UPDATE no SET cardNumber=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            cardNumber = cd+"";
            return cardNumber;
        }

        return null;
    }

    static public String getCVV2() throws SQLException {

        String sqlCMD = "SELECT CVV2 FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            long cd = resultSet.getInt("CVV2");
            String sqlCMD1 = String.format("UPDATE no SET CVV2=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            CVV2 = cd+"";
            return CVV2;
        }

        return null;
    }

    static public String getCheckNumber() throws SQLException {

        String sqlCMD = "SELECT checkNumber FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        if(resultSet.next()){
            long cd = resultSet.getInt("checkNumber");
            String sqlCMD1 = String.format("UPDATE no SET checkNumber=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            checkNumber = (cd)+"";
            return checkNumber;
        }

        return null;
    }

    static public String getDocumentRegistrationCode() throws SQLException {

        String sqlCMD = "SELECT documentRegistrationCode FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            long cd = resultSet.getInt("documentRegistrationCode");
            String sqlCMD1 = String.format("UPDATE no SET documentRegistrationCode=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            documentRegistrationCode = cd+"";
            return documentRegistrationCode;
        }

        return null;
    }

    static public String getAccountNumber() throws SQLException {

        String sqlCMD = "SELECT accountNumber FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            long cd = resultSet.getInt("accountNumber");
            String sqlCMD1 = String.format("UPDATE no SET accountNumber=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            accountNumber = cd+"";
            return accountNumber;
        }

        return null;
    }

    static public String getID() throws SQLException {

        String sqlCMD = "SELECT ID FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            long cd = resultSet.getInt("ID");
            String sqlCMD1 = String.format("UPDATE no SET ID=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            ID = cd+"";
            return ID;
        }

        return null;
    }

    static public String getLoanNumber() throws SQLException {

        String sqlCMD = "SELECT loanNumber FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            long cd = resultSet.getInt("loanNumber");
            String sqlCMD1 = String.format("UPDATE no SET loanNumber=%d", cd+1);
            MySQL.executeSQL(sqlCMD1);
            loanNumber = cd+"";
            return loanNumber;
        }

        return null;

    }

    static public LocalDate getDate() throws SQLException {

        String sqlCMD = "SELECT localDate FROM no";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        assert resultSet != null;
        if(resultSet.next()){
            return resultSet.getDate("localDate").toLocalDate();
        }

        return null;

    }

    static public void changeDate(LocalDate localDate){
        String sqlCMD = "UPDATE no SET localDate='" + localDate + "'";
        MySQL.executeSQL(sqlCMD);
    }
}
