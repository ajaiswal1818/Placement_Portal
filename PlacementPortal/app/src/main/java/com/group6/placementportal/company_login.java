package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.company;


public class company_login extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText username;
    private EditText password;
    private DatabaseReference valid;
    private boolean flag=true;
    public company c=null;
    Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);

    public company getUser(){
        return c;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login2);
        if(isNetworkAvailable()==false){
            Toast.makeText(company_login.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        login= findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        valid= FirebaseDatabase.getInstance().getReference("Company");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                company c=null;
                Intent company_profile=new Intent(company_login.this, company_profile.class);
                //  company_profile.putExtra("MyClass",c);
                company_profile.putExtra("coming_from","signup");
                startActivity(company_profile);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valid.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists() && dataSnapshot.hasChild(username.getText().toString()))
                        {
                            if(dataSnapshot.child(username.getText().toString()).child("approved").exists() && dataSnapshot.child(username.getText().toString()).child("approved").getValue().toString().equals("Approved"))
                            {
                                if (encryption.encryptOrNull(password.getText().toString()).equals(dataSnapshot.child(username.getText().toString()).child("password").getValue().toString())) {
                                    company c = dataSnapshot.child(username.getText().toString()).getValue(company.class);
                                    Intent company_dashboard = new Intent(company_login.this, company_dashboard.class);
                                    company_dashboard.putExtra("MyClass", c);
                                    //company_dashboard.putExtra("PrevActivity","company_login");
                                    finish();
                                    startActivity(company_dashboard);
                                }
                                else
                                {
                                    Toast.makeText(company_login.this,"Incorrect Login Credentials",Toast.LENGTH_LONG).show();
                                }
                            }
                            else if(dataSnapshot.child(username.getText().toString()).child("approved").getValue().toString().equals("Rejected"))
                            {
                                Toast.makeText(company_login.this,"Sorry, your request has been rejected by the admin.\nPlease contact admin for more details.",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(company_login.this,"Your request to admin is pending for approval, can't login now.",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(company_login.this,"Incorrect Login Credentials",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}