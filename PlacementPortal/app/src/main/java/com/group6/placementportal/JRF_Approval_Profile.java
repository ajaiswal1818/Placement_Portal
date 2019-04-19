package com.group6.placementportal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.AcademicDetails;
import com.group6.placementportal.DatabasePackage.Data;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.PersonalDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.RegistrationDetails;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JRF_Approval_Profile extends AppCompatActivity{

    private ExpandableListView listView,listView2,listView3;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private Data data;
    private AcademicDetails acads;
    private PersonalDetails pers;
    private RegistrationDetails registrationDetails;
    private DatabaseReference reference;
    private HashMap<String,List<Data>> listHashMap;
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
        setContentView(R.layout.content_jrf__approval__profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(isNetworkAvailable()==false) {
            Toast.makeText(JRF_Approval_Profile.this, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                context, JRF_Approval_Profile.this, startYear, starthMonth, startDay);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        user = (Student)getIntent().getSerializableExtra("user");
        reference = FirebaseDatabase.getInstance().getReference();
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
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void initData2() {


        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        reference.child("JRF").child(user.getWebmailID()).child("PersonalDetails").addListenerForSingleValueEvent(new ValueEventListener() {
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

        reference.child("JRF").child(user.getWebmailID()).child("AcademicDetails").addListenerForSingleValueEvent(new ValueEventListener() {
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

    private void initData3() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();
        listDataHeader.add("Registration Details");
        List<Data> RegRDetails = new ArrayList<>();
        reference.child("JRF").child(user.getWebmailID()).child("RegistrationDetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                registrationDetails = dataSnapshot.getValue(RegistrationDetails.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        data = new Data("Application No.",registrationDetails.getApplicationNo());
        RegRDetails.add(data);
        data = new Data("Programming Languages",registrationDetails.getProgrammingLanguage());
        RegRDetails.add(data);
        data = new Data("Year and Type of Experiences",registrationDetails.getYearAndExperience());
        RegRDetails.add(data);
        data = new Data("Applied For Project",registrationDetails.getProject());
        RegRDetails.add(data);
        data = new Data("Applied For Post",registrationDetails.getPost());
        RegRDetails.add(data);

        listHashMap.put(listDataHeader.get(0),RegRDetails);
    }


}
