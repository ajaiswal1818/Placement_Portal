package com.group6.placementportal.DatabasePackage;

public class intern {
    private String intern_id;
    private String profile;
    private float ctc;
    private String location;
    private String brochure;
    private float cpi;
    private String departments;

    public String getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(String intern_id) {
        this.intern_id = intern_id;
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

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

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

    public intern(String intern_id, String profile, float ctc, String location, String brochure, float cpi, String departments) {
        this.intern_id = intern_id;
        this.profile = profile;
        this.ctc = ctc;
        this.location = location;
        this.brochure = brochure;
        this.cpi = cpi;
        this.departments = departments;
    }
}
