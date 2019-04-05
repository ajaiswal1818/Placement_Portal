package com.group6.placementportal;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PasswordAuthentication;

public class LoginPage extends AppCompatActivity {

    private DatabaseReference Login_Details;
    private EditText Webmail;
    private EditText Password;
    private Button login_button;
    private String rollNo;
    private String password;
    private String check_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Login_Details= FirebaseDatabase.getInstance().getReference();

        //Taking Views From the screen
        Webmail=findViewById(R.id.input_email);
        Password=findViewById(R.id.input_password);
        login_button=findViewById(R.id.btn_login);
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                rollNo = Webmail.getText().toString();
                password = Password.getText().toString();
                check_password = Login_Details.child("Student").child(rollNo).child("Password").toString();
                if(check_password == password){
                    Toast.makeText(getBaseContext(),  "yes", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(),  "no", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
