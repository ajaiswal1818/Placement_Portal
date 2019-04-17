package com.group6.placementportal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JRF_Approval_Profile extends AppCompatActivity {

    private ExpandableListView listView,listView2,listView3;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReference2;
    private Button disapprove;
    private Button approve;
    private Notifications notif;
    private Student user;
    private String list_of_notif;
    private long children;
    private String ID;
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

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference();
        listView = findViewById(R.id.lv2Exp);
        listView2 = findViewById(R.id.lvExp);
        listView3 = findViewById(R.id.pdExp);
        disapprove=findViewById(R.id.btn_disapprove);
        approve=findViewById(R.id.btn_approve);

        initData();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView.setAdapter(listAdapter);
        initData2();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView2.setAdapter(listAdapter);
        initData3();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView3.setAdapter(listAdapter);


        disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference2 = mDatabaseReference2.child("Student").child("vakul170101076");
                mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list_of_notif = dataSnapshot.child("List_of_Notification_IDs").getValue(String.class);
                        list_of_notif+=",";
                        list_of_notif+=(notif.getNotification_ID());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);

                mDatabaseReference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        children = dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                children+=1;
                ID=Long.toString(children);

                mDatabaseReference.child("Notifications").child(notif.getNotification_ID()).child("Description").setValue("JRF application form rejected");
                mDatabaseReference.child("Notifications").child(notif.getNotification_ID()).child("Read").setValue("False");
                mDatabaseReference.child("Notifications").child(notif.getNotification_ID()).child("Subject").setValue("JRF APPLICATION REQUEST");
                mDatabaseReference.child("Notifications").child(notif.getNotification_ID()).child("notification_ID").setValue(notif.getNotification_ID());

            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference2 = mDatabaseReference2.child("Student").child("vakul170101076");
                mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list_of_notif = dataSnapshot.child("List_of_Notification_IDs").getValue(String.class);
                        list_of_notif+=",";
                        list_of_notif+=(notif.getNotification_ID());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);

                mDatabaseReference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        children = dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                children+=1;
                ID=Long.toString(children);
                mDatabaseReference.child("Notifications").child(ID).child("Description").setValue("JRF application form approved");
                mDatabaseReference.child("Notifications").child(ID).child("Read").setValue("False");
                mDatabaseReference.child("Notifications").child(ID).child("Subject").setValue("JRF APPLICATION REQUEST");
                mDatabaseReference.child("Notifications").child(ID).child("notification_ID").setValue(notif.getNotification_ID());

            }
        });

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
        RegRDetails.add("Application No.");
        RegRDetails.add("Programming Languages");
        RegRDetails.add("Year and Type of Experiences");
        RegRDetails.add("Applied For Project");
        RegRDetails.add("Applied For Post");
        listHashMap.put(listDataHeader.get(0),RegRDetails);
    }


}
