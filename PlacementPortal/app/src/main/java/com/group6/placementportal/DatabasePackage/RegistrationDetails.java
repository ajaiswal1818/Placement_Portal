package com.group6.placementportal.DatabasePackage;

public class RegistrationDetails {
    private String ApplicationNo;
    private String ProgrammingLanguage;
    private String YearAndExperience;
    private String Post;
    private String Project;
    private String Qualified;

    public RegistrationDetails() {
    }

    public RegistrationDetails(String applicationNo, String programmingLanguage, String yearAndExperience, String post, String project, String qualified) {
        ApplicationNo = applicationNo;
        ProgrammingLanguage = programmingLanguage;
        YearAndExperience = yearAndExperience;
        Post = post;
        Project = project;
        Qualified = qualified;
    }

    public String getQualified() {
        return Qualified;
    }

    public void setQualified(String qualified) {
        Qualified = qualified;
    }

    public String getApplicationNo() {
        return ApplicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        ApplicationNo = applicationNo;
    }

    public String getProgrammingLanguage() {
        return ProgrammingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        ProgrammingLanguage = programmingLanguage;
    }

    public String getYearAndExperience() {
        return YearAndExperience;
    }

    public void setYearAndExperience(String yearAndExperience) {
        YearAndExperience = yearAndExperience;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }
}
