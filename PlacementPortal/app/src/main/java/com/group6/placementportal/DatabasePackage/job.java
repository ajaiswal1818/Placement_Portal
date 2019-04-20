package com.group6.placementportal.DatabasePackage;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class job implements Serializable {
<<<<<<< HEAD
    private int job_id;
=======
    private String job_id;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    private String profile;
    private float ctc;
    private String location;
    private String brochure;
    private float cpi;
    private String departments;
<<<<<<< HEAD
=======
    private String job_requirements;

    public String getJob_requirements() {
        return job_requirements;
    }

    public void setJob_requirements(String job_requirements) {
        this.job_requirements = job_requirements;
    }
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

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

<<<<<<< HEAD
    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
=======
    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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

<<<<<<< HEAD
    public job(int job_id, String profile, float ctc, String location,String brochure,float cpi,String departments) {
=======
    public job(String job_id, String profile, float ctc, String location,String brochure,float cpi,String departments,String job_requirements) {
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        this.job_id = job_id;
        this.profile = profile;
        this.ctc = ctc;
        this.location = location;
        this.brochure = brochure;
        this.cpi=cpi;
        this.departments=departments;
<<<<<<< HEAD
=======
        this.job_requirements=job_requirements;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    }

    public job() {
    }
}
