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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notices;
import com.group6.placementportal.DatabasePackage.Student;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student_Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Notices> list;
    private MyAdapter_Notices adapter;
    private PublicClientApplication sampleApp;
    private Student user;
    private int flag;

    private static final String TAG = Student_Dashboard.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__notices);

        if(isNetworkAvailable()==false){
            Toast.makeText(Student_Dashboard.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }




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

        recyclerView = findViewById(R.id.recyclerNotices);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        Intent intent = new Intent(this, Student_Foreground_Notifications.class);
        intent.putExtra("user",user.getWebmailID());
        intent.putExtra("flag",0);
        startService(intent);



        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.Name_of_user);
        name.setText(user.getFullName());

        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
        }


        reference = FirebaseDatabase.getInstance().getReference().child("noticestostudents");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Notices>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Notices p = dataSnapshot1.getValue(Notices.class);
                    list.add(p);
                }
                Collections.reverse(list);
                adapter = new MyAdapter_Notices(Student_Dashboard.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Student_Dashboard.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        } else if (id == R.id.nav_prefr_job) {
            Intent i = new Intent(getApplicationContext(), GivePreference.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_prefr_internships) {
            Intent i = new Intent(getApplicationContext(), GivePreference_Interns.class);
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

        } else if (id == R.id.nav_change_pass) {
            Intent i = new Intent(getApplicationContext(), Student_ChangePassword.class);
            i.putExtra("user",user);
            startActivity(i);
            exit();

        } else if (id == R.id.nav_applications) {
            Intent i = new Intent(getApplicationContext(), Student_Application_Forms.class);
            i.putExtra("user",user);
            startActivity(i);

        } else if (id == R.id.nav_help) {
            Intent i = new Intent(getApplicationContext(), help.class);
            i.putExtra("user",user);
            startActivity(i);

        } else if (id == R.id.nav_inst_profile) {
            Intent i = new Intent(getApplicationContext(), Help_Students.class);
            i.putExtra("user",user);
            startActivity(i);

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
        Intent intent = new Intent(Student_Dashboard.this,LoginPage.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
        exit();
    }
    private void exit(){
        Student_Dashboard.this.finish();
    }
}
