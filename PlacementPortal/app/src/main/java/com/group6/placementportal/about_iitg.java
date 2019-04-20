package com.group6.placementportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class about_iitg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_iitg);
    }
    public void Learn(View view) {
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iitg.ac.in/"));
        startActivity(browse);
    }

}
