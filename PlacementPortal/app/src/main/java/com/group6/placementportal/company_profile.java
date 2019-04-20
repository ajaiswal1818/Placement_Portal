package com.group6.placementportal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import java.util.ArrayList;

public class company_profile extends AppCompatActivity {

    private String came_from;
    private company c1 = new company();
    private Button proc;
    private Button change_password;
    private Button goto_jobs;
    private Button goto_interns;
    private int c_id;
    private EditText name;
    private EditText sector;
    private EditText contact;
    private EditText email;
    private EditText hq;
    private EditText username;
    private EditText password;
    private EditText password2;
    private DatabaseReference valid;
    private ArrayList<job> jobs;
    private int flag;
    private long max_id;
    public company_profile() {
        jobs=new ArrayList<job>();
    }
    private ProgressDialog dialog1;
    private Activity activity;


    public void onAttach(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        company_profile.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile2);

        if(isNetworkAvailable()==false){
            Toast.makeText(company_profile.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        valid= FirebaseDatabase.getInstance().getReference("Company");
        valid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // it is first going to bottom code and then it comes to onDataChange
                // need to fix this
                if(dataSnapshot.exists()) {
                    String key = dataSnapshot.child("Max_entry").getValue().toString();
                    max_id = Integer.parseInt(key);

                }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* if((company) getIntent().getSerializableExtra("MyClass")!=null)
        {
            this.c=(company) getIntent().getSerializableExtra("MyClass");
        }*/
        came_from=(String) getIntent().getSerializableExtra("coming_from");
        if(came_from.equals("dashboard"))
        {
            c1=(company) getIntent().getSerializableExtra("MyClass");
        }

        proc=findViewById(R.id.proceed);
        name=findViewById(R.id.name);
        sector=findViewById(R.id.sector);
        contact=findViewById(R.id.contact);
        email=findViewById(R.id.email);
        hq=findViewById(R.id.hq);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        password2=findViewById(R.id.password2);
        change_password=findViewById(R.id.change_password);
        goto_jobs=findViewById(R.id.goto_jobs);
        goto_interns=findViewById(R.id.goto_interns);

        final Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);


        if(came_from.equals("dashboard"))
        {
            goto_jobs.setVisibility(View.VISIBLE);
            goto_interns.setVisibility(View.VISIBLE);
            name.setText(c1.getCompany_name());
            sector.setText(c1.getSector());
            contact.setText(c1.getContact_no());
            email.setText(c1.getEmail_address());
            hq.setText(c1.getHeadoffice());
            username.setText("Username : " + c1.getUsername());
            password.setVisibility(View.INVISIBLE);
            password2.setVisibility(View.INVISIBLE);
            change_password.setVisibility(View.VISIBLE);
            name.setEnabled(false);
            sector.setEnabled(false);
            username.setEnabled(false);
            proc.setText("Save Changes");
            proc.setEnabled(false);

            contact.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(contact.getText().toString().length()==0)
                    {
                        proc.setEnabled(true);
                        Toast.makeText(company_profile.this,"Can't leave any field empty",Toast.LENGTH_SHORT).show();
                    }
                    if(isNumeric(contact.getText().toString())==false){
                        contact.setError("Contact No. can contain only digits");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(contact.getText().toString().length()!=10){
                        contact.setError("Contact No. should be of 10 digits");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    proc.setEnabled(true);
                }
            });

            email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(email.getText().toString().length()==0)
                    {
                        proc.setEnabled(true);
                        Toast.makeText(company_profile.this,"Can't leave any field empty",Toast.LENGTH_SHORT).show();
                    }
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+[\\.+[a-z]+]+";
                    if(!email.getText().toString().matches(emailPattern))
                    {
                        email.setError("Email format is incorrect");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    proc.setEnabled(true);
                }
            });

            hq.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(hq.getText().toString().length()==0)
                    {
                        proc.setEnabled(true);
                        Toast.makeText(company_profile.this,"Can't leave any field empty",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    proc.setEnabled(true);
                }
            });



            proc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(contact.getText().toString().trim().equals("")||email.getText().toString().trim().equals("")||hq.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(company_profile.this,"Can't leave any field empty",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        proc.setEnabled(false);
                        c1.setContact_no(contact.getText().toString());
                        c1.setEmail_address(email.getText().toString());
                        c1.setHeadoffice(hq.getText().toString());

                        valid= FirebaseDatabase.getInstance().getReference("Company");
                        valid.child(c1.getCompany_id()).setValue(c1);
                        Toast.makeText(company_profile.this,"Changes saved",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            goto_jobs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    valid= FirebaseDatabase.getInstance().getReference("Company").child(c1.getCompany_id());
                    valid.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists())
                            {
                                if(dataSnapshot.hasChild("jobs"))
                                {
                                    Intent job_list=new Intent(company_profile.this, job_list.class);
                                    job_list.putExtra("id",c1.getCompany_id());
                                    startActivity(job_list);
                                }
                                else
                                {
                                    Intent job_profile=new Intent(company_profile.this, job_profile.class);
                                    job_profile.putExtra("MyClassID",c1.getCompany_id());
                                    job_profile.putExtra("PrevActivity","company_profile");
                                    startActivity(job_profile);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });


            goto_interns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    valid= FirebaseDatabase.getInstance().getReference("Company").child(c1.getCompany_id());
                    valid.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists())
                            {
                                if(dataSnapshot.hasChild("interns"))
                                {
                                    Intent intern_list=new Intent(company_profile.this, intern_list.class);
                                    intern_list.putExtra("id",c1.getCompany_id());
                                    startActivity(intern_list);
                                }
                                else
                                {
                                    Intent intern_profile=new Intent(company_profile.this, intern_profile.class);
                                    intern_profile.putExtra("MyClassID",c1.getCompany_id());
                                    intern_profile.putExtra("PrevActivity","company_profile");
                                    startActivity(intern_profile);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });
            change_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pass=new Intent(company_profile.this, company_change_password.class);
                    pass.putExtra("MyClassID",c1.getCompany_id());
                    pass.putExtra("Class",c1);
                    startActivity(pass);
                    company_profile.this.finish();
                }
            });


        }
        else
        {
            valid= FirebaseDatabase.getInstance().getReference("Company");
            valid.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    // it is first going to bottom code and then it comes to onDataChange
                    // need to fix this
                    if(dataSnapshot.exists()) {
                        String key = dataSnapshot.child("Max_entry").getValue().toString();
                        max_id = Integer.parseInt(key);

                    }}

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            goto_jobs.setVisibility(View.INVISIBLE);
            goto_interns.setVisibility(View.INVISIBLE);
            change_password.setVisibility(View.INVISIBLE);
            username.setEnabled(true);
            proc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(username.getText().toString().contains(".")||username.getText().toString().contains("_")||username.getText().toString().contains(",")||username.getText().toString().contains(":")||username.getText().toString().contains(" ")||username.getText().toString().contains(";")){
                        username.setError("Username can't contain any special characters ");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(isNumeric(contact.getText().toString())==false){
                        contact.setError("Contact No. can contain only digits");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(contact.getText().toString().length()!=10){
                        contact.setError("Contact No. should be of 10 digits");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(!(password.getText().toString().equals(password2.getText().toString())))
                    {
                        password2.setError("Re-enter correct password");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+[\\.+[a-z]+]+";
                    if(!email.getText().toString().matches(emailPattern))
                    {
                        email.setError("Email format is incorrect");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(name.getText().toString().trim().equals("")||sector.getText().toString().trim().equals("")||contact.getText().toString().trim().equals("")||email.getText().toString().trim().equals("")||hq.getText().toString().trim().equals("")||username.getText().toString().trim().equals("")||password.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(company_profile.this,"Can't leave any field empty",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(password.getText().toString().contains(" "))
                    {
                        password.setError("Password can't contain any spaces");
                        Toast.makeText(company_profile.this, "Invalid Input!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        dialog1 = new ProgressDialog(company_profile.this);
                        dialog1.setMessage("Please Wait");
                        dialog1.setCancelable(false);
                        dialog1.show();
                        valid= FirebaseDatabase.getInstance().getReference("Company");
                        valid.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                // it is first going to bottom code and then it comes to onDataChange
                                // need to fix this
                                if(dataSnapshot.exists()) {
                                    if (dataSnapshot.hasChild(username.getText().toString())) {
                                        Toast.makeText(company_profile.this, "Username already exists, choose another", Toast.LENGTH_LONG).show();
                                        dialog1.setCancelable(true);
                                        dialog1.hide();
                                        return;
                                    }
                                    else {
                                        company c = new company(name.getText().toString().trim(), "1", sector.getText().toString().trim(), contact.getText().toString().trim(), email.getText().toString().trim(), hq.getText().toString().trim(), username.getText().toString().trim(), encryption.encryptOrNull(password.getText().toString().trim()), "Pending");
                                        c.setCompany_id(username.getText().toString());
                                        valid.child(c.getCompany_id()).setValue(c);
                                        valid.child("Max_entry").setValue(String.valueOf(max_id+1));
                                        Toast.makeText(company_profile.this, "Your request has been sent to the admin for approval.", Toast.LENGTH_LONG).show();
                                        Intent company_login = new Intent(company_profile.this, company_login.class);
                                        finish();
                                        startActivity(company_login);
                                        dialog1.setCancelable(true);
                                        dialog1.hide();
                                    }
                                }
                                else
                                {
                                    company c = new company(name.getText().toString().trim(), "1", sector.getText().toString().trim(), contact.getText().toString().trim(), email.getText().toString().trim(), hq.getText().toString().trim(), username.getText().toString().trim(), encryption.encryptOrNull(password.getText().toString().trim()), "Pending");
                                    c.setCompany_id(username.getText().toString());
                                    valid.child(c.getCompany_id()).setValue(c);
                                    valid.child("Max_entry").setValue("1");
                                    Toast.makeText(company_profile.this, "Your request has been sent to the admin for approval.", Toast.LENGTH_LONG).show();
                                    Intent company_login = new Intent(company_profile.this, company_login.class);
                                    finish();
                                    startActivity(company_login);
                                    dialog1.setCancelable(true);
                                    dialog1.hide();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }
            });
        }


        /*Toast toast=Toast.makeText(getApplicationContext(),sector.getText().toString().trim(),Toast.LENGTH_SHORT);
                        toast.setMargin(50,50);
                        toast.show();*//*
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
                                    // jobs.set(0, null);

                                    //valid.child("Company").child("Max_entry").setValue(String.valueOf(max_id + 1));

                                    *//*valid.child("temp_company").child(String.valueOf(max_id + 1)).child("company_id").setValue(String.valueOf(max_id + 1));
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("company_name").setValue(name.getText().toString());
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("contact_no").setValue(contact.getText().toString());
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("email_address").setValue(email.getText().toString());
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("headoffice").setValue(hq.getText().toString());
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("sector").setValue(sector.getText().toString());
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("approved").setValue("Pending");
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("password").setValue(encryption.encryptOrNull(password.getText().toString()));
                                    valid.child("temp_company").child(String.valueOf(max_id + 1)).child("username").setValue(String.valueOf(max_id + 1));*//*



         *//*Intent job_profile=new Intent(company_profile.this, job_profile.class);
                            job_profile.putExtra("MyClass",c);
                            job_profile.putExtra("PrevActivity","company_profile");
                            startActivity(job_profile);*//*
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


*/

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

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
