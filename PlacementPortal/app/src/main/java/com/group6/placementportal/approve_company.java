package com.group6.placementportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import java.util.ArrayList;

public class approve_company extends AppCompatActivity {

    private String id;
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private approve_company_adapter adapter;
    private ArrayList<company> list;
    // private Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_company);

        // c = getIntent().getSerializableExtra("id").toString();

        recyclerView =findViewById(R.id.view_companies);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        // go=findViewById(R.id.go);
        reference = FirebaseDatabase.getInstance().getReference().child("Company");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        if(dataSnapshot1.child("approved").exists() && dataSnapshot1.child("approved").getValue().toString().equals("Pending"))
                        {
                            company c = dataSnapshot1.getValue(company.class);
                            list.add(c);
                        }
                    }
                    adapter = new approve_company_adapter(approve_company.this,list);
                    recyclerView.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(approve_company.this,"No companies to show with pending approval status",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(approve_company.this, "Oops ... something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

