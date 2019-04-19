package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.group6.placementportal.DatabasePackage.company;


public class job_or_intern extends AppCompatActivity {

    public void pressed_job(View v){
        Intent act_enrollments=new Intent(this,company_enrolments_screen1.class);
        company c= (company) getIntent().getSerializableExtra("MyClass");
        act_enrollments.putExtra("MyClass",c);
        act_enrollments.putExtra("is_job",true);
        startActivity(act_enrollments);
    }

    public void pressed_intern(View v){
        Intent act_enrollments=new Intent(this,company_enrolments_screen1.class);
        company c= (company) getIntent().getSerializableExtra("MyClass");
        act_enrollments.putExtra("MyClass",c);
        act_enrollments.putExtra("is_job",false);
        startActivity(act_enrollments);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_or_intern);

    }

}
