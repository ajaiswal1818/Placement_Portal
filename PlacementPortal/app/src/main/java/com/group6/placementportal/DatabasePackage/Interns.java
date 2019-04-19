package com.group6.placementportal.DatabasePackage;

import java.io.Serializable;

import javax.sql.StatementEvent;

public class Interns implements Serializable {
    private String intern_id;
    private String profile;
    private float ctc;
    private String location;
    private String brochure;
    private String company_id;
    private String company_name;
    private String branches;
    private float cutoff_cpi;
    private String intern_requirements;

    public Interns(String intern_id, String profile, float ctc, String location, String brochure, String company_id, String company_name, String branches, float cutoff_cpi, String intern_requirements) {
        this.intern_id = intern_id;
        this.profile = profile;
        this.ctc = ctc;
        this.location = location;
        this.brochure = brochure;
        this.company_id = company_id;
        this.company_name = company_name;
        this.branches = branches;
        this.cutoff_cpi = cutoff_cpi;
        this.intern_requirements = intern_requirements;
    }

    public String getIntern_requirements() {
        return intern_requirements;
    }

    public void setIntern_requirements(String intern_requirements) {
        this.intern_requirements = intern_requirements;
    }

    public Interns() {
    }

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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBranches() {
        return branches;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }

    public float getCutoff_cpi() {
        return cutoff_cpi;
    }

    public void setCutoff_cpi(float cutoff_cpi) {
        this.cutoff_cpi = cutoff_cpi;
    }
}
