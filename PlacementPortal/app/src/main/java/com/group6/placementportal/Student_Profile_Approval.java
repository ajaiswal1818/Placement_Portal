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
import com.group6.placementportal.DatabasePackage.AcademicDetails;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.PersonalDetails;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student_Profile_Approval extends AppCompatActivity {

    private ExpandableListView listView,listView2;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReference2;
    private DatabaseReference mDatabaseReference3;
    private Button disapprove;
    private Button approve;
    private Notifications notif;
    private Student user;
    private String list_of_notif;
    private long children;
    private AcademicDetails acad;
    private PersonalDetails pers;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__profile__approval);
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
        mDatabaseReference3 = FirebaseDatabase.getInstance().getReference();
        listView = findViewById(R.id.lv2Exp);
        listView2 = findViewById(R.id.lvExp);
        disapprove=findViewById(R.id.btn_Disapprove);
        approve=findViewById(R.id.btn_Approve);

        initData();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView.setAdapter(listAdapter);
        initData2();
        listAdapter = new com.group6.placementportal.ExpandableListAdapter(this,listDataHeader,listHashMap);
        listView2.setAdapter(listAdapter);


        disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                mDatabaseReference2 = mDatabaseReference2.child("Student").child("vakul170101076");
                mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list_of_notif = dataSnapshot.child("List_of_Notification_IDs").getValue(String.class);
                        list_of_notif+=",";
                        list_of_notif+=(Long.toString(children));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);

                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("Description").setValue("You have not been approved by the admin");
                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("Read").setValue("False");
                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("Subject").setValue("STUDENT APPROVAL/DISAPPROVAL");
                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("notification_ID").setValue(Long.toString(children));

            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDatabaseReference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        children = dataSnapshot.getChildrenCount();
                        acad=dataSnapshot.child("AcademicDetails_Temp").child("vakul170101076").getValue(AcademicDetails.class);
                        pers=dataSnapshot.child("PersonalDetails_Temp").child("vakul170101076").getValue(PersonalDetails.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                children+=1;
                mDatabaseReference2 = mDatabaseReference2.child("Student").child("vakul170101076");
                mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list_of_notif = dataSnapshot.child("List_of_Notification_IDs").getValue(String.class);
                        list_of_notif+=",";
                        list_of_notif+=(Long.toString(children));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);

                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("Description").setValue("You have been approved by the admin");
                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("Read").setValue("False");
                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("Subject").setValue("STUDENT APPROVAL/DISAPPROVAL");
                mDatabaseReference.child("Notifications").child(Long.toString(children)).child("notification_ID").setValue(Long.toString(children));

                mDatabaseReference.child("AcademicDetails").child("vakul170101076").setValue(acad);
                mDatabaseReference.child("PersonalDetails").child("vakul170101076").setValue(pers);
            }
        });
    }

    private void initData() {
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

}
