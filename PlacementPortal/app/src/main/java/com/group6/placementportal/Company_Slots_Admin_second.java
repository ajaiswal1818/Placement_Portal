package com.group6.placementportal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Company_Slots_Admin_second extends AppCompatActivity {
    private DatabaseReference database;
    private Spinner s1;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    String company_id;
    String company_name;
    company_details new_class;
    List<company_details> main_list= new ArrayList<company_details>();
    List<String> round_list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__slots__admin_second);

        s1=findViewById(R.id.spinner2);
        t1=findViewById(R.id.Company_id);
        t2=findViewById(R.id.Company_name);
        t3=findViewById(R.id.Place);
        t4=findViewById(R.id.Date);
        t5=findViewById(R.id.Timeslot);
        t6=findViewById(R.id.Type);
        company_id=getIntent().getStringExtra("id");
        company_name=getIntent().getStringExtra("name");

        database = FirebaseDatabase.getInstance().getReference().child("Company").child(company_id).child("Application_Slots");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    main_list.add(dataSnapshot1.getValue(company_details.class));
                    round_list.add(dataSnapshot1.getKey());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Company_Slots_Admin_second.this, android.R.layout.simple_spinner_item, round_list);
                    s1.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int round_id=position+1;
                String place= main_list.get(position).getPlace();
                String date= main_list.get(position).getDate();
                String time= main_list.get(position).getSlot();
                String type= main_list.get(position).getType();

                t1.setText("Company ID        :"+company_id);
                t2.setText("Company Name :"+company_name);
                t4.setText("Date Of Event    :"+date);
                t3.setText("Allocated Place :"+place);
                t5.setText("Time Slot           :"+time);
                t6.setText("Type Of Event   :"+type);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}