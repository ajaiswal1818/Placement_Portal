package com.group6.placementportal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Company_Slots_Admin extends AppCompatActivity {

    Context context;
    Company_Slots_customclass new_class;
    private DatabaseReference database;
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__slots__admin);
        context=this;

        if(isNetworkAvailable()==false){
            Toast.makeText(Company_Slots_Admin.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        final List<Company_Slots_customclass> main_list=new ArrayList<Company_Slots_customclass>();
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance().getReference().child("Company");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    if (dataSnapshot1.hasChild("Application_Slots")) {
                        String companyName = dataSnapshot1.child("company_name").getValue(String.class);
                        String company_id = dataSnapshot1.child("company_id").getValue(String.class);

                        new_class = new Company_Slots_customclass(companyName, company_id);

                        main_list.add(new_class);
                        Call_Adapter(main_list);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void Call_Adapter(List<Company_Slots_customclass> new_list){

        adapter= new Company_Slots_Admin_Adapter(new_list,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

}
