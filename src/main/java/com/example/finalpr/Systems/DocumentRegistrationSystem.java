package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.MYSQL.Estates;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.systems;

public class DocumentRegistrationSystem{

    public static LocalDate localDate;

    private final ArrayList<Estate> estates;
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

    public boolean loadEstates() throws SQLException {

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

        boolean v = systems.deleteEstate(searchEstate(documentRegistrationCode));
        boolean v1 = estates.remove(searchEstate(documentRegistrationCode));
        return Estates.deleteEstate(documentRegistrationCode) && v1 && v;
    }

    public Estate searchEstate(String documentRegistrationCode){

        for(Estate estate : estates){
            if(documentRegistrationCode.equals(estate.getDocumentRegistrationCode())) return estate;
        }

        return null;
    }

    public ArrayList<Estate> getEstates() {
        return estates;
    }
}
