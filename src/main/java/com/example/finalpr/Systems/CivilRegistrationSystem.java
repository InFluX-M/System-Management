package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.BankCard;
import com.example.finalpr.Availabilities.Person;
import com.example.finalpr.Availabilities.Wallet;
import com.example.finalpr.MYSQL.MySQL;
import com.example.finalpr.MYSQL.People;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class CivilRegistrationSystem {

    private Person nowPerson;
    private ArrayList<Person> people;
    private static CivilRegistrationSystem singletonCivilRegistrationSystem;

    private CivilRegistrationSystem(){
        this.people = new ArrayList<>();
    }

    public static CivilRegistrationSystem getInstanceCivilRegistrationSystem(){
        if(singletonCivilRegistrationSystem == null){
            singletonCivilRegistrationSystem = new CivilRegistrationSystem();
        }
        return singletonCivilRegistrationSystem;
    }

    public boolean LoadPeople() throws SQLException {
        return People.LoadPeople();
    }

    public Person getNowPerson() {
        return nowPerson;
    }
    public void setNowPerson(Person nowPerson) {
        this.nowPerson = nowPerson;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }
    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public static CivilRegistrationSystem getSingletonCivilRegistrationSystem() {
        return singletonCivilRegistrationSystem;
    }
    public static void setSingletonCivilRegistrationSystem(CivilRegistrationSystem singletonCivilRegistrationSystem) {
        CivilRegistrationSystem.singletonCivilRegistrationSystem = singletonCivilRegistrationSystem;
    }

    public boolean loginPerson(String ID, String name){

        for(Person person : people){
            if(ID.equals(person.getID()) && name.equals(person.getName())){
                this.nowPerson = person;
                return true;
            }
        }

        return false;
    }

}
