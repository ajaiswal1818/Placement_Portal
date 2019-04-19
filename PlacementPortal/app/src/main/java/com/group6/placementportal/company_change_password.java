package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class company_change_password extends AppCompatActivity {
    private Button submit;
    private EditText old;
    private EditText new1;
    private EditText new2;
    private String c_id;
    private company c1;
    private DatabaseReference valid;
    Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_change_password);

        if(isNetworkAvailable()==false){
            Toast.makeText(company_change_password.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        c_id = (String) getIntent().getSerializableExtra("MyClassID");
        c1= (company) getIntent().getSerializableExtra("Class");
        submit=findViewById(R.id.submit);
        old=findViewById(R.id.old);
        new1=findViewById(R.id.new1);
        new2=findViewById(R.id.new2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(old.getText().toString().equals("")||new1.getText().toString().equals("")||new2.getText().toString().equals(""))
                {
                    Toast.makeText(company_change_password.this,"No field can be empty",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!new1.getText().toString().equals(new2.getText().toString()))
                {
                    Toast.makeText(company_change_password.this,"Re-enter new password correctly",Toast.LENGTH_LONG).show();
                    return;
                }
                if(new1.getText().toString().contains(" "))
                {
                    new1.setError("Password can't contain any spaces");
                    return;
                }
                else
                {
                    valid= FirebaseDatabase.getInstance().getReference("Company");
                    valid.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists())
                            {
                                if(dataSnapshot.child(c_id).exists() && dataSnapshot.child(c_id).child("password").exists())
                                {
                                    if (encryption.encryptOrNull(old.getText().toString()).equals(dataSnapshot.child(c_id).child("password").getValue().toString()))
                                    {
                                        valid.child(c_id).child("password").setValue(encryption.encryptOrNull(new1.getText().toString()));
                                        Toast.makeText(company_change_password.this,"Password changed successfully",Toast.LENGTH_LONG).show();
                                        old.setText("");
                                        new1.setText("");
                                        new2.setText("");
                                        Intent company_profile=new Intent(company_change_password.this, company_profile.class);
                                        company_profile.putExtra("MyClass",c1);
                                        company_profile.putExtra("coming_from","dashboard");
                                        //finish();
                                        startActivity(company_profile);
                                    }
                                    else
                                    {
                                        Toast.makeText(company_change_password.this,"Incorrect Current password",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
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
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
