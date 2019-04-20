package com.group6.placementportal;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
=======
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
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

public class MainLogin extends AppCompatActivity {

    private Button admin;
    private Button company;
    private Button student;
<<<<<<< HEAD
=======
    private DatabaseReference reference;
    private int flag=0;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
<<<<<<< HEAD
        admin =findViewById(R.id.admin);
        company = findViewById(R.id.company);
        student =findViewById(R.id.student);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

=======
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
                Intent company_login=new Intent(MainLogin.this, Login_Page_Admin.class);
                startActivity(company_login);
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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
<<<<<<< HEAD
    }
=======

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
}
