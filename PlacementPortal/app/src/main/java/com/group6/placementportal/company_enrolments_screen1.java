package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;

import java.util.ArrayList;

public class company_enrolments_screen1 extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private enrolments_screen1_adpater adapter;
    private  ArrayList<Interns> list_i;
    private company c;
    private enrolments_screen1_adapter_intern adapter_i;
//    public company user=company_login.getUser();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(company_enrolments_screen1.this,job_or_intern.class);
        this.c=(company) getIntent().getSerializableExtra("MyClass");
        i.putExtra("MyClass",c);
        startActivity(i);
        company_enrolments_screen1.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_enrolments_screen1);

        if(isNetworkAvailable()==false){
            Toast.makeText(company_enrolments_screen1.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        this.c=(company) getIntent().getSerializableExtra("MyClass");
        recyclerView =findViewById(R.id.recycler_view_jobs_enrolments);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        boolean is_job=getIntent().getBooleanExtra("is_job",true);
        if(is_job){
            reference = FirebaseDatabase.getInstance().getReference().child("Jobs");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list = new ArrayList<Jobs>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Jobs p = dataSnapshot1.getValue(Jobs.class);

                        String company_name=p.getCompany_id().toString();

                   /* if(user!=null){
                        if(user.getCompnany_id().equals(company_id)){
                            list.add(p);
                        }
                    }*/
                        if(c.getCompany_id().equals(company_name)){
                            // list.add(p);
                            Log.d("enrolments1",dataSnapshot1.getRef().toString());
                            list.add(p);

                        }





                    }
                    c=(company) getIntent().getSerializableExtra("MyClass");
                    adapter = new enrolments_screen1_adpater(company_enrolments_screen1.this,list,c);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(company_enrolments_screen1.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            reference = FirebaseDatabase.getInstance().getReference().child("Interns");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list_i = new ArrayList<Interns>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Interns p = dataSnapshot1.getValue(Interns.class);

                        String company_name=p.getCompany_id().toString();

                   /* if(user!=null){
                        if(user.getCompnany_id().equals(company_id)){
                            list.add(p);
                        }
                    }*/
                        if(c.getCompany_id().equals(company_name)){
                            // list.add(p);
                            Log.d("enrolments1",dataSnapshot1.getRef().toString());
                            list_i.add(p);

                        }





                    }
                    c=(company) getIntent().getSerializableExtra("MyClass");
                    adapter_i = new enrolments_screen1_adapter_intern(company_enrolments_screen1.this,list_i,c);
                    recyclerView.setAdapter(adapter_i);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(company_enrolments_screen1.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
