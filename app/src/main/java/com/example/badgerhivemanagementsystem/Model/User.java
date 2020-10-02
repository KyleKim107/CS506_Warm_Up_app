package com.example.badgerhivemanagementsystem.Model;

import java.util.ArrayList;

public class User {

    private String id, name, imageURL, phone;
//    private ArrayList<Hive> hives;
//    private ArrayList<Apiary> apiaries;

    public User(String id, String displayName, String imageURL, String phone, ArrayList<Object> hives, ArrayList<Object> apiaries) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.phone = phone;
//        this.hives = new ArrayList<>();
//        this.apiaries = new ArrayList<>();
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public ArrayList<Hive> getHives() {
//        return hives;
//    }
//
//    public void setHives(ArrayList<Hive> hives) {
//        this.hives = hives;
//    }
//
//    public ArrayList<Apiary> getApiaries() {
//        return apiaries;
//    }
//
//    public void setApiaries(ArrayList<Apiary> apiaries) {
//        this.apiaries = apiaries;
//    }
}
