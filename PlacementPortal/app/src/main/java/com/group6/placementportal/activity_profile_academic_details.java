package com.group6.placementportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class activity_profile_academic_details extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_academic_details);
        listView = findViewById(R.id.lvExp);
        initData();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Secondary");
        listDataHeader.add("Higher Secondary");
        listDataHeader.add("Graduation");

        List<String> Secondary = new ArrayList<>();
        Secondary.add("Username");
        Secondary.add("Username");

        List<String> HigherSecondary = new ArrayList<>();
        HigherSecondary.add("Username");
        HigherSecondary.add("Username");

        List<String> Graduation = new ArrayList<>();
        Graduation.add("Username");
        Graduation.add("Username");

        listHashMap.put(listDataHeader.get(0),Secondary);
        listHashMap.put(listDataHeader.get(1),HigherSecondary);
        listHashMap.put(listDataHeader.get(2),Graduation);
    }
}
