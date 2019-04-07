package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class Student_Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView name;
    private TextView webm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__dashboard);

        name = findViewById(R.id.Name_of_user);
//        name.setText("Anubhav Tyagi");                         // SET THIS VALUE TO CURRENT USER'S NAME

        webm = findViewById(R.id.Webmail_user);
//        webm.setText("tyagi170101009@iitg.ac.in");             // SET THIS VALUE TO CURRENT USER'S WEBMAIL

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            // Handle the camera action
        } else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_prefr) {

        } else if (id == R.id.nav_company) {

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
