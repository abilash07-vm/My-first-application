package com.example.registerationapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "people")
public class Entry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String gender;
    private String email;
    private String Phonenumber;

    public Entry(String name, String gender, String email, String phonenumber) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        Phonenumber = phonenumber;
    }

    public Entry(int id, String name, String gender, String email, String phonenumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        Phonenumber = phonenumber;
    }

    public Entry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }
}
