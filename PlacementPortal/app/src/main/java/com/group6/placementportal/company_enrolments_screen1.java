package com.group6.placementportal;

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
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;

import java.util.ArrayList;

public class company_enrolments_screen1 extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Jobs> list;
    private enrolments_screen1_adpater adapter;
    private company c;
//    public company user=company_login.getUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_enrolments_screen1);
        this.c=(company) getIntent().getSerializableExtra("MyClass");
        recyclerView =findViewById(R.id.recycler_view_jobs_enrolments);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
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
                adapter = new enrolments_screen1_adpater(company_enrolments_screen1.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(company_enrolments_screen1.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
