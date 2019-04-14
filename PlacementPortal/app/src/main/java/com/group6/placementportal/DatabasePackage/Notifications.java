package com.group6.placementportal.DatabasePackage;

public class Notifications {
    private String Subject;
    private String Description;
    private Boolean read;

    public Notifications(String subject, String description, Boolean read) {
        Subject = subject;
        Description = description;
        this.read = read;
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
}