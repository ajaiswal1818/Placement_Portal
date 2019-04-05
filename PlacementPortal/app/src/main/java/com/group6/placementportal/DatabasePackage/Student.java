package com.group6.placementportal.DatabasePackage;

import java.util.List;

class Student {
    private string FirstName;
    private string LastName;
    private string Department;
    private string EmailID;
    private string Contact;
    private string RollNo;
    private string Gender;
    private double CPI;
    private string Programme;
    private List<string> Applied_Company_IDs;
    private int YearOfGraduation;


    public Student(string firstName, string lastName, string department, string emailID, string contact, string rollNo, string gender, double CPI, string programme, List<string> applied_Company_IDs, int yearOfGraduation) {
        FirstName = firstName;
        LastName = lastName;
        Department = department;
        EmailID = emailID;
        Contact = contact;
        RollNo = rollNo;
        Gender = gender;
        this.CPI = CPI;
        Programme = programme;
        Applied_Company_IDs = applied_Company_IDs;
        YearOfGraduation = yearOfGraduation;
    }

    public string getFirstName() {
        return FirstName;
    }

    public void setFirstName(string firstName) {
        FirstName = firstName;
    }

    public string getLastName() {
        return LastName;
    }

    public void setLastName(string lastName) {
        LastName = lastName;
    }

    public string getDepartment() {
        return Department;
    }

    public void setDepartment(string department) {
        Department = department;
    }

    public string getEmailID() {
        return EmailID;
    }

    public void setEmailID(string emailID) {
        EmailID = emailID;
    }

    public string getContact() {
        return Contact;
    }

    public void setContact(string contact) {
        Contact = contact;
    }

    public string getRollNo() {
        return RollNo;
    }

    public void setRollNo(string rollNo) {
        RollNo = rollNo;
    }

    public string getGender() {
        return Gender;
    }

    public void setGender(string gender) {
        Gender = gender;
    }

    public double getCPI() {
        return CPI;
    }

    public void setCPI(double CPI) {
        this.CPI = CPI;
    }

    public string getProgramme() {
        return Programme;
    }

    public void setProgramme(string programme) {
        Programme = programme;
    }

    public List<string> getApplied_Company_IDs() {
        return Applied_Company_IDs;
    }

    public void setApplied_Company_IDs(List<string> applied_Company_IDs) {
        Applied_Company_IDs = applied_Company_IDs;
    }

    public int getYearOfGraduation() {
        return YearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        YearOfGraduation = yearOfGraduation;
    }
}
