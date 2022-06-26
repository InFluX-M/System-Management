package com.example.finalpr.MYSQL;

import com.example.finalpr.Availabilities.Estate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

public class BankCards {

    static public boolean insertBankCard(String accountNumber, String ownerID, String addressEstate, LocalDate date, double cost){

        String sqlCMD = String.format("INSERT INTO estates (documentRegistrationCode, ownerID, address, date, cost) VALUES ('%s', '%s', '%s', '"+date+"', '"+cost+"')",accountNumber, ownerID, addressEstate);
        return MySQL.executeSQL(sqlCMD);

    }

    static public boolean loadBankCards() throws SQLException {


        String sqlCMD = "SELECT documentRegistrationCode, ownerID, address, date, cost FROM estates";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        while(resultSet.next()){

            String documentRegistrationCode = resultSet.getString("documentRegistrationCode");
            String ownerID = resultSet.getString("ownerID");
            String address = resultSet.getString("address");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            double cost = resultSet.getDouble("cost");

            Estate estate = new Estate(documentRegistrationCode, ownerID, address, date, cost);

            documentRegistrationSystem.getEstates().add(estate);
        }


        return true;
    }

}
