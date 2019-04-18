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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.Student;
import com.group6.placementportal.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class GivePreference extends AppCompatActivity
        implements Adapter_Selected_Preferences.OnDragStartListener, NavigationView.OnNavigationItemSelectedListener  {
    private ItemTouchHelper mItemTouchHelper;

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private Adapter_Selected_Preferences adapter;
    private Student user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_preference);
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

        recyclerView = findViewById(R.id.recycle_give_preferences_selected);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);


        reference = FirebaseDatabase.getInstance().getReference().child("Jobs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Jobs>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Jobs p = dataSnapshot1.getValue(Jobs.class);
                    list.add(p);
                }
                adapter = new Adapter_Selected_Preferences(GivePreference.this, list, user, mDragStartListener);
                recyclerView.setAdapter(adapter);
            }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){
                    Toast.makeText(GivePreference.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }


        @Override
        public void onBackPressed () {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }


        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
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
    @Override
    public void onDragStarted(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    }
