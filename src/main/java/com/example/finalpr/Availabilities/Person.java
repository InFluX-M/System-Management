package com.example.finalpr.Availabilities;

public class Person {

    private String ID;
    private String name;
    private int age;
    private String sex;
    private Wallet wallet;

    public Person(String ID, String name, int age, String sex) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.sex = sex.toUpperCase();
        this.wallet = new Wallet();
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public Wallet getWallet() {
        return wallet;
    }
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
