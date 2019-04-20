package com.group6.placementportal.DatabasePackage;

public class notices2company {
<<<<<<< HEAD
    /*public notices2company(String title, String description) {
        this.title = title;
        this.description = description;
    }*/

    private String title;
    private String description;
=======

    private String title;
    private String description;
    private String company_list;
    private String file;
    private String notice_id;

    public notices2company(String notice_id,String  title, String description, String company_list, String file) {
        this.notice_id=notice_id;
        this.title = title;
        this.description = description;
        this.company_list = company_list;
        this.file = file;
    }
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    public notices2company(){
        
    }

<<<<<<< HEAD
=======
    public String getCompany_list() {
        return company_list;
    }

    public void setCompany_list(String company_list) {
        this.company_list = company_list;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
