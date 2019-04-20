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
        job_or_intern.this.finish();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(job_or_intern.this,company_dashboard.class);
        company c= (company) getIntent().getSerializableExtra("MyClass");

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("MyClass",c);
        startActivity(i);
       //job_or_intern.this.finish();
    }
}
