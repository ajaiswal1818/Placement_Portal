package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.group6.placementportal.DatabasePackage.company;

public class admin_job_or_intern extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(admin_job_or_intern.this,Admin_DashBoard.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    public void admin_pressed_job(View v){
        Intent act_enrollments=new Intent(this,admin_enrollments_screen1.class);

        act_enrollments.putExtra("is_job",true);
        startActivity(act_enrollments);
    }

    public void admin_pressed_intern(View v){

            Intent act_enrollments=new Intent(this,admin_enrollments_screen1.class);
            Log.w("Go to screen..","as intern to screen1");
            act_enrollments.putExtra("is_job",false);
            startActivity(act_enrollments);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_job_or_intern);

    }

}
