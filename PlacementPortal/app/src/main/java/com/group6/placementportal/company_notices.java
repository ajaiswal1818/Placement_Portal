package com.group6.placementportal;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
=======
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

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
<<<<<<< HEAD
import com.group6.placementportal.DatabasePackage.notices2company;


public class company_notices extends AppCompatActivity {
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;
    private DatabaseReference db= FirebaseDatabase.getInstance().getReference("notices2company");

    protected void onStart(){
        super.onStart();


       /* db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                //cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);

                /*for (int i = 0; i < 10; i++) {
                    Card card = new Card("Card " + (i+1) + " Line 1", "Card " + (i+1) + " Line 2");
                    cardArrayAdapter.add(card);
                }

                for(DataSnapshot noticec : dataSnapshot.getChildren()){
                    notices2company the_notice=noticec.getValue(notices2company.class);
                  //  Card card=new Card(the_notice.getTitle().toString(),the_notice.getDescription().toString());
                   // cardArrayAdapter.add(card);
                    Log.d(the_notice.getTitle(), the_notice.getDescription());

                }
                //listView.setAdapter(cardArrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

=======
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
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_notices);

<<<<<<< HEAD
        //private listView=(ListView) listViewfindViewById(R.id.card_listView);

       listView = (ListView) findViewById(R.id.card_listView);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);


        listView.setAdapter(cardArrayAdapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.d("check",dataSnapshot.getRef().toString());
                    for(DataSnapshot notice :dataSnapshot.getChildren()){
                        // Log.d("check notices",notice.getRef().toString());
                        String key;
                        if( ! notice.getKey().toString().equals("total_count")){
                            Log.d("check check",notice.getRef().toString());
                            notices2company nc=notice.getValue(notices2company.class);
                            Card card_new = new Card(nc.getTitle().toString(), nc.getDescription().toString());
                            cardArrayAdapter.add(card_new);
                            Log.d(nc.getTitle().toString(),nc.getDescription().toString());
                        }

                    }
                    ListView listview=findViewById(R.id.card_listView);
                    listview.addHeaderView(new View(company_notices.this));
                    listview.addHeaderView(new View(company_notices.this));
                    listview.setAdapter(cardArrayAdapter);
=======
        if(isNetworkAvailable()==false){
            Toast.makeText(company_notices.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        c =(company) getIntent().getSerializableExtra("MyClass");

        recyclerView =findViewById(R.id.card_approve_company);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Company").child(c.getCompany_id()).child("notices");
        reference.addValueEventListener(new ValueEventListener() {
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
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
<<<<<<< HEAD

            }
        });



    }

}
=======
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


>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
