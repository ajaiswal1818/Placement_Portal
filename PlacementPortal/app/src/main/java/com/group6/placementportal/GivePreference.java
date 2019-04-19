package com.group6.placementportal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;

public class GivePreference extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecycleNameTouchHelper.AnimationListener  {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private Adapter_Selected_Preferences adapter;
    private Student user;
    private Button setPref;


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
        reference = FirebaseDatabase.getInstance().getReference();
        setPref = findViewById(R.id.btnapplyAs);
        user = (Student) getIntent().getSerializableExtra("user");

        boolean userApp = checkUserApplication();
        if(!userApp){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference.this);
            mBuilder.setTitle("You Already Set your preferences");
            mBuilder.setCancelable(false);
            mBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
            setPref.setVisibility(View.INVISIBLE);
        }
        else {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference.this);
            mBuilder.setTitle("You Can only Set Your preferences once");
            mBuilder.setCancelable(false);
            mBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        }
        AllowUsertogivePreferences();
        }

        public void  AllowUsertogivePreferences(){
            recyclerView = findViewById(R.id.recycle_give_preferences_selected);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);


            setPref.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GivePreference.this,"Hey",Toast.LENGTH_LONG).show();
                    String list_of_applied_companies="";
                    for(Jobs j: list){
                        String job_id = j.getJob_id();
                        if(list_of_applied_companies.equals("")){
                            list_of_applied_companies+=job_id;
                        }
                        else{
                            list_of_applied_companies+=",";
                            list_of_applied_companies+=job_id;
                        }
                    }
                    CAllDatabase(list_of_applied_companies);
                }
            });

            new ItemTouchHelper(new RecycleNameTouchHelper(this)).attachToRecyclerView(recyclerView);



            reference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String applied = dataSnapshot.child("preferences").getValue(String.class);
                    final String[] list_applied = applied.split("\\,");

                    reference.child("Jobs").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list = new ArrayList<>();

                            for (String jobId : list_applied) {
                                Jobs p = dataSnapshot.child(jobId).getValue(Jobs.class);
                                list.add(p);
                            }
                            adapter = new Adapter_Selected_Preferences(GivePreference.this, list, user);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){
                    Toast.makeText(GivePreference.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public boolean checkUserApplication(){
            final boolean[] userApplied = new boolean[1];
            reference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean checkChild = dataSnapshot.hasChild("has_given_preferences");
                    userApplied[0] = checkChild;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    userApplied[0] = false;
                }
            });
            return userApplied[0];
        }

        public void CAllDatabase(final String list_companies){
            Toast.makeText(GivePreference.this,"Hey Yo",Toast.LENGTH_LONG).show();
            reference.child("Student").child(user.getWebmailID()).child("preferences").setValue(list_companies);
            reference.child("Student").child(user.getWebmailID()).child("has_given_preferences").setValue("Completed");
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
                Intent i = new Intent(getApplicationContext(), GivePreference.class);
                i.putExtra("user", user);
                startActivity(i);

            } else if (id == R.id.nav_jobs) {
                Intent i = new Intent(getApplicationContext(), View_Jobs.class);
                i.putExtra("user", user);
                startActivity(i);

            } else if (id == R.id.nav_interns) {
                Intent i = new Intent(getApplicationContext(), View_Interns.class);
                i.putExtra("user", user);
                startActivity(i);

            } else if (id == R.id.nav_my_profile) {
                Intent i = new Intent(getApplicationContext(), Student_Complete_Profile.class);
                i.putExtra("user", user);
                startActivity(i);

            } else if (id == R.id.nav_edit_profile) {
                Intent i = new Intent(getApplicationContext(), Student_Complete_Profile.class);
                i.putExtra("user", user);
                startActivity(i);

            } else if (id == R.id.nav_change_pass) {
                Intent i = new Intent(getApplicationContext(), Student_ChangePassword.class);
                i.putExtra("user", user);
                startActivity(i);

            } else if (id == R.id.nav_help) {
                Intent i = new Intent(getApplicationContext(), Student_Application_Forms.class);
                i.putExtra("user", user);
                startActivity(i);
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    @Override
    public void onMove(int fromPos, int toPos) {
        list.add(toPos, list.remove(fromPos));
        adapter.notifyItemMoved(fromPos,toPos);
    }

    @Override
    public void onSwiped(int direction, int pos) {
    }
}
