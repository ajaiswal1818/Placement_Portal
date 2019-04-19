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
import com.group6.placementportal.DatabasePackage.Notifications_Admin;

import java.util.ArrayList;

public class list_notification extends AppCompatActivity {

    RecyclerView mrecyclerView;
    notification_layout mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference db;
    // ArrayList<notification_card> list = new ArrayList<>();
    ArrayList<Notifications_Admin> cardList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notification);

        if(isNetworkAvailable()==false){
            Toast.makeText(list_notification.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }


//            cardList.add(new notification_card("Line1"));
//            cardList.add(new notification_card("Line2"));
//            cardList.add(new notification_card("Line3"));
        mrecyclerView = findViewById(R.id.recycler);
        mrecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(list_notification.this);
        mrecyclerView.setLayoutManager(mLayoutManager);

        db = FirebaseDatabase.getInstance().getReference().child("Notifications_Admin");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                        Notifications_Admin x = dataSnapshot1.getValue(Notifications_Admin.class);
                        assert x != null;
                        String subject = x.getSubject();
                        String destination = x.getDescription();
                        String pdflink = x.getPdflink();
                        String notification_id = x.getNotification_ID();

                        cardList.add(new Notifications_Admin(subject, destination,pdflink,notification_id));
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
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
