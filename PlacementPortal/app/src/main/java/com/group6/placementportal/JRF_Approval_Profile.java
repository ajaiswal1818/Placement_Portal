package com.group6.placementportal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JRF_Approval_Profile extends AppCompatActivity {

    private ExpandableListView listView,listView2,listView3;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jrf__approval__profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = findViewById(R.id.lv2Exp);
        listView2 = findViewById(R.id.lvExp);
        listView3 = findViewById(R.id.pdExp);
        initData();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView.setAdapter(listAdapter);
        initData2();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView2.setAdapter(listAdapter);
        initData3();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView3.setAdapter(listAdapter);
    }

    private void initData3() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Personal Details");

        List<String> PersonalDetails = new ArrayList<>();
        PersonalDetails.add("Name");
        PersonalDetails.add("Father's Name");
        PersonalDetails.add("Date of Birth");
        PersonalDetails.add("Gender");
        PersonalDetails.add("Category");
        PersonalDetails.add("Religion");
        PersonalDetails.add("State belongs to");
        PersonalDetails.add("Address");
        PersonalDetails.add("Mobile Number");
        PersonalDetails.add("Phone Number");
        PersonalDetails.add("Email Id");

        listHashMap.put(listDataHeader.get(0),PersonalDetails);

    }

    private void initData2() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Secondary");
        listDataHeader.add("Higher Secondary");
        listDataHeader.add("Graduation");

        List<String> Secondary = new ArrayList<>();
        Secondary.add("Percentage");
        Secondary.add("Year of Passing");
        Secondary.add("Board");

        List<String> HigherSecondary = new ArrayList<>();
        HigherSecondary.add("Percentage");
        HigherSecondary.add("Year of Passing");
        HigherSecondary.add("Board");

        List<String> Graduation = new ArrayList<>();
        Graduation.add("Course Name");
        Graduation.add("University Board");
        Graduation.add("Semester 1 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 2 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 3 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 4 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 5 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 6 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 7 CPI");
        Graduation.add("Date of Passing");
        Graduation.add("Semester 8 CPI");
        Graduation.add("Date of Passing");

        listHashMap.put(listDataHeader.get(0),Secondary);
        listHashMap.put(listDataHeader.get(1),HigherSecondary);
        listHashMap.put(listDataHeader.get(2),Graduation);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();
        listDataHeader.add("Registration Details");
        List<String> RegRDetails = new ArrayList<>();
        RegRDetails.add("Name");
        RegRDetails.add("Father's Name");
        RegRDetails.add("Date of Birth");
        RegRDetails.add("Gender");
        RegRDetails.add("Category");
        listHashMap.put(listDataHeader.get(0),RegRDetails);
    }

}
