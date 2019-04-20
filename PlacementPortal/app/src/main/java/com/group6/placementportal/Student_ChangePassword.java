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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group6.placementportal.DatabasePackage.Student;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.List;

public class Student_ChangePassword extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText CurrPass ;
    private EditText NewPass ;
    private EditText NewPass2;

    private String Pass = "";
    private String NPass = "";
    private String NPass2 = "";

    private String Prev_Pass;
    private String Username;

    private PublicClientApplication sampleApp;

    private Button Change_Button;
    private Student user;

    private int flag;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private static final String TAG = Student_Dashboard.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__change_password);

        if(isNetworkAvailable()==false){
            Toast.makeText(Student_ChangePassword.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
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

        CurrPass = findViewById(R.id.editCurrPass);
        NewPass = findViewById(R.id.editNewPass);
        NewPass2 = findViewById(R.id.editNewPass2);
//      Get student from bundle
//      student= Bundle data
        user = (Student) getIntent().getSerializableExtra("user");

        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
        }


        Change_Button = findViewById(R.id.btn_Change_Pass);
        Change_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Prev_Pass = user.getPassword();
                Username = user.getWebmailID();
                Pass = CurrPass.getText().toString();
                NPass = NewPass.getText().toString();
                NPass2 = NewPass2.getText().toString();
                Toast.makeText(getApplicationContext(), user.getPassword(), Toast.LENGTH_SHORT).show();
                if(Prev_Pass.equals(Pass)){
                    if(NPass2.equals(NPass)){
                        user.setPassword(NPass);
                        mDatabase.child("Student").child(Username).child("password").setValue(NPass);
                        NewPass.setText("");
                        NewPass2.setText("");
                        CurrPass.setText("");
                    }
                    else{
                        NewPass2.setError("Passwords do not match");
                        //Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    CurrPass.setError("Incorrect Password");
                    //Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
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
     * Logically similar to0 "sign out" but only signs out of this app.
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
        Intent intent = new Intent(Student_ChangePassword.this,LoginPage.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
        exit();
    }
    private void exit(){
        Student_ChangePassword.this.finish();
    }
}
