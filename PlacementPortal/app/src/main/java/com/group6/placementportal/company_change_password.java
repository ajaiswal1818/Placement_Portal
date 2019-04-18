package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Student;
import com.microsoft.identity.client.PublicClientApplication;

public class company_change_password extends AppCompatActivity {
    private Button submit;
    private EditText old;
    private EditText new1;
    private EditText new2;
    private String c_id;
    private DatabaseReference valid;
    Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_change_password);
        c_id = (String) getIntent().getSerializableExtra("MyClassID");

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

}
