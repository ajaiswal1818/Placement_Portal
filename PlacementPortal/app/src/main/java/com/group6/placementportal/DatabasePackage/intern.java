package com.group6.placementportal.DatabasePackage;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class intern implements Serializable {
    private String intern_id;
    private String profile;
    private float ctc;
    private String location;
    private String brochure;
    private float cpi;
    private String departments;
    private String intern_requirements;

    public String getIntern_requirements() {
        return intern_requirements;
    }

    public void setIntern_requirements(String intern_requirements) {
        this.intern_requirements = intern_requirements;
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

    public String  getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
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

    /*public File getBrochure() {
        return brochure;
    }

    public void setBrochure(File brochure) {
        this.brochure = brochure;
    }*/

    public intern(String intern_id, String profile, float ctc, String location,String brochure,float cpi,String departments,String intern_requirements) {
        this.intern_id = intern_id;
        this.profile = profile;
        this.ctc = ctc;
        this.location = location;
        this.brochure = brochure;
        this.cpi=cpi;
        this.departments=departments;
        this.intern_requirements=intern_requirements;
    }

    public intern() {
    }
}
