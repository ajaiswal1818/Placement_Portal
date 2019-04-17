package com.group6.placementportal.DatabasePackage;

import java.io.Serializable;

public class JRF_applications implements Serializable {
    private String Application_No;
    private String ProgrammingLanguages;
    private String YearandType;
    private String AppliedProject;
    private String AppliedPost;
    private String Qualified;

    public JRF_applications(String application_No, String programmingLanguages, String yearandType, String appliedProject, String appliedPost, String qualified) {
        Application_No = application_No;
        ProgrammingLanguages = programmingLanguages;
        YearandType = yearandType;
        AppliedProject = appliedProject;
        AppliedPost = appliedPost;
        Qualified = qualified;
    }

    public String getApplication_No() {
        return Application_No;
    }

    public void setApplication_No(String application_No) {
        Application_No = application_No;
    }

    public String getProgrammingLanguages() {
        return ProgrammingLanguages;
    }

    public void setProgrammingLanguages(String programmingLanguages) {
        ProgrammingLanguages = programmingLanguages;
    }

    public String getYearandType() {
        return YearandType;
    }

    public void setYearandType(String yearandType) {
        YearandType = yearandType;
    }

    public String getAppliedProject() {
        return AppliedProject;
    }

    public void setAppliedProject(String appliedProject) {
        AppliedProject = appliedProject;
    }

    public String getAppliedPost() {
        return AppliedPost;
    }

    public void setAppliedPost(String appliedPost) {
        AppliedPost = appliedPost;
    }

    public String getQualified() {
        return Qualified;
    }

    public void setQualified(String qualified) {
        Qualified = qualified;
    }

    public JRF_applications() {
    }
}