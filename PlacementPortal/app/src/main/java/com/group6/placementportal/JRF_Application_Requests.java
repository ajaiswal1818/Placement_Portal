package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.JRF_applications;

import java.util.ArrayList;

public class JRF_Application_Requests extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<JRF_applications> list;
    private Adapter_JRF_Admin adapter;
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jrf__application__requests);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(isNetworkAvailable()==false){
            Toast.makeText(JRF_Application_Requests.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView =findViewById(R.id.recycle_jrf);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("JRF").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    JRF_applications p = dataSnapshot1.getValue(JRF_applications.class);
                    if(!dataSnapshot1.hasChild("isChecked")){
                        list.add(p);
                    }
                }
                adapter = new Adapter_JRF_Admin(JRF_Application_Requests.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(JRF_Application_Requests.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(getApplicationContext(), JRF_Application_Requests.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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
                            Toast.makeText(JRF_Application_Requests.this, "No company with pending request", Toast.LENGTH_LONG).show();
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
        JRF_Application_Requests.this.finish();
    }
}
