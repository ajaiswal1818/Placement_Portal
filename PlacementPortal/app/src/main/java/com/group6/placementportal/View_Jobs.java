package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
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
import com.group6.placementportal.DatabasePackage.Jobs;

import java.util.ArrayList;

public class View_Jobs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Jobs> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__jobs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_jobs);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference().child("Jobs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Jobs>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Jobs p = dataSnapshot1.getValue(Jobs.class);
//                    String branches = p.getBranches();
//                    float cpi =p.getCutoff_cpi();
//                    String[] split_branches =branches.split("\\.");
//                    boolean flag1=false;
//                    for(int i=0;i<split_branches.length;i++){
//                        Log.d("myTag", split_branches[i]);
//                        if(split_branches[i].equals("cse")){
//                            flag1=true;
//                        }
//                    }
//                    boolean flag2=false;
//                    if(cpi>=8){
//                        flag2=true;
//                }
//                    Log.d("myTag", flag1+" ");
//                    Log.d("myTag", flag2+" ");
//                if(flag1==true && flag2==true){
//
//                }
                    list.add(p);
                }
                adapter = new MyAdapter(View_Jobs.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(View_Jobs.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            Intent i = new Intent(getApplicationContext(), Student_Dashboard.class);
            startActivity(i);

        } else if (id == R.id.nav_notifications) {
            Intent i = new Intent(getApplicationContext(), Student_Notifications.class);
            startActivity(i);

        } else if (id == R.id.nav_prefr) {

        } else if (id == R.id.nav_company) {
            Intent i = new Intent(getApplicationContext(), View_Jobs.class);
            startActivity(i);

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_edit_profile) {
            Intent i = new Intent(getApplicationContext(), Student_Profile.class);
            startActivity(i);

        } else if (id == R.id.nav_change_pass) {
            Intent i = new Intent(getApplicationContext(), Student_ChangePass.class);
            startActivity(i);

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
