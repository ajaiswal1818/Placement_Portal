package com.group6.placementportal;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
=======
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.view.Menu;
=======
import android.util.Log;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.Student;
<<<<<<< HEAD

import java.util.ArrayList;
=======
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.ArrayList;
import java.util.List;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

public class View_Jobs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private MyAdapter adapter;
    private Student user;
<<<<<<< HEAD
=======
    private PublicClientApplication sampleApp;
    private int flag;

    private static final String TAG = Student_Dashboard.class.getSimpleName();
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__jobs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
=======
        if(isNetworkAvailable()==false){
            Toast.makeText(View_Jobs.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }


>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        user=(Student) getIntent().getSerializableExtra("user");

        recyclerView =findViewById(R.id.recycler_view_jobs);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

<<<<<<< HEAD
=======
        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
        }

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

        reference = FirebaseDatabase.getInstance().getReference().child("Jobs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Jobs>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Jobs p = dataSnapshot1.getValue(Jobs.class);
                    String branches = p.getBranches();
                    float cpi =p.getCutoff_cpi();
                    String[] split_branches =branches.split("\\.");
                    boolean flag1=false;
                    for(int i=0;i<split_branches.length;i++){
                        Log.d("myTag", split_branches[i]);
                        if(split_branches[i].equals(user.getDepartment())){
                            flag1=true;
                        }
                    }
                    boolean flag2=false;
<<<<<<< HEAD
                    if(cpi>=user.getCPI()){
=======
                    if(cpi<=user.getCPI()){
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                        flag2=true;
                }
                    Log.d("myTag", flag1+" ");
                    Log.d("myTag", flag2+" ");
<<<<<<< HEAD
                if(flag1==true && flag2==true){
=======
                if(flag1 && flag2){
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                    list.add(p);
                }

                }
<<<<<<< HEAD
                adapter = new MyAdapter(View_Jobs.this,list);
=======
                adapter = new MyAdapter(View_Jobs.this,list,user);
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(View_Jobs.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

<<<<<<< HEAD
=======
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }





>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
<<<<<<< HEAD
=======
            exit();
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

        } else if (id == R.id.nav_notifications) {
            Intent i = new Intent(getApplicationContext(), Student_Notifications.class);
            i.putExtra("user",user);
            startActivity(i);
<<<<<<< HEAD

        } else if (id == R.id.nav_prefr) {

        } else if (id == R.id.nav_company) {
            Intent i = new Intent(getApplicationContext(), View_Jobs.class);
            i.putExtra("user",user);
            startActivity(i);

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_edit_profile) {
            Intent i = new Intent(getApplicationContext(), Student_Profile.class);
=======
            exit();

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
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
            i.putExtra("user",user);
            startActivity(i);

        } else if (id == R.id.nav_change_pass) {
            Intent i = new Intent(getApplicationContext(), Student_ChangePassword.class);
            i.putExtra("user",user);
            startActivity(i);
<<<<<<< HEAD

        } else if (id == R.id.nav_help) {

=======
            exit();

        } else if (id == R.id.nav_applications) {
            Intent i = new Intent(getApplicationContext(), Student_Application_Forms.class);
            i.putExtra("user",user);
            startActivity(i);

        } else if (id == R.id.nav_help) {
            Intent i = new Intent(getApplicationContext(), FAQ.class);
            i.putExtra("user",user);
            startActivity(i);

        } else if (id == R.id.nav_inst_profile) {
            Intent i = new Intent(getApplicationContext(), Help_Students.class);
            i.putExtra("user",user);
            startActivity(i);

        } else if(id == R.id.nav_signout){
            onSignOutClicked();
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
<<<<<<< HEAD
=======

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
        Intent intent = new Intent(View_Jobs.this,LoginPage.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
        exit();
    }
    private void exit(){
        View_Jobs.this.finish();
    }
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
}
