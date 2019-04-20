package com.group6.placementportal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.Student;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.ArrayList;
import java.util.List;

public class GivePreference extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecycleNameTouchHelper.AnimationListener  {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private Adapter_Selected_Preferences adapter;
    private Student user;
    private Button setPref;
    private PublicClientApplication sampleApp;
    private int flag;

    private static final String TAG = Student_Dashboard.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_preference);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(isNetworkAvailable()==false){
            Toast.makeText(GivePreference.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

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
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference.this);
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
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference.this);
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
            recyclerView = findViewById(R.id.recycle_give_preferences_selected);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);


            setPref.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(GivePreference.this);
                    mBuilder.setTitle("You Can only Set Your preferences once");
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String list_of_applied_companies="";
                            if(list!=null) {
                                for (Jobs j : list) {
                                    String job_id = j.getJob_id();
                                    if (list_of_applied_companies.equals("")) {
                                        list_of_applied_companies += job_id;
                                    } else {
                                        list_of_applied_companies += ",";
                                        list_of_applied_companies += job_id;
                                    }
                                }
                            }else{
                                AlertDialog.Builder mBuilder1 = new AlertDialog.Builder(GivePreference.this);
                                mBuilder1.setTitle("You haven't applied for any jobs yet");
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

            new ItemTouchHelper(new RecycleNameTouchHelper(this)).attachToRecyclerView(recyclerView);



            reference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String applied = dataSnapshot.child("preferences").getValue(String.class);
                    if(applied!=null && !applied.equals("")) {
                        final String[] list_applied = applied.split("\\,");

                        reference.child("Jobs").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                list = new ArrayList<>();

                                for (String jobId : list_applied) {
                                    Log.d(TAG, jobId);
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
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){
                    Toast.makeText(GivePreference.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void CAllDatabase(final String list_companies){
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
            Intent i = new Intent(getApplicationContext(), FAQ.class);
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
        Intent intent = new Intent(GivePreference.this,LoginPage.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
        exit();
    }
    private void exit(){
        GivePreference.this.finish();
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
