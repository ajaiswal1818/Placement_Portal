package com.group6.placementportal;

import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class company_profile extends AppCompatActivity {

    private Button proc;
    private int c_id;
    private EditText name;
    private EditText sector;
    private EditText contact;
    private EditText email;
    private EditText hq;
    private ArrayList<job> jobs=null;
    public company_profile() {

    }

    private Activity activity;


    public void onAttach(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile2);
        proc=findViewById(R.id.proceed);
        name=findViewById(R.id.profile);
        sector=findViewById(R.id.ctc);
        contact=findViewById(R.id.location);
        email=findViewById(R.id.email);
        hq=findViewById(R.id.hq);

        //final company c = new company(name.getText().toString(),1,sector.getText().toString(),contact.getText().toString(),email.getText().toString(),hq.getText().toString(), jobs);
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent job_profile=new Intent(company_profile.this, job_profile.class);
                //job_profile.putExtra("MyClass",c);
                startActivity(job_profile);
            }
        });
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
