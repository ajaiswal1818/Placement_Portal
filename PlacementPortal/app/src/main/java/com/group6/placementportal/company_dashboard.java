package com.group6.placementportal;

import android.app.Activity;
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
import com.google.firebase.database.FirebaseDatabase;
import com.group6.placementportal.DatabasePackage.company;

import static com.group6.placementportal.R.id.send_notification;


public class company_dashboard extends AppCompatActivity {

    private company c;
        private Activity activity;

        private DatabaseReference database;
        public void onAttach(Activity activity) {
                this.activity = activity;
        }

    private android.support.v7.widget.CardView manage_events;
    private android.support.v7.widget.CardView profile;
    private android.support.v7.widget.CardView notices;
    private android.support.v7.widget.CardView enrollments;
    private android.support.v7.widget.CardView events;
    private android.support.v7.widget.CardView notification;
    private android.support.v7.widget.CardView addnotices;


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_companydashboard);

            if(isNetworkAvailable()==false){
                Toast.makeText(company_dashboard.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
                return;
            }

              profile   = findViewById(R.id.profile_card);
              enrollments=findViewById(R.id.enrollments_card);
              manage_events= findViewById(R.id.book_card);
              database= FirebaseDatabase.getInstance().getReference();
              addnotices = findViewById(R.id.AddNotices);
              notification=findViewById(R.id.notification);


            this.c=(company) getIntent().getSerializableExtra("MyClass");
            profile   = findViewById(R.id.profile_card);
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent company_profile=new Intent(company_dashboard.this, company_profile.class);
                    company_profile.putExtra("MyClass",c);
                    company_profile.putExtra("coming_from","dashboard");
                    //finish();
                    startActivity(company_profile);
                }
            });

            notices=findViewById(R.id.notices_card);

            notices.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent company_notices=new Intent(company_dashboard.this, company_notices.class);
                    company_notices.putExtra("MyClass",c);
                    startActivity(company_notices);
                }
            });

            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(company_dashboard.this,Sending_Notifications.class);
                    i.putExtra("MyClass",c);
                    startActivity(i);
                }
            });
            enrollments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent company_enrollments_screen1=new Intent(company_dashboard.this,job_or_intern.class);
                    company_enrollments_screen1.putExtra("MyClass",c);
                    startActivity(company_enrollments_screen1);
                }
            });


            addnotices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent company_enrollements=new Intent(company_dashboard.this, NoticeFromCompany.class);
                    startActivity(company_enrollements);
                }
            });
            manage_events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent company_enrollements = new Intent(company_dashboard.this, Company_Application_Slots.class);
                        company_enrollements.putExtra("id", c.getCompany_id());
                        Toast.makeText(company_dashboard.this,c.getCompany_id(),Toast.LENGTH_SHORT).show();
                        startActivity(company_enrollements);

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
