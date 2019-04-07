package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class Student_Profile extends AppCompatActivity {

    private DatabaseReference Profile_Details;
    private EditText first_name_V;
    private EditText last_name_V;
    private EditText dept_V;
    private EditText prog_V;
    private Spinner gender_V;
    private EditText roll_no_V;
    private EditText year_of_graduation_V;
    private EditText contact_V;
    private EditText webmail_V;
    private EditText cpi_V;
    private Button btn_save_V;

    private String first_name;
    private String last_name;
    private String dept;
    private String prog;
    private String gender;
    private String roll_no;
    private String year_of_graduation;
    private String contact;
    private String webmail;
    private String cpi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Taking Views From the screen

        first_name_V=findViewById(R.id.first_name_text);
        last_name_V=findViewById(R.id.last_name_text);
        dept_V=findViewById(R.id.dept_text);
        prog_V=findViewById(R.id.prog_text);
        gender_V=findViewById(R.id.gender_spinner);
        roll_no_V=findViewById(R.id.roll_no_text);
        year_of_graduation_V=findViewById(R.id.year_text);
        contact_V=findViewById(R.id.contact_text);
        webmail_V=findViewById(R.id.webmail_text);
        cpi_V=findViewById(R.id.cpi_text);
        btn_save_V=findViewById(R.id.btn_savechanges);



        btn_save_V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name=first_name_V.getText().toString();
                last_name=last_name_V.getText().toString();
                dept=dept_V.getText().toString();
                prog=prog_V.getText().toString();
                gender=gender_V.getSelectedItem().toString();
                roll_no=roll_no_V.getText().toString();
                year_of_graduation=year_of_graduation_V.getText().toString();
                contact=contact_V.getText().toString();
                webmail=webmail_V.getText().toString();
                cpi=cpi_V.getText().toString();
                if(first_name.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(last_name.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(dept.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(prog.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(gender.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(roll_no.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(year_of_graduation.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(contact.isEmpty()){
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                if(cpi.isEmpty()) {
                    Toast.makeText(Student_Profile.this, "blahblah", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent(Student_Profile.this, Student_Dashboard.class);
                startActivity(i);
            }
        });

    }
}