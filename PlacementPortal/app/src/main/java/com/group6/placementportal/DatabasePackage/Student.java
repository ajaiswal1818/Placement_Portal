package com.group6.placementportal.DatabasePackage;

import java.util.List;

public class Student {
    private static String FullName;
    private static String Department;

    private static String WebmailID;
    private static String Contact;
    private static String RollNo;
    private static String Gender;
    private static double CPI;
    private static String Programme;
    private static List<String> Applied_Company_IDs;
    private static int YearOfGraduation;
    private static String Password;

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

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static String getWebmailID() {
        return WebmailID;
    }

    public static void setWebmailID(String webmailID) {
        WebmailID = webmailID;
    }

    public static String getFullName() {
        return FullName;
    }

    public static void setFullName(String fullName) {
        FullName = fullName;
    }

    public static String getDepartment() {
        return Department;
    }

    public static void setDepartment(String department) {
        Department = department;
    }

    public static String getContact() {
        return Contact;
    }

    public static void setContact(String contact) {
        Contact = contact;
    }

    public static String getRollNo() {
        return RollNo;
    }

    public static void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public static String getGender() {
        return Gender;
    }

    public static void setGender(String gender) {
        Gender = gender;
    }

    public static double getCPI() {
        return CPI;
    }

    public static void setCPI(double CPI_get) {
        CPI = CPI_get;
    }

    public Student() {
    }

    public static String getProgramme() {
        return Programme;
    }

    public static void setProgramme(String programme) {
        Programme = programme;
    }

    public static List<String> getApplied_Company_IDs() {
        return Applied_Company_IDs;
    }

    public static void setApplied_Company_IDs(List<String> applied_Company_IDs) {
        Applied_Company_IDs = applied_Company_IDs;
    }

    public static int getYearOfGraduation() {
        return YearOfGraduation;
    }

    public static void setYearOfGraduation(int yearOfGraduation) {
        YearOfGraduation = yearOfGraduation;
    }
}