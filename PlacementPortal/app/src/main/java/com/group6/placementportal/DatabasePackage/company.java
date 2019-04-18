package com.group6.placementportal.DatabasePackage;

import com.group6.placementportal.DatabasePackage.job;
import java.io.Serializable;
import java.util.ArrayList;

public class company implements Serializable {
    private String company_name;
    private String company_id;
    private String sector;
    private String contact_no;
    private String email_address;
    private String headoffice;
    private String username;
    private String password;
    private String approved;

    public company() {
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*    public company() {
    }*/

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getHeadoffice() {
        return headoffice;
    }

    public void setHeadoffice(String headoffice) {
        this.headoffice = headoffice;
    }


    public company(String company_name, String company_id, String sector, String contact_no, String email_address, String headoffice,String username,String password,String approved) {
        this.company_name = company_name;
        this.company_id = company_id;
        this.sector = sector;
        this.contact_no = contact_no;
        this.email_address = email_address;
        this.headoffice = headoffice;
        //this.jobs = jobs;
        this.username=username;
        this.password=password;
        this.approved=approved;
    }
}
