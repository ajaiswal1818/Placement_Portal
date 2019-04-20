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
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;
import com.group6.placementportal.DatabasePackage.notices2company;

import java.util.ArrayList;


public class company_notices extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private CardArrayAdapter adapter;
    private ArrayList<Card> list;
    private company c;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(company_notices.this,company_dashboard.class);
        i.putExtra("MyClass",c);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_notices);

        if(isNetworkAvailable()==false){
            Toast.makeText(company_notices.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        c =(company) getIntent().getSerializableExtra("MyClass");

        recyclerView =findViewById(R.id.card_approve_company);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Company").child(c.getCompany_id()).child("notices");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Card>();
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        notices2company new_notice = dataSnapshot1.getValue(notices2company.class);
                        Card p =new Card(new_notice.getTitle(),new_notice.getDescription(),new_notice.getFile());
                        list.add(p);
                    }
                    adapter = new CardArrayAdapter(company_notices.this,list);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(company_notices.this, "Oops ... something is wrong", Toast.LENGTH_LONG).show();
            }
        });
        adapter = new CardArrayAdapter(company_notices.this,list);
        recyclerView.setAdapter(adapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

