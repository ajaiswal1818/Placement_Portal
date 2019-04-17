package com.group6.placementportal;

import android.content.Intent;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.Collections;

public class Student_Notifications extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Student user;

    private int num;

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Notifications> list;
    private MyAdapter_Notifications adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__notifications);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        user = (Student) getIntent().getSerializableExtra("user");
        recyclerView = findViewById(R.id.recycler_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference().child("Notifications");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Notifications>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Notifications p = dataSnapshot1.getValue(Notifications.class);
                    String ID = p.getNotification_ID();
                    //TO DO TASK LEFT
                    //check for id in students list of notifications IDS and then add to the list
                    String list_of_IDs = user.getList_of_Notification_IDs();
                    String[] split_IDs = list_of_IDs.split("\\,");
                    boolean flag1 = false;
                    for (int i = 0; i < split_IDs.length; i++) {
                        Log.d("myTag", split_IDs[i] + " " + ID + " " + split_IDs[i].equals(ID));
                        if (split_IDs[i].equals(ID)) {
                            flag1 = true;
                        }
                    }
                    if (flag1 == true) {
                        list.add(p);
                    }
                }
                Collections.reverse(list);
                adapter = new MyAdapter_Notifications(Student_Notifications.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Student_Notifications.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_student__dashboard_drawer, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            Intent i = new Intent(getApplicationContext(), Student_Dashboard.class);
            i.putExtra("user", user);
            startActivity(i);

        } else if (id == R.id.nav_notifications) {
            Intent i = new Intent(getApplicationContext(), Student_Notifications.class);
            i.putExtra("user", user);
            startActivity(i);

        } else if (id == R.id.nav_prefr) {

        } else if (id == R.id.nav_company) {
            Intent i = new Intent(getApplicationContext(), View_Jobs.class);
            i.putExtra("user", user);
            startActivity(i);

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_edit_profile) {
            Intent i = new Intent(getApplicationContext(), Student_Profile.class);
            i.putExtra("user", user);
            startActivity(i);

        } else if (id == R.id.nav_change_pass) {
            Intent i = new Intent(getApplicationContext(), Student_ChangePassword.class);
            i.putExtra("user", user);
            startActivity(i);

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
