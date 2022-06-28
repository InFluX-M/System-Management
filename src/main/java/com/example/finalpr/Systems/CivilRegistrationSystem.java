package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.BankCard;
import com.example.finalpr.Availabilities.Person;
import com.example.finalpr.Availabilities.Wallet;
import com.example.finalpr.MYSQL.MySQL;
import com.example.finalpr.MYSQL.People;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class CivilRegistrationSystem {

    public static LocalDate localDate;

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

    public boolean LoadPeople() throws SQLException, IOException, ClassNotFoundException {

//        File file = new File("DateCRS.txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        localDate = (LocalDate) objectInputStream.readObject();
//        objectInputStream.close();
//        fileInputStream.close();

        return People.LoadPeople();
    }

    public boolean addPerson(Person person){
        People.insertPerson(person.getID(), person.getName(), person.getAge(), person.getSex());
        return people.add(person);
    }

    public boolean deletePerson(String ID){
        people.remove(searchPerson(ID));
        return People.deletePerson(ID);
    }

    public boolean editPerson(String ID, String name, int age, String sex){
        People.updatePerson(ID, name, age, sex);
        searchPerson(ID).setName(name);
        searchPerson(ID).setAge(age);
        searchPerson(ID).setSex(sex);
        return true;
    }

    public Person searchPerson(String ID){
        for(Person person : people){
            if(ID.equals(person.getID())) return person;
        }

        return null;
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
