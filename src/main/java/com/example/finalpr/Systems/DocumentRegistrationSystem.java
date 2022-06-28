package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.Availabilities.Person;
import com.example.finalpr.MYSQL.Estates;
import com.example.finalpr.MYSQL.MySQL;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;
import static com.example.finalpr.HelloApplication.systems;

public class DocumentRegistrationSystem{

    public static LocalDate localDate = LocalDate.now();

    private Estate nowEstate;
    private ArrayList<Estate> estates;
    private static DocumentRegistrationSystem singletonDocumentRegistrationSystem;

    private DocumentRegistrationSystem(){
        this.estates = new ArrayList<>();

    }

    public static DocumentRegistrationSystem getInstanceDocumentRegistrationSystem(){

        if(singletonDocumentRegistrationSystem == null){
            singletonDocumentRegistrationSystem = new DocumentRegistrationSystem();
        }
        return singletonDocumentRegistrationSystem;
    }

    public boolean loadEstates() throws SQLException, IOException, ClassNotFoundException {

//        File file = new File("DateDRS.txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        localDate = (LocalDate) objectInputStream.readObject();
//        objectInputStream.close();
//        fileInputStream.close();

        return Estates.LoadEstates();
    }

    public boolean addEstate(Estate estate){
        estates.add(estate);
        return Estates.insertEstate(estate.getDocumentRegistrationCode(), estate.getOwnerID(), estate.getAddressEstate(), estate.getDate(), estate.getCost());
    }

    public boolean editEstate(String documentRegistrationCode, String ownerID, String addressEstate, LocalDate date, double cost){
        Estate estate = searchEstate(documentRegistrationCode);
        estate.setAddressEstate(addressEstate);
        estate.setCost(cost);
        estate.setOwnerID(ownerID);
        estate.setDate(date);
        return Estates.updateEstate(estate.getDocumentRegistrationCode(), estate.getOwnerID(), estate.getAddressEstate(), estate.getDate(), estate.getCost());
    }

    public boolean deleteEstate(String documentRegistrationCode){
        systems.deleteEstate(searchEstate(documentRegistrationCode));
        estates.remove(searchEstate(documentRegistrationCode));
        return Estates.deleteEstate(documentRegistrationCode);
    }

    public Estate searchEstate(String documentRegistrationCode){
        for(Estate estate : estates){
            if(documentRegistrationCode.equals(estate.getDocumentRegistrationCode())) return estate;
        }

        return null;
    }

    public Estate getNowEstate() {
        return nowEstate;
    }

    public void setNowEstate(Estate nowEstate) {
        this.nowEstate = nowEstate;
    }

    public ArrayList<Estate> getEstates() {
        return estates;
    }

    public void setEstates(ArrayList<Estate> estates) {
        this.estates = estates;
    }

    public static DocumentRegistrationSystem getSingletonDocumentRegistrationSystem() {
        return singletonDocumentRegistrationSystem;
    }

    public static void setSingletonDocumentRegistrationSystem(DocumentRegistrationSystem singletonDocumentRegistrationSystem) {
        DocumentRegistrationSystem.singletonDocumentRegistrationSystem = singletonDocumentRegistrationSystem;
    }

    public boolean loginEstate(String documentRegistrationCode, String ownerID){

        for(Estate estate : estates){
            if(documentRegistrationCode.equals(estate.getDocumentRegistrationCode()) && ownerID.equals(estate.getOwnerID())){
                this.nowEstate = estate;
                return true;
            }
        }

        return false;
    }



}
