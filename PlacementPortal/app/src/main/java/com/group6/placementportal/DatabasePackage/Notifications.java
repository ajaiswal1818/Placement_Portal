package com.group6.placementportal.DatabasePackage;

public class Notifications {
    private String Subject;
    private String Description;
    private Boolean read;
    private String Notiication_ID;

    public Notifications(String subject, String description, Boolean read, String notiication_ID) {
        Subject = subject;
        Description = description;
        this.read = read;
        Notiication_ID = notiication_ID;
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

    public String getNotiication_ID() {
        return Notiication_ID;
    }

    public void setNotiication_ID(String notiication_ID) {
        Notiication_ID = notiication_ID;
    }
}