package com.group6.placementportal.DatabasePackage;

import java.io.File;

public class job {
    private int job_id;
    private String profile;
    private float ctc;
    private String location;
    private String brochure;

    public String  getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
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

    public job(int job_id, String profile, float ctc, String location,String brochure) {
        this.job_id = job_id;
        this.profile = profile;
        this.ctc = ctc;
        this.location = location;
        this.brochure = brochure;
    }

    public job() {
    }
}
