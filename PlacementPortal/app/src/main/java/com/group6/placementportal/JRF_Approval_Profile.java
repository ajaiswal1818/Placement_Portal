package com.group6.placementportal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.AcademicDetails;
import com.group6.placementportal.DatabasePackage.Data;
import com.group6.placementportal.DatabasePackage.PersonalDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JRF_Approval_Profile extends AppCompatActivity {

    private ExpandableListView listView,listView2;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<Data>> listHashMap;
    private DatabaseReference reference;
    private PersonalDetails pers;
    private AcademicDetails acads;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_complete_profile);
        listView = findViewById(R.id.lvExp);
        listView2 = findViewById(R.id.pdExp);
        reference = FirebaseDatabase.getInstance().getReference();
        initData();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView.setAdapter(listAdapter);
        initData2();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView2.setAdapter(listAdapter);

    }

    private void initData2() {


        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        reference.child("vakul170101076").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean check = dataSnapshot.hasChild("PersonalDetails");
                if(check){
                    pers = dataSnapshot.getValue(PersonalDetails.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(pers==null){
            pers = new PersonalDetails("","","","","","","","","","","");
        }

        listDataHeader.add("Personal Details");

        List<Data> PersonalDetails = new ArrayList<>();

        data = new Data("Name",pers.getName());
        PersonalDetails.add(data);
        data = new Data("Father's Name",pers.getFather_Name());
        PersonalDetails.add(data);
        data = new Data("Date of Birth",pers.getDOB());
        PersonalDetails.add(data);
        data = new Data("Gender",pers.getGender());
        PersonalDetails.add(data);
        data = new Data("Category",pers.getCategory());
        PersonalDetails.add(data);
        data = new Data("Religion",pers.getReligion());
        PersonalDetails.add(data);
        data = new Data("State belongs to",pers.getState());
        PersonalDetails.add(data);
        data = new Data("Address",pers.getAddress());
        PersonalDetails.add(data);
        data = new Data("Mobile Number",pers.getMobile());
        PersonalDetails.add(data);
        data = new Data("Phone Number",pers.getPhone());
        PersonalDetails.add(data);
        data = new Data("Email Id",pers.getEmail());
        PersonalDetails.add(data);


        listHashMap.put(listDataHeader.get(0),PersonalDetails);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        reference.child("vakul170101076").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean check = dataSnapshot.hasChild("AcademicDetails");
                if(check){
                    acads = dataSnapshot.getValue(AcademicDetails.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(acads==null){
            acads = new AcademicDetails("","","","","","","","","","","","","","","","","","","","","","","","");
        }

        listDataHeader.add("Secondary");
        listDataHeader.add("Higher Secondary");
        listDataHeader.add("Graduation");

        List<Data> Secondary = new ArrayList<>();
        data = new Data("Percentage",acads.getSec_perc());
        Secondary.add(data);
        data = new Data("Year of Passing",acads.getSec_year());
        Secondary.add(data);
        data = new Data("Board",acads.getSec_board());
        Secondary.add(data);

        List<Data> HigherSecondary = new ArrayList<>();
        data = new Data("Percentage",acads.getHighsec_perc());
        HigherSecondary.add(data);
        data = new Data("Year of Passing",acads.getHighsec_year());
        HigherSecondary.add(data);
        data = new Data("Board",acads.getHighsec_board());
        HigherSecondary.add(data);

        List<Data> Graduation = new ArrayList<>();
        data = new Data("Course Name",acads.getCourse());
        Graduation.add(data);
        data = new Data("University Board",acads.getUniv_board());
        Graduation.add(data);
        data = new Data("Semester 1 CPI",acads.getSem1cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem1date());
        Graduation.add(data);
        data = new Data("Semester 2 CPI",acads.getSem2cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem2date());
        Graduation.add(data);
        data = new Data("Semester 3 CPI",acads.getSem3cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem3date());
        Graduation.add(data);
        data = new Data("Semester 4 CPI",acads.getSem4cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem4date());
        Graduation.add(data);
        data = new Data("Semester 5 CPI",acads.getSem5cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem5date());
        Graduation.add(data);
        data = new Data("Semester 6 CPI",acads.getSem6cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem6date());
        Graduation.add(data);
        data = new Data("Semester 7 CPI",acads.getSem7cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem7date());
        Graduation.add(data);
        data = new Data("Semester 8 CPI",acads.getSem8cpi());
        Graduation.add(data);
        data = new Data("Date of Passing",acads.getSem8date());
        Graduation.add(data);


        listHashMap.put(listDataHeader.get(0),Secondary);
        listHashMap.put(listDataHeader.get(1),HigherSecondary);
        listHashMap.put(listDataHeader.get(2),Graduation);
    }

}
