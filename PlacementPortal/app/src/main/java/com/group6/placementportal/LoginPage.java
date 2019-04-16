package com.group6.placementportal;

import com.group6.placementportal.DatabasePackage.company;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.TokenWatcher;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.net.PasswordAuthentication;

public class LoginPage extends AppCompatActivity {

    private DatabaseReference Login_Details;
    private EditText Webmail;
    private EditText Password;
    private Button login_button;
    private String rollNo;
    private String password;
    private String check_password;
    public boolean isadmin=false;

    public void onadminclicked(View v){
        if(isadmin==true){
            isadmin=false;
        }
        else{
            isadmin=true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Login_Details= FirebaseDatabase.getInstance().getReference();

        //Taking Views From the screen
        Webmail=findViewById(R.id.input_email);
        Password=findViewById(R.id.input_password);
        login_button=findViewById(R.id.btn_login);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollNo = Webmail.getText().toString();
                password = Password.getText().toString();

                if(isadmin){
                    Intent admin_notices=new Intent(LoginPage.this, com.group6.placementportal.admin_notices.class);
                    startActivity(admin_notices);
                }
                else{
                    Intent company_login = new Intent(LoginPage.this, company_login.class);
                  //  Login_Details= FirebaseDatabase.getInstance().getReference();
                   // Login_Details = Login_Details.child("Student").child(rollNo).child("Password");
                    startActivity(company_login);
                }


               /* Login_Details.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        check_password = dataSnapshot.getValue(String.class);
                        Toast.makeText(LoginPage.this, check_password, Toast.LENGTH_LONG).show();
                        if(password.equals(check_password)){
                            Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginPage.this, "UNsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginPage.this, "UNsuccessfull", Toast.LENGTH_LONG).show();
                    }



                });*/



            }
        });

    }
}
