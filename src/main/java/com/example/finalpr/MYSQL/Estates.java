package com.example.finalpr.MYSQL;

import com.example.finalpr.Availabilities.BankCard;
import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.Availabilities.Person;
import com.example.finalpr.Availabilities.Wallet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.civilRegistrationSystem;
import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

public class Estates {

    static public boolean insertEstate(String documentRegistrationCode, String ownerID, String addressEstate, LocalDate date, double cost){

        String sqlCMD = String.format("INSERT INTO estates (documentRegistrationCode, ownerID, address, date, cost) VALUES ('%s', '%s', '%s', '"+date+"', '"+cost+"')",documentRegistrationCode, ownerID, addressEstate);
        return MySQL.executeSQL(sqlCMD);

    }

    static public boolean LoadEstates() throws SQLException {


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

    static public boolean updateEstate(String documentRegistrationCode, String ownerID, String addressEstate, LocalDate date, double cost){

        String sqlCMD = String.format("UPDATE estates SET ownerID='%s', address='%s', date='"+date+"', cost=%f WHERE documentRegistrationCode='%s'",ownerID, addressEstate, cost, documentRegistrationCode);
        return MySQL.executeSQL(sqlCMD);
    }

    static public boolean deleteEstate(String documentRegistrationCode){
        String sqlCMD = String.format("DELETE FROM estates WHERE documentRegistrationCode = '%s'", documentRegistrationCode);
        return MySQL.executeSQL(sqlCMD);
    }
}
