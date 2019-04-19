package com.group6.placementportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.company;

import java.util.ArrayList;

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
}
