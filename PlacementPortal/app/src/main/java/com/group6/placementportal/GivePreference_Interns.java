package com.group6.placementportal;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Student;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.ArrayList;

public class GivePreference_Interns extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Interns> list;
    private Adapter_Selected_Preferences_Interns adapter;
    private Student user;
    private Button setPref;
    private PublicClientApplication sampleApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_preference__interns);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(isNetworkAvailable()==false){
            Toast.makeText(GivePreference_Interns.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        reference = FirebaseDatabase.getInstance().getReference();
        setPref = findViewById(R.id.btnapplyAs);
        user = (Student) getIntent().getSerializableExtra("user");

        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
        }

        reference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("has_given_preferences")){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference_Interns.this);
                    mBuilder.setTitle("You Already Set your preferences");
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                    setPref.setVisibility(View.INVISIBLE);
                    AllowUsertogivePreferences();
                }
                else {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference_Interns.this);
                    mBuilder.setTitle("You Can only Set Your preferences once");
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                    AllowUsertogivePreferences();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void  AllowUsertogivePreferences(){
        recyclerView = findViewById(R.id.recycle_give_preferences_interns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        setPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference_Interns.this);
                mBuilder.setTitle("You Can only Set Your preferences once");
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String list_of_applied_companies="";
                        if(list!=null) {
                            for (Interns j : list) {
                                String job_id = j.getIntern_id();
                                if (list_of_applied_companies.equals("")) {
                                    list_of_applied_companies += job_id;
                                } else {
                                    list_of_applied_companies += ",";
                                    list_of_applied_companies += job_id;
                                }
                            }
                        }else{
                            AlertDialog.Builder mBuilder1 = new AlertDialog.Builder(GivePreference_Interns.this);
                            mBuilder1.setTitle("You haven't applied for any Interns yet");
                            mBuilder1.setCancelable(false);
                            mBuilder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog mDialog = mBuilder1.create();
                            mDialog.show();
                        }
                        setPref.setVisibility(View.INVISIBLE);
                        CAllDatabase(list_of_applied_companies);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();


            }
        });

        new ItemTouchHelper(new RecycleNameTouchHelper((RecycleNameTouchHelper.AnimationListener) this)).attachToRecyclerView(recyclerView);



        reference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String applied = dataSnapshot.child("preferences").getValue(String.class);
                if(applied!=null && !applied.equals("")) {
                    final String[] list_applied = applied.split("\\,");

                    reference.child("Interns").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list = new ArrayList<>();

                            for (String jobId : list_applied) {
                                Log.d("mytag", jobId);
                                Interns p = dataSnapshot.child(jobId).getValue(Interns.class);
                                list.add(p);
                            }
                            adapter = new Adapter_Selected_Preferences_Interns(GivePreference_Interns.this, list, user);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
                Toast.makeText(GivePreference_Interns.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CAllDatabase(final String list_companies){
        reference.child("Student").child(user.getWebmailID()).child("preferences").setValue(list_companies);
        reference.child("Student").child(user.getWebmailID()).child("has_given_preferences").setValue("Completed");
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.give_preference__interns, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
