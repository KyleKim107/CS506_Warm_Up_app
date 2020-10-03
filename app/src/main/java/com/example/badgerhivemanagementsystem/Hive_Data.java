package com.example.badgerhivemanagementsystem;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class Hive_Data {


    private String Name;
    private String Honey_Stores;
    private String Information;
    private String Gain_Loss;
    private String Inventory;
    private String Q_Production;
    private String Inspection;
    private String Health_Quality;
    private String Owner;
    private Bitmap image;

//



//    public Hive_Data(String Name, String Honey_Stores, String Information, String Gain_Loss,
//                     String Inventory, String Q_Production, String Inspection,  String Health_Quality  , Bitmap image){ // constructor
//        this.Name = Name;
//        this.Honey_Stores = Honey_Stores;
//        this.Information = Information;
//        this.Gain_Loss = Gain_Loss;
//        this.Inventory = Inventory;
//        this.Q_Production = Q_Production;
//        this.Inspection = Inspection;
//        this.Health_Quality = Health_Quality;
//        this.image = image;
//        this.Owner = null;
//    }
    public Hive_Data(){}


    // You can assing owner
    public Hive_Data(String Name, String Honey_Stores, String Information, String Gain_Loss,
                     String Inventory, String Q_Production, String Inspection, String Health_Quality, Bitmap image , String Owner ){ // constructor
        this.Name = Name;
        this.Honey_Stores = Honey_Stores;
        this.Information = Information;
        this.Gain_Loss = Gain_Loss;
        this.Inventory = Inventory;
        this.Q_Production = Q_Production;
        this.Inspection = Inspection;
        this.Health_Quality = Health_Quality;
        this.image = image;
        this.Owner = Owner;
    }

//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("image", image);
//        result.put("Honey_Stores", Honey_Stores);
//        result.put("Information", Information);
//        result.put("Gain_Loss", Gain_Loss);
//        result.put("Inventory", Inventory);
//        result.put("Q_Production", Q_Production);
//        result.put("Inpections", Inspection);
//        result.put("Health_Quality", Health_Quality);
//        result.put("Owner", Owner);
//
//        return result;
//    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHoney_Stores() {
        return Honey_Stores;
    }

    public void setHoney_Stores(String honey_Stores) {
        Honey_Stores = honey_Stores;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getGain_Loss() {
        return Gain_Loss;
    }

    public void setGain_Loss(String gain_Loss) {
        Gain_Loss = gain_Loss;
    }

    public String getInventory() {
        return Inventory;
    }

    public void setInventory(String inventory) {
        Inventory = inventory;
    }

    public String getQ_Production() {
        return Q_Production;
    }

    public void setQ_Production(String q_Production) {
        Q_Production = q_Production;
    }

    public String getInspection() {
        return Inspection;
    }

    public void setInspection(String inspection) {
        Inspection = inspection;
    }

    public String getHealth_Quality() {
        return Health_Quality;
    }

    public void setHealth_Quality(String health_Quality) {
        Health_Quality = health_Quality;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
