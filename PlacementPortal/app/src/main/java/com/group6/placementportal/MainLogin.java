package com.group6.placementportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainLogin extends AppCompatActivity {

    private Button admin;
    private Button company;
    private Button student;
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

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent admin_enrollments_screen1=new Intent(MainLogin.this, com.group6.placementportal.admin_enrollments_screen1.class);
                //startActivity(admin_enrollments_screen1);
                Intent admin_checkout=new Intent(MainLogin.this,admin_checkout_the_portal.class);
                startActivity(admin_checkout);

            }
        });
    }
}
