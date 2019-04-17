package com.group6.placementportal.DatabasePackage;

import java.io.Serializable;

public class JRF_applications implements Serializable {
    private String Application_No;
    private String ProgrammingLanguages;
    private String YearandType;
    private String AppliedProject;
    private String AppliedPost;
    private String Qualified;
    private String Student_Name;
    private String Interview_Date;
    private String Interview_Time;
    private String Webmail;

    public JRF_applications() {
    }

    public JRF_applications(String application_No, String programmingLanguages, String yearandType, String appliedProject, String appliedPost, String qualified, String student_Name, String interview_Date, String interview_Time, String webmail) {
        Application_No = application_No;
        ProgrammingLanguages = programmingLanguages;
        YearandType = yearandType;
        AppliedProject = appliedProject;
        AppliedPost = appliedPost;
        Qualified = qualified;
        Student_Name = student_Name;
        Interview_Date = interview_Date;
        Interview_Time = interview_Time;
        Webmail = webmail;
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

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getInterview_Date() {
        return Interview_Date;
    }

    public void setInterview_Date(String interview_Date) {
        Interview_Date = interview_Date;
    }

    public String getInterview_Time() {
        return Interview_Time;
    }

    public void setInterview_Time(String interview_Time) {
        Interview_Time = interview_Time;
    }

    public String getWebmail() {
        return Webmail;
    }

    public void setWebmail(String webmail) {
        Webmail = webmail;
    }
}