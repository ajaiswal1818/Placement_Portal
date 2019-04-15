package com.group6.placementportal.DatabasePackage;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private String FullName;
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

    public Student(String firstName, String department, String webmailID, String contact, String rollNo, String gender, double CPI_get, String programme, List<String> applied_Company_IDs, int yearOfGraduation, String password) {
        FullName = firstName;
        Department = department;
        WebmailID = webmailID;
        Contact = contact;
        RollNo = rollNo;
        Gender = gender;
        CPI = CPI_get;
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

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
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

    public void setCPI(double CPI_get) {
        CPI = CPI_get;
    }

    public Student() {
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