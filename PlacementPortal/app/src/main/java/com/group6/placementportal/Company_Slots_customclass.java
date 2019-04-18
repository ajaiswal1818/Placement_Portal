package com.group6.placementportal;

public class Company_Slots_customclass {
    String CompanyName;
    String CompanyId;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public Company_Slots_customclass(String companyName, String companyId) {
        CompanyName = companyName;
        CompanyId = companyId;
    }
}
