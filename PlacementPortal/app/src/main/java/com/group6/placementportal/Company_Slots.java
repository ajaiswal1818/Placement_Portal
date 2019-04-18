package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Company_Slots extends AppCompatActivity {

    private EditText number;
    private Button Add;
    private int id;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__slots);
        if(isNetworkAvailable()==false){
            Toast.makeText(Company_Slots.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        number= findViewById(R.id.Number_Rounds);
        Add = findViewById(R.id.button_save);
        this.id=(int) getIntent().getSerializableExtra("id");
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance().getReference();
                database.child("Company").child(String.valueOf(id)).child("Application_Slots").child("Total_No_Rounds").setValue(number.getText().toString());
                Intent company_enrollements=new Intent(Company_Slots.this,Company_Application_Slots.class);
                company_enrollements.putExtra("id",id);
                startActivity(company_enrollements);
            }
        });

    }

}
