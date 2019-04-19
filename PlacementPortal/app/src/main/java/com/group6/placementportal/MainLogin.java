package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class MainLogin extends AppCompatActivity {

    private Button admin;
    private Button company;
    private Button student;
    private DatabaseReference reference;
    private int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        if(isNetworkAvailable()==false){
            Toast.makeText(MainLogin.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        admin =findViewById(R.id.admin);
        company = findViewById(R.id.company);
        student =findViewById(R.id.student);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent company_login=new Intent(MainLogin.this, approve_company.class);
                startActivity(company_login);
            }
        });
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent company_login=new Intent(MainLogin.this, company_login.class);
                startActivity(company_login);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent company_login=new Intent(MainLogin.this, LoginPage.class);
                startActivity(company_login);
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
