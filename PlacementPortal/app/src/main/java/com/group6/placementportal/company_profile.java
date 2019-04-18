package com.group6.placementportal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class company_profile extends AppCompatActivity {

    private company c;
    private Button proc;
    private int c_id;
    private EditText name;
    private EditText sector;
    private EditText contact;
    private EditText email;
    private EditText hq;
    private EditText username;
    private EditText password;
    private DatabaseReference valid;
    private ArrayList<job> jobs;
    private int flag;
    public company_profile() {
        jobs=new ArrayList<job>();
    }

    private Activity activity;


    public void onAttach(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile2);
       /* if((company) getIntent().getSerializableExtra("MyClass")!=null)
        {
            this.c=(company) getIntent().getSerializableExtra("MyClass");
        }*/

        proc=findViewById(R.id.proceed);
        name=findViewById(R.id.sub);
        sector=findViewById(R.id.sector);
        contact=findViewById(R.id.contact);
        email=findViewById(R.id.email);
        hq=findViewById(R.id.hq);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        final Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);


        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().equals("")||sector.getText().toString().trim().equals("")||contact.getText().toString().trim().equals("")||email.getText().toString().trim().equals("")||hq.getText().toString().trim().equals("")||username.getText().toString().trim().equals("")||password.getText().toString().trim().equals(""))
                {
                    Toast.makeText(company_profile.this,"Can't leave any field empty",Toast.LENGTH_LONG).show();
                }
                else {
                /*Toast toast=Toast.makeText(getApplicationContext(),sector.getText().toString().trim(),Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();*/
                    flag=1;
                    valid= FirebaseDatabase.getInstance().getReference("Company");
                    valid.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // it is first going to bottom code and then it comes to onDataChange
                        // need to fix this
                        if(dataSnapshot.exists())
                        {
                            Iterable<DataSnapshot> all_children= dataSnapshot.getChildren();
                            for (DataSnapshot son : all_children)
                            {
                                if(son.child("username").exists() && son.child("username").getValue().toString().equals(username.getText().toString()))
                                {
                                    Toast.makeText(company_profile.this,"Username already exists, choose another",Toast.LENGTH_LONG).show();
                                    flag =0;
                                    break;
                                }
                            }
                        }
                        if(flag==1)
                        {
                            //Toast.makeText(company_profile.this,String.valueOf(flag),Toast.LENGTH_LONG).show();

                            company c = new company(name.getText().toString(),"1",sector.getText().toString(),contact.getText().toString(),email.getText().toString(),hq.getText().toString(), jobs,username.getText().toString(),encryption.encryptOrNull(password.getText().toString()));
                            Intent job_profile=new Intent(company_profile.this, job_profile.class);
                            job_profile.putExtra("MyClass",c);
                            job_profile.putExtra("PrevActivity","company_profile");
                            startActivity(job_profile);
                        }
                        else {
                            //Toast.makeText(company_profile.this,"vjhvugv"+ flag,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                    //Toast.makeText(company_profile.this,String.valueOf(flag),Toast.LENGTH_LONG).show();


                }
            }
        });
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
