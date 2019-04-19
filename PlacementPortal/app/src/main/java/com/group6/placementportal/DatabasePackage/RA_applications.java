package com.group6.placementportal.DatabasePackage;

import android.widget.Toast;

import java.io.Serializable;

public class RA_applications implements Serializable {
    private String Name_of_Org;
    private String Designation;
    private String From_Duration;
    private String To_Duration;
    private String Type_of_Job;
    private String Student_Name;
    private String Interview_Date;
    private String Interview_Time;
    private String Webmail;

    public RA_applications() {
    }

    public RA_applications(String name_of_org, String designation, String from_duration, String to_duration, String appliedPost, String type_of_job, String student_Name, String interview_Date, String interview_Time, String webmail) {
        Name_of_Org = name_of_org;
        Designation = designation;
        From_Duration = from_duration;
        To_Duration = to_duration;
        Type_of_Job = type_of_job;
        Student_Name = student_Name;
        Interview_Date = interview_Date;
        Interview_Time = interview_Time;
        Webmail = webmail;
    }

    public String getName_of_Org() {
        return Name_of_Org;
    }

    public void setName_of_Org(String name_of_org) {
        Name_of_Org = name_of_org;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getFrom_Duration() {
        return From_Duration;
    }

    public void setFrom_Duration(String from_duration) {
        From_Duration = from_duration;
    }

    public String getTo_Duration() {
        return To_Duration;
    }

    public void setTo_Duration(String to_duration) {
        To_Duration = to_duration;
    }



    public String getType_of_Job() {
        return Type_of_Job;
    }

    public void setType_of_Job(String type_of_job) {
        Type_of_Job = type_of_job;
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