package com.example.uiinterface.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "person")
public class    Person {

    // columns
    @PrimaryKey(autoGenerate = true)
            int id;
    String name;
    String phone;
    String address;
    String city;
    String zip;
    String email;
    String date;

    public Person(String name, String phone, String address, String city, String zip,
                  String email, String date) {

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.date = date;

    }

    @Ignore
    public Person(int id, String name, String phone, String address, String city, String zip,
                  String email, String date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.date = date;

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

    public void setName(String name) {this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate(){return date;}

    public void setDate(String date) { this.date = date;}


    @Override
    public String toString(){
        return "person{" + "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + date + '\'' +
                '}';
    }
}
