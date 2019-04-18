package com.group6.placementportal.DatabasePackage;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class job implements Serializable {
    private String job_id;
    private String profile;
    private float ctc;
    private String location;
    private String brochure;
    private float cpi;
    private String departments;

    public float getCpi() {
        return cpi;
    }

    public void setCpi(float cpi) {
        this.cpi = cpi;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String  getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public float getCtc() {
        return ctc;
    }

    public void setCtc(float ctc) {
        this.ctc = ctc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /*public File getBrochure() {
        return brochure;
    }

    public void setBrochure(File brochure) {
        this.brochure = brochure;
    }*/

    public job(String job_id, String profile, float ctc, String location,String brochure,float cpi,String departments) {
        this.job_id = job_id;
        this.profile = profile;
        this.ctc = ctc;
        this.location = location;
        this.brochure = brochure;
        this.cpi=cpi;
        this.departments=departments;
    }

    public job() {
    }
}
