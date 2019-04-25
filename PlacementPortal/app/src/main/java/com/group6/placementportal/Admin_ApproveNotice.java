package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notices;

import java.util.ArrayList;
import java.util.Collections;

public class Admin_ApproveNotice extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference reference;
    private DatabaseReference refnoticetocompany;
    private RecyclerView recyclerView;
    private ArrayList<Notices> list;
    private MyAdapter_Notices_FromCompany adapter;
    private int flag=0;
    private String key;

    private static final String TAG = Admin_ApproveNotice.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__approve_notice);
        if(isNetworkAvailable()==false){
            Toast.makeText(Admin_ApproveNotice.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = findViewById(R.id.recyclerNoticesfromCompany);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        refnoticetocompany = FirebaseDatabase.getInstance().getReference().child("noticestostudents");
        reference = FirebaseDatabase.getInstance().getReference().child("Notices");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Notices>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.child("hasApproved").getValue().equals("null")){
                        Notices p = dataSnapshot1.getValue(Notices.class);
                        list.add(p);
                    }

                }
                Collections.reverse(list);
                adapter = new MyAdapter_Notices_FromCompany(Admin_ApproveNotice.this,list);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MyAdapter_Notices_FromCompany.OnItemClickListener() {
                    @Override
                    public void rejectnotice(int position) {

                        final Notices check = list.get(position);

                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//                        Query a = ref.child("Notices");




                        ref.child("Notices").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                    if(snapshot.child("topic").getValue() == check.getTopic()){
//                                        key = snapshot.getKey();

                                        ref.child("Notices").child(snapshot.getKey()).child("hasApproved").setValue("False");

                                    }
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e(TAG, "onCancelled", databaseError.toException());
                            }
                        });


                    }

                    @Override
                    public void approvenotice(int position){

                        final Notices check = list.get(position);



                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//                        Query a = ref.child("Notices");




                        ref.child("Notices").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                    if(snapshot.child("topic").getValue() == check.getTopic()){
//                                        key = snapshot.getKey();

                                        ref.child("Notices").child(snapshot.getKey()).child("hasApproved").setValue("True");

                                        refnoticetocompany.child(snapshot.getKey()).setValue(check);
                                    }
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e(TAG, "onCancelled", databaseError.toException());
                            }
                        });

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Admin_ApproveNotice.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent i = new Intent(getApplicationContext(), MainLogin.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_jrf_application_requests) {
            Intent i = new Intent(getApplicationContext(), JRF_Application_Requests.class);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_approve_notice) {
            Intent i = new Intent(getApplicationContext(), Admin_ApproveNotice.class);
            startActivity(i);
            exit();

        } else if (id== R.id.Company_slots){
            Intent i = new Intent(getApplicationContext(), Company_Slots_Admin.class);
            startActivity(i);
            exit();

        } else if(id==R.id.nav_signout){
            Intent i = new Intent(getApplicationContext(), Login_Page_Admin.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_approve_company){
            reference= FirebaseDatabase.getInstance().getReference("Company");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        flag=0;
                        Iterable<DataSnapshot> all_children = dataSnapshot.getChildren();
                        for(DataSnapshot dataSnapshot1: all_children)
                        {
                            if(dataSnapshot1.child("approved").exists() && dataSnapshot1.child("approved").getValue().toString().equals("Pending"))
                            {
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0)
                        {
                            Toast.makeText(Admin_ApproveNotice.this, "No company with pending request", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent i = new Intent(getApplicationContext(), approve_company.class);
                            startActivity(i);
                            exit();
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Toast.makeText(approve_company.this, "Oops ... something is wrong", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (id== R.id.nav_manage_enroll){
            Intent i = new Intent(getApplicationContext(), admin_job_or_intern.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_notice_to_company){
            Intent i = new Intent(getApplicationContext(), admin_notices.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_checkout){
            Intent i = new Intent(getApplicationContext(), admin_checkout_the_portal.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_list_notifications){
            Intent i = new Intent(getApplicationContext(), list_notification.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_emails){
            Intent i = new Intent(getApplicationContext(), sending_emails.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_approve_student){
            Intent i = new Intent(getApplicationContext(), Student_Profile_Approval.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_ra_application_requests){
            Intent i = new Intent(getApplicationContext(), RA_Application_Requests.class);
            startActivity(i);
            exit();

        } else if (id== R.id.nav_change_pass){
            Intent i = new Intent(getApplicationContext(), admin_change_password.class);
            startActivity(i);
            exit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void exit(){
        Admin_ApproveNotice.this.finish();
    }
}
