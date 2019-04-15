package com.example.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText t1;
    EditText t2;
    EditText t3;
    EditText t4,t5;
    EditText t6,t7;
    EditText t8,t9;
    EditText t10;
    Firebase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(EditText)findViewById(R.id.Company_ID);
        t2=(EditText)findViewById(R.id.Round);
        t3=(EditText)findViewById(R.id.Rank);
        t4=(EditText)findViewById(R.id.Pref1_Date);
        t5=(EditText)findViewById(R.id.Pref1_Time);
        t6=(EditText)findViewById(R.id.Pref2_Date);
        t7=(EditText)findViewById(R.id.Pref2_Time);
        t8=(EditText)findViewById(R.id.Pref3_Date);
        t9=(EditText)findViewById(R.id.Pref3_Time);
        t10=(EditText)findViewById(R.id.type);

        Firebase.setAndroidContext(this);


    }

    public void Load_Click(View view){
        String company_id,round,company_rank,pref1_date,pref1_time,pref2_date,pref2_time,pref3_date,pref3_time;
        String type_round;

        company_id=t1.getText().toString();
        round=t2.getText().toString();
        company_rank=t3.getText().toString();
        pref1_date=t4.getText().toString();
        pref1_time=t5.getText().toString();
        pref2_date=t6.getText().toString();
        pref2_time=t7.getText().toString();
        pref3_date=t8.getText().toString();
        pref3_time=t9.getText().toString();
        type_round= t10.getText().toString();

        if (company_id.equals("")|round.equals("")|company_rank.equals("")|pref1_date.equals("")|pref1_time.equals("")|pref2_date.equals("")|pref2_time.equals("")|pref3_date.equals("")|pref3_time.equals("")){
            Toast.makeText(this,"All Values are not filled.",Toast.LENGTH_SHORT).show();
        }
        else{
            company_details new_company = new company_details();
            new_company.setCompany_id(company_id);
            new_company.setCompany_rank(company_rank);
            new_company.setRound(round);
            new_company.setPref1_date(pref1_date);
            new_company.setPref1_time(pref1_time);
            new_company.setPref2_date(pref2_date);
            new_company.setPref2_time(pref2_time);
            new_company.setPref3_date(pref3_date);
            new_company.setPref3_time(pref3_time);
            new_company.setResult_date("");
            new_company.setResult_time("");
            new_company.setType(type_round);
            database = new Firebase("https://placement-portal-89289.firebaseio.com/");
            database.child("Company").child(company_id).child("Application_Slots").child(round).setValue(new_company);

            Toast.makeText(this,"Added Successfully",Toast.LENGTH_SHORT).show();

        }

    }
    public void Next_Click(View view){
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

}
