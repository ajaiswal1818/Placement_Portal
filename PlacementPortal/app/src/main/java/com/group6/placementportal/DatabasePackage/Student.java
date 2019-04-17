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
    private String List_of_Notification_IDs;

    public Student(String fullName, String department, String webmailID, String contact, String rollNo, String gender, double CPI, String programme, List<String> applied_Company_IDs, int yearOfGraduation, String password, String list_of_Notification_IDs) {
        FullName = fullName;
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
        List_of_Notification_IDs = list_of_Notification_IDs;
    }

    public Student() {
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

    public String getWebmailID() {
        return WebmailID;
    }

    public void setWebmailID(String webmailID) {
        WebmailID = webmailID;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getList_of_Notification_IDs() {
        return List_of_Notification_IDs;
    }

    public void setList_of_Notification_IDs(String list_of_Notification_IDs) {
        List_of_Notification_IDs = list_of_Notification_IDs;
    }
}