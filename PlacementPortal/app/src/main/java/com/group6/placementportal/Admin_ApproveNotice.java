package com.group6.placementportal;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notices;

import java.util.ArrayList;
import java.util.Collections;

public class Admin_ApproveNotice extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Notices> list;
    private MyAdapter_Notices_FromCompany adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__approve_notice);
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

        reference = FirebaseDatabase.getInstance().getReference().child("Notices");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Notices>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.getKey().equals("dummy")) continue;
                    Notices p = dataSnapshot1.getValue(Notices.class);
                    list.add(p);
                }
                Collections.reverse(list);
                adapter = new MyAdapter_Notices_FromCompany(Admin_ApproveNotice.this,list);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MyAdapter_Notices_FromCompany.OnItemClickListener() {
                    @Override
                    public void rejectnotice(int position) {
                        list.remove(position);

                    }

                    @Override
                    public void approvenotice(int position) {
                        list.remove(position);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Admin_ApproveNotice.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_admin__dash_board_drawer, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_jrf_application_requests) {
            // Handle the camera action
        } else if (id == R.id.nav_approve_notice) {
            Intent i = new Intent(getApplicationContext(), Admin_ApproveNotice.class);
            startActivity(i);
        }
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
