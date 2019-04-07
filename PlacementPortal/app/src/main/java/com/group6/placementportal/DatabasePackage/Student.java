package com.group6.placementportal.DatabasePackage;

import java.util.List;

class Student {
    private String FirstName;
    private String LastName;
    private String Department;

    private String WebmailID;
    private String Contact;
    private String RollNo;
    private String Gender;
    private double CPI;
    private String Programme;
    private List<String> Applied_Company_IDs;
    private int YearOfGraduation;
    private String Password;

    public Student(String firstName, String lastName, String department, String webmailID, String contact, String rollNo, String gender, double CPI, String programme, List<String> applied_Company_IDs, int yearOfGraduation, String password) {
        FirstName = firstName;
        LastName = lastName;
        Department = department;
        WebmailID = webmailID;
        Contact = contact;
        RollNo = rollNo;
        Gender = gender;
        this.CPI = CPI;
        Programme = programme;
        Applied_Company_IDs = applied_Company_IDs;
        YearOfGraduation = yearOfGraduation;
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getWebmailID() {
        return WebmailID;
    }

    public void setWebmailID(String webmailID) {
        WebmailID = webmailID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public double getCPI() {
        return CPI;
    }

    public void setCPI(double CPI) {
        this.CPI = CPI;
    }

    public String getProgramme() {
        return Programme;
    }

    public void setProgramme(String programme) {
        Programme = programme;
    }

    public List<String> getApplied_Company_IDs() {
        return Applied_Company_IDs;
    }

    public void setApplied_Company_IDs(List<String> applied_Company_IDs) {
        Applied_Company_IDs = applied_Company_IDs;
    }

    public int getYearOfGraduation() {
        return YearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        YearOfGraduation = yearOfGraduation;
    }
}
