package com.example.finalpr.Systems;

import com.example.finalpr.Availabilities.Estate;
import com.example.finalpr.Availabilities.Person;
import com.example.finalpr.MYSQL.Estates;
import com.example.finalpr.MYSQL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

public class DocumentRegistrationSystem {

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

    public boolean loadEstates() throws SQLException {
        return Estates.LoadEstates();
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
