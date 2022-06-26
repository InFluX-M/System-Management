package com.example.finalpr.MYSQL;

import com.example.finalpr.Availabilities.BankCard;
import com.example.finalpr.Availabilities.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.finalpr.Availabilities.Wallet;
import com.example.finalpr.Systems.CivilRegistrationSystem;

import static com.example.finalpr.HelloApplication.civilRegistrationSystem;

public class People {

    static public boolean insertPerson(String ID, String name, int age, String sex){

        String sqlCMD = String.format("INSERT INTO people (ID, name, age, sex) VALUES ('%s', '%s', %d, '%s')",ID,name,age,sex);

        boolean valid1 = Wallets.insertWallet(ID);
        boolean valid2 = MySQL.executeSQL(sqlCMD);

        return (valid1 && valid2);
    }

    static public boolean LoadPeople() throws SQLException {

        String sqlCMD = "SELECT people.ID, people.name, people.sex, people.age FROM people";
        ResultSet resultSet = MySQL.executeQuery(sqlCMD);

        while(resultSet.next()){

            String ID = resultSet.getString("people.ID");
            String name = resultSet.getString("people.name");
            String sex = resultSet.getString("people.ID");
            int age = resultSet.getInt("people.age");

            String sqlCMD3 = String.format("SELECT wallets.money FROM wallets WHERE ownerID = '%s'",ID);
            ResultSet resultSet3 = MySQL.executeQuery(sqlCMD3);
            resultSet3.next();
            double money = resultSet3.getDouble("wallets.money");


            ArrayList<BankCard> bankCards = new ArrayList<>();
            String sqlCMD2 = String.format("SELECT bankcards.cardNumber, bankcards.CVV2, bankcards.expirationDate FROM bankcards WHERE ownerID = '%s'",ID);
            ResultSet resultSet2 = MySQL.executeQuery(sqlCMD2);
            while(resultSet2.next()){

                String cardNumber = resultSet2.getString("bankcards.cardNumber");
                LocalDate expirationDate = resultSet2.getDate("bankcards.expirationDate").toLocalDate();
                String CVV2 = resultSet2.getString("bankcards.CVV2");

                bankCards.add(new BankCard(cardNumber, expirationDate, CVV2));
            }

            Wallet wallet = new Wallet();
            wallet.setBankCards(bankCards);
            wallet.setMoney(money);

            Person person = new Person(ID, name, age, sex);
            person.setWallet(wallet);

            civilRegistrationSystem.getPeople().add(person);
        }

        return true;
    }

    static public boolean updatePerson(String ID, String name, int age, String sex){

        String sqlCMD = String.format("UPDATE people SET name='%s', age=%d, sex='%s' WHERE ID='%s'",name,age,sex,ID);
        return MySQL.executeSQL(sqlCMD);
    }
}
