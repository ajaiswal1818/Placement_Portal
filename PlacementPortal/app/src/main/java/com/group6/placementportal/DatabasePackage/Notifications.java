package com.group6.placementportal.DatabasePackage;

public class Notifications {
    private String Subject;
    private String Description;
    private Boolean read;
    private String Notification_ID;

    public Notifications(String subject, String description, Boolean read, String notification_ID) {
        Subject = subject;
        Description = description;
        this.read = read;
        Notification_ID = notification_ID;
    }

    public Notifications() {
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getNotification_ID() {
        return Notification_ID;
    }

    public void setNotification_ID(String notification_ID) {
        Notification_ID = notification_ID;
    }
}