package com.group6.placementportal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.notices2company;


public class company_notices extends AppCompatActivity {
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;
    private DatabaseReference db;
    private company c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_notices);

        if(isNetworkAvailable()==false){
            Toast.makeText(company_notices.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        c =(company) getIntent().getSerializableExtra("MyClass");
        listView = (ListView) findViewById(R.id.card_listView);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);

        listView.setAdapter(cardArrayAdapter);
        db=FirebaseDatabase.getInstance().getReference("Company").child(c.getCompany_id());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("notices"))
                    {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.child("notices").getChildren())
                        {
                            notices2company nc=dataSnapshot.getValue(notices2company.class);
                            Card card_new = new Card(nc.getTitle(), nc.getDescription(),nc.getFile());
                            cardArrayAdapter.add(card_new);
                        }
                    }
                    ListView listview=findViewById(R.id.card_listView);
                    listview.addHeaderView(new View(company_notices.this));
                    listview.addHeaderView(new View(company_notices.this));
                    listview.setAdapter(cardArrayAdapter);
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

}
