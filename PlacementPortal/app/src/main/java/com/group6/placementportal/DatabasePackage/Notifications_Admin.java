package com.group6.placementportal.DatabasePackage;

public class Notifications_Admin {
    private String Subject;
    private String Description;
    private String Pdflink;
    private String Notification_ID;

    public Notifications_Admin(){

    }

    public Notifications_Admin(String subject, String description, String pdflink, String notification_ID) {
        Subject = subject;
        Description = description;
        Pdflink = pdflink;
        Notification_ID = notification_ID;
    }


    public void setSubject(String subject) {
        Subject = subject;
    }


    public void setDescription(String description) {
        Description = description;
    }

    public void setPdflink(String Pdflink) {
        this.Pdflink = Pdflink;
    }

    public void setNotification_ID(String notification_ID) {
        Notification_ID = notification_ID;
    }

    public String getSubject() {
        return Subject;
    }

    public String getDescription() {
        return Description;
    }

    public String getPdflink() {
        return Pdflink;
    }

    public String getNotification_ID() {
        return Notification_ID;
    }
}
