package com.group6.placementportal.DatabasePackage;

import java.io.Serializable;

public class PersonalDetails implements Serializable {
    private String Name;
    private String Father_Name;
    private String DOB;
    private String Gender;
    private String Category;
    private String Religion;
    private String State;
    private String Address;
    private String Mobile;
    private String Phone;
    private String Email;

    public PersonalDetails() {
    }

    public PersonalDetails(String name, String father_Name, String DOB, String gender, String category, String religion, String state, String address, String mobile, String phone, String email) {
        this.Name = name;
        this.Father_Name = father_Name;
        this.DOB = DOB;
        this.Gender = gender;
        this.Category = category;
        this.Religion = religion;
        this.State = state;
        this.Address = address;
        this.Mobile = mobile;
        this.Phone = phone;
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFather_Name() {
        return Father_Name;
    }

    public void setFather_Name(String father_Name) {
        Father_Name = father_Name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
