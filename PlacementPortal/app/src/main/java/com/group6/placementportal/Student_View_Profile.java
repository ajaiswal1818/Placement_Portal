package com.group6.placementportal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student_View_Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ExpandableListView listView, listView2;
    private ExpandableList_ViewProfile listAdapter;
    private List<String> listDataHeader;
    private Data data;
    private AcademicDetails acads;
    private PersonalDetails pers;
    private DatabaseReference reference;
    private HashMap<String, List<Data>> listHashMap;
    private Student user;
    private PublicClientApplication sampleApp;
    private int flag;

    private static final String TAG = Student_View_Profile.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__view__profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(isNetworkAvailable()==false){
            Toast.makeText(Student_View_Profile.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        user = (Student) getIntent().getSerializableExtra("user");
        reference = FirebaseDatabase.getInstance().getReference();

        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
        }

        listView = findViewById(R.id.edu);
        listView2 = findViewById(R.id.pd);

        initData();
        initData2();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void initData2() {


        reference.child("Student").child(user.getWebmailID()).child("PersonalDetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDataHeader = new ArrayList<>();
                listHashMap = new HashMap<>();
                boolean check = dataSnapshot.hasChild("PersonalDetails");
                if (check) {
                    pers = dataSnapshot.getValue(PersonalDetails.class);
                }
                if (pers == null) {
                    pers = new PersonalDetails("", "", "", "", "", "", "", "", "", "", "");
                }

                listDataHeader.add("Personal Details");

                List<Data> PersonalDetails = new ArrayList<>();

                data = new Data("Name", pers.getName());
                PersonalDetails.add(data);
                data = new Data("Father's Name", pers.getFather_Name());
                PersonalDetails.add(data);
                data = new Data("Date of Birth", pers.getDOB());
                PersonalDetails.add(data);
                data = new Data("Gender", pers.getGender());
                PersonalDetails.add(data);
                data = new Data("Category", pers.getCategory());
                PersonalDetails.add(data);
                data = new Data("Religion", pers.getReligion());
                PersonalDetails.add(data);
                data = new Data("State belongs to", pers.getState());
                PersonalDetails.add(data);
                data = new Data("Address", pers.getAddress());
                PersonalDetails.add(data);
                data = new Data("Mobile Number", pers.getMobile());
                PersonalDetails.add(data);
                data = new Data("Phone Number", pers.getPhone());
                PersonalDetails.add(data);
                data = new Data("Email Id", pers.getEmail());
                PersonalDetails.add(data);


                listHashMap.put(listDataHeader.get(0), PersonalDetails);
                listAdapter = new ExpandableList_ViewProfile(getApplicationContext(), listDataHeader, listHashMap);
                listView2.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void initData() {


        reference.child("Student").child(user.getWebmailID()).child("AcademicDetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDataHeader = new ArrayList<>();
                listHashMap = new HashMap<>();
                boolean check = dataSnapshot.hasChild("AcademicDetails");
                if (check) {
                    acads = dataSnapshot.getValue(AcademicDetails.class);
                }
                if (acads == null) {
                    acads = new AcademicDetails("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                }

                listDataHeader.add("Secondary");
                listDataHeader.add("Higher Secondary");
                listDataHeader.add("Graduation");

                List<Data> Secondary = new ArrayList<>();
                data = new Data("Percentage", acads.getSec_perc());
                Secondary.add(data);
                data = new Data("Year of Passing", acads.getSec_year());
                Secondary.add(data);
                data = new Data("Board", acads.getSec_board());
                Secondary.add(data);

                List<Data> HigherSecondary = new ArrayList<>();
                data = new Data("Percentage", acads.getHighsec_perc());
                HigherSecondary.add(data);
                data = new Data("Year of Passing", acads.getHighsec_year());
                HigherSecondary.add(data);
                data = new Data("Board", acads.getHighsec_board());
                HigherSecondary.add(data);

                List<Data> Graduation = new ArrayList<>();
                data = new Data("Course Name", acads.getCourse());
                Graduation.add(data);
                data = new Data("University Board", acads.getUniv_board());
                Graduation.add(data);
                data = new Data("Semester 1 CPI", acads.getSem1cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem1date());
                Graduation.add(data);
                data = new Data("Semester 2 CPI", acads.getSem2cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem2date());
                Graduation.add(data);
                data = new Data("Semester 3 CPI", acads.getSem3cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem3date());
                Graduation.add(data);
                data = new Data("Semester 4 CPI", acads.getSem4cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem4date());
                Graduation.add(data);
                data = new Data("Semester 5 CPI", acads.getSem5cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem5date());
                Graduation.add(data);
                data = new Data("Semester 6 CPI", acads.getSem6cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem6date());
                Graduation.add(data);
                data = new Data("Semester 7 CPI", acads.getSem7cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem7date());
                Graduation.add(data);
                data = new Data("Semester 8 CPI", acads.getSem8cpi());
                Graduation.add(data);
                data = new Data("Date of Passing", acads.getSem8date());
                Graduation.add(data);


                listHashMap.put(listDataHeader.get(0), Secondary);
                listHashMap.put(listDataHeader.get(1), HigherSecondary);
                listHashMap.put(listDataHeader.get(2), Graduation);
                listAdapter = new ExpandableList_ViewProfile(getApplicationContext(), listDataHeader, listHashMap);
                listView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_notifications) {
            Intent i = new Intent(getApplicationContext(), Student_Notifications.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_prefr_job) {
            Intent i = new Intent(getApplicationContext(), GivePreference.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_prefr_internships) {
            Intent i = new Intent(getApplicationContext(), GivePreference.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_jobs) {
            Intent i = new Intent(getApplicationContext(), View_Jobs.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_interns) {
            Intent i = new Intent(getApplicationContext(), View_Interns.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_my_profile) {
            Intent i = new Intent(getApplicationContext(), Student_View_Profile.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_edit_profile) {
            Intent i = new Intent(getApplicationContext(), Student_Complete_Profile.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_change_pass) {
            Intent i = new Intent(getApplicationContext(), Student_ChangePassword.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_applications) {
            Intent i = new Intent(getApplicationContext(), Student_Application_Forms.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_help) {
            Intent i = new Intent(getApplicationContext(), FAQ.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_inst_profile) {
            Intent i = new Intent(getApplicationContext(), Help_Students.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if(id == R.id.nav_signout){
            onSignOutClicked();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* Clears an account's tokens from the cache.
     * Logically similar to "sign out" but only signs out of this app.
     */
    private void onSignOutClicked() {

        /* Attempt to get a account and remove their cookies from cache */
        List<IAccount> accounts = null;

        try {
            accounts = sampleApp.getAccounts();

            if (accounts == null) {
                /* We have no accounts */
                updateSignedOutUI();

            } else if (accounts.size() == 1) {
                /* We have 1 account */
                /* Remove from token cache */
                sampleApp.removeAccount(accounts.get(0));
                updateSignedOutUI();

            }
            else {
                /* We have multiple accounts */
                for (int i = 0; i < accounts.size(); i++) {
                    sampleApp.removeAccount(accounts.get(i));
                }
                updateSignedOutUI();
            }

            Toast.makeText(getBaseContext(), "Signed Out!", Toast.LENGTH_SHORT).show();

        } catch (IndexOutOfBoundsException e) {
            Log.d(TAG, "User at this position does not exist: " + e.toString());
        }
    }

    private void updateSignedOutUI() {
        flag=1;
        Intent intent = new Intent(Student_View_Profile.this,LoginPage.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
        exit();
    }
    private void exit(){
        Student_View_Profile.this.finish();
    }
}
