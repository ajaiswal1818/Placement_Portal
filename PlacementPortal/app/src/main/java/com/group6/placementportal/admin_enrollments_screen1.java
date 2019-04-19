package com.group6.placementportal;

import android.content.Context;
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

import java.util.ArrayList;

public class admin_enrollments_screen1 extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private  ArrayList<Interns> list_i;
    private admin_enrolments_screen1_adapter adapter;
    private admin_enrolments_screen1_adapter_intern adapter_i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_enrollments_screen1);
        if(isNetworkAvailable()==false){
            Toast.makeText(admin_enrollments_screen1.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        recyclerView =findViewById(R.id.recycler_view_jobs_enrolments_admin);
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

                        // String company_name=p.getCompany_name().toString();
                   /* if(user!=null){
                        if(user.getCompnany_id().equals(company_id)){
                            list.add(p);
                        }
                    }*/
                        list.add(p);
                   /* if(c.getCompany_name().equals(company_name)){
                        // list.add(p);
                        Log.d("enrolments1",dataSnapshot1.getRef().toString());
                    }*/





                    }

                    adapter = new admin_enrolments_screen1_adapter(admin_enrollments_screen1.this,list);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(admin_enrollments_screen1.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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

                        // String company_name=p.getCompany_name().toString();
                   /* if(user!=null){
                        if(user.getCompnany_id().equals(company_id)){
                            list.add(p);
                        }
                    }*/
                        list_i.add(p);
                   /* if(c.getCompany_name().equals(company_name)){
                        // list.add(p);
                        Log.d("enrolments1",dataSnapshot1.getRef().toString());
                    }*/





                    }

                    adapter_i = new admin_enrolments_screen1_adapter_intern(admin_enrollments_screen1.this,list_i);
                    Log.w("Intern..","adapter screen1_intern");
                    recyclerView.setAdapter(adapter_i);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(admin_enrollments_screen1.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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
