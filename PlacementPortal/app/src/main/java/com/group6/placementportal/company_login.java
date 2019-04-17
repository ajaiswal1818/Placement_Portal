package com.group6.placementportal;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.apache.commons.io.HexDump;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;


public class company_login extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText username;
    private EditText password;
    private DatabaseReference valid;
    private boolean flag=true;
    Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login2);
        login= findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        valid= FirebaseDatabase.getInstance().getReference("Company");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                company c=null;
                Intent company_profile=new Intent(company_login.this, company_profile.class);
                company_profile.putExtra("MyClass",c);
                startActivity(company_profile);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valid.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            Iterable<DataSnapshot> all_children= dataSnapshot.getChildren();
                            for (DataSnapshot son : all_children)
                            {
                                if(son.child("username").exists())
                                {
                              //      Toast.makeText(company_login.this,son.child("username").getKey(),Toast.LENGTH_LONG).show();

                                    if(son.child("username").getValue().toString().equals(username.getText().toString()))
                                    {
                                        if(encryption.encryptOrNull(password.getText().toString()).equals(son.child("password").getValue().toString()))
                                        {
                                            company c = son.getValue(company.class);
                                            Intent company_dashboard=new Intent(company_login.this, company_dashboard.class);
                                            company_dashboard.putExtra("MyClass",c);
                                            //company_dashboard.putExtra("PrevActivity","company_login");
                                            finish();
                                            startActivity(company_dashboard);
                                        }
                                        else
                                        {
                                            Toast.makeText(company_login.this,"Invalid username or password",Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
