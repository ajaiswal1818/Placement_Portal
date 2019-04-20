package com.group6.placementportal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import com.group6.placementportal.DatabasePackage.JRF_applications;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.PersonalDetails;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Student_Profile_Approval extends AppCompatActivity {

    private ExpandableListView listView,listView2;
    private ExpandableList_ViewProfile listAdapter;
    private List<String> listDataHeader;
    private Data data;
    private AcademicDetails acads;
    private PersonalDetails pers;
    private DatabaseReference reference;
    private HashMap<String,List<Data>> listHashMap;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReference2;
    private DatabaseReference mDatabaseReference3;
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
        setContentView(R.layout.content_student__profile__approval);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(isNetworkAvailable()==false){
            Toast.makeText(Student_Profile_Approval.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        user = (Student) getIntent().getSerializableExtra("user");

        reference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference3 = FirebaseDatabase.getInstance().getReference();
        listView = findViewById(R.id.eduex);
        listView2 = findViewById(R.id.pdex);
        disapprove=findViewById(R.id.btn_disapprove1);
        approve=findViewById(R.id.btn_approve1);

        initData();
        initData2();

        disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        children = dataSnapshot.getChildrenCount();
                        children += 1;
                        ID = Long.toString(children);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                mDatabaseReference2 = reference.child("Student").child(user.getWebmailID());
                mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("List_of_Notification_IDs")) {
                            list_of_notif = dataSnapshot.child("List_of_Notification_IDs").getValue(String.class);
                            list_of_notif += ",";
                            list_of_notif += (ID);
                            mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);
                            notif = new Notifications();
                            notif.setDescription("Your PROFILE has been REJECTED");
                            notif.setNotification_ID(ID);
                            notif.setRead(false);
                            notif.setSubject("STUDENT PROFILE REQUEST");
                            reference.child("Notifications").child(ID).setValue(notif);
                            reference.child("Approve_Students").child(user.getWebmailID()).child("Action_Taken").setValue(true);
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Student_Profile_Approval.this);
                            mBuilder.setTitle("Student has been disapproved");
                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent company_login = new Intent(Student_Profile_Approval.this, Student_Requests.class);
                                    startActivity(company_login);
                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();
                        } else {
                            list_of_notif = "";
                            list_of_notif += ID;
                            mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);
                            notif = new Notifications();
                            notif.setDescription("Your PROFILE has been REJECTED");
                            notif.setNotification_ID(ID);
                            notif.setRead(false);
                            notif.setSubject("STUDENT PROFILE REQUEST");
                            reference.child("Notifications").child(ID).setValue(notif);
                            reference.child("Approve_Students").child(user.getWebmailID()).child("Action_Taken").setValue(true);
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Student_Profile_Approval.this);
                            mBuilder.setTitle("Student has been disapproved");
                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent company_login=new Intent(Student_Profile_Approval.this, Student_Requests.class);
                                    startActivity(company_login);
                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        children = dataSnapshot.getChildrenCount();
                        children += 1;
                        ID = Long.toString(children);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mDatabaseReference2 = reference.child("Student").child(user.getWebmailID());
                mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("List_of_Notification_IDs")) {
                            list_of_notif = dataSnapshot.child("List_of_Notification_IDs").getValue(String.class);
                            list_of_notif += ",";
                            list_of_notif += (ID);
                            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Student_Profile_Approval.this);
                            mBuilder.setTitle("Allocate the Interview dates");
                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);
                                    notif = new Notifications();
                                    notif.setDescription("Your profile request has been accepted");
                                    notif.setNotification_ID(ID);
                                    notif.setRead(false);
                                    notif.setSubject("STUDENT PROFILE REQUEST");
                                    reference.child("Notifications").child(ID).setValue(notif);
                                    mDatabaseReference.child("Student").child("AcademicDetails").setValue(acads);
                                    mDatabaseReference.child("Student").child("PersonalDetails").setValue(pers);
                                    reference.child("Approve_Students").child(user.getWebmailID()).child("Action_Taken").setValue(true);
                                }
                            });
                            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();


                        } else {
                            list_of_notif = "";
                            list_of_notif += ID;
                            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Student_Profile_Approval.this);
                            mBuilder.setTitle("Allocate the Interview dates");
                            mBuilder.setCancelable(false);
                            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDatabaseReference2.child("List_of_Notification_IDs").setValue(list_of_notif);
                                    notif = new Notifications();
                                    notif.setDescription("Your profile request has been accepted");
                                    notif.setNotification_ID(ID);
                                    notif.setRead(false);
                                    notif.setSubject("STUDENT PROFILE REQUEST");
                                    reference.child("Notifications").child(ID).setValue(notif);
                                    mDatabaseReference.child("Student").child("AcademicDetails").setValue(acads);
                                    mDatabaseReference.child("Student").child("PersonalDetails").setValue(pers);
                                    reference.child("Approve_Students").child(user.getWebmailID()).child("Action_Taken").setValue(true);
                                }
                            });
                            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


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

        reference.child("Approve_Students").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDataHeader = new ArrayList<>();
                listHashMap = new HashMap<>();
                boolean check = dataSnapshot.hasChild("PersonalDetails_Admin");
                if(check) {
                    pers = dataSnapshot.child("PersonalDetails_Admin").getValue(PersonalDetails.class);
                }
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
                    listAdapter = new com.group6.placementportal.ExpandableList_ViewProfile(getApplicationContext(),listDataHeader,listHashMap);
                    listView2.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void initData() {


        reference.child("Approve_Students").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDataHeader = new ArrayList<>();
                listHashMap = new HashMap<>();
                boolean check = dataSnapshot.hasChild("AcademicDetails_Admin");
                if(check) {
                    acads = dataSnapshot.child("AcademicDetails_Admin").getValue(AcademicDetails.class);
                }
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
                    listAdapter = new com.group6.placementportal.ExpandableList_ViewProfile(getApplicationContext(),listDataHeader,listHashMap);
                    listView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
