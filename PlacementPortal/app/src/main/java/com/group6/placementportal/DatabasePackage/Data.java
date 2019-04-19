package com.group6.placementportal.DatabasePackage;

import java.io.Serializable;

public class Data implements Serializable {
    private String fieldname;
    private String fielddata;

    public Data() {
    }

    public Data(String fieldname, String fielddata) {
        this.fieldname = fieldname;
        this.fielddata = fielddata;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFielddata() {
        return fielddata;
    }

    public void setFielddata(String fielddata) {
        this.fielddata = fielddata;
    }
}
