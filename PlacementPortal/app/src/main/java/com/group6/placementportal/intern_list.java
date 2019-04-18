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
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.intern;

import java.util.ArrayList;

public class intern_list extends AppCompatActivity {

    private String c;
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private intern_adapter adapter;
    private ArrayList<intern> list;
    private Button fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_list);

        c = getIntent().getSerializableExtra("id").toString();

        recyclerView =findViewById(R.id.add_intern_list);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        fab=findViewById(R.id.fab);
        reference = FirebaseDatabase.getInstance().getReference().child("Company").child(c).child("interns");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<intern>();
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        intern p = dataSnapshot1.getValue(intern.class);
                        list.add(p);
                    }
                    adapter = new intern_adapter(intern_list.this,list,c);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(intern_list.this, "Oops ... something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intern_profile=new Intent(intern_list.this, intern_profile.class);
                intern_profile.putExtra("MyClassID",c);
                intern_profile.putExtra("PrevActivity","intern_list");
                startActivity(intern_profile);
            }
        });
    }
}

