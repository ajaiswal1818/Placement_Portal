package com.group6.placementportal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.Collections;

public class notification_list extends AppCompatActivity {

    private Student user;
    private DatabaseReference reference;
    private ArrayList<Notifications> list;
    private MyAdapter_Notifications adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        recyclerView = findViewById(R.id.recycler_notifications);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference().child("Notifications_Admin");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Notifications>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Notifications p = dataSnapshot1.getValue(Notifications.class);
                    String ID= p.getNotification_ID();
                    //TO DO TASK LEFT
                    //check for id in students list of notifications IDS and then add to the list
                    String list_of_IDs=user.getList_of_Notification_IDs();
                    String[] split_IDs =list_of_IDs.split("\\,");
                    boolean flag1=false;
                    for(int i=0;i<split_IDs.length;i++){
                        Log.d("myTag", split_IDs[i]+" "+ID+" "+ split_IDs[i].equals(ID));
                        if(split_IDs[i].equals(ID)){
                            flag1=true;
                        }
                    }
                    if(flag1==true) {
                        list.add(p);
                    }
                }
                Collections.reverse(list);
                adapter = new MyAdapter_Notifications(notification_list.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(notification_list.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
