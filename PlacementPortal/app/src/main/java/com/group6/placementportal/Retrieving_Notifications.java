package com.group6.placementportal;

public class Retrieving_Notifications {
    private String subject;
    private String description;
    private String notification_id;
    private String pdf_link;

    public Retrieving_Notifications(String subject, String description, String notification_id, String pdf_link) {
        this.subject = subject;
        this.description = description;
        this.notification_id = notification_id;
        this.pdf_link = pdf_link;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getPdf_link() {
        return pdf_link;
    }

    public void setPdf_link(String pdf_link) {
        this.pdf_link = pdf_link;
    }
}
