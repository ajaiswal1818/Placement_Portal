package com.group6.placementportal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class list_notification extends AppCompatActivity {

    RecyclerView mrecyclerView;
    notification_layout mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference db;
    // ArrayList<notification_card> list = new ArrayList<>();
    ArrayList<notification_card> cardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notification);


//            cardList.add(new notification_card("Line1"));
//            cardList.add(new notification_card("Line2"));
//            cardList.add(new notification_card("Line3"));
            mrecyclerView = findViewById(R.id.recycler);
            mrecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(list_notification.this);
        mrecyclerView.setLayoutManager(mLayoutManager);

            db = FirebaseDatabase.getInstance().getReference().child("notification_card");
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                            notification_card x = dataSnapshot1.getValue(notification_card.class);
                            assert x != null;
                            String subject = x.getText1();
                            String destination = x.getText2();
                            String pdflink = x.getText3();
                            cardList.add(new notification_card(subject, destination,pdflink));
                        }

                        mAdapter = new notification_layout(list_notification.this,cardList);
                        mrecyclerView.setAdapter(mAdapter);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(list_notification.this,"Something went Wrong",Toast.LENGTH_LONG).show();
                }
            });

    }

}
