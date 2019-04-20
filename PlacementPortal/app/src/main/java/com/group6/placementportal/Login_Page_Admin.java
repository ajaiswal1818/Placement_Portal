package com.group6.placementportal;

import android.app.ProgressDialog;
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
import com.group6.placementportal.DatabasePackage.Admin;

public class Login_Page_Admin extends AppCompatActivity {

    private DatabaseReference Login_Details;
    private EditText Webmail;
    private EditText Password;
    private Button login_button;
    private Admin user;
    private String username;
    private String password;
    private String check_password;
    private String user_name;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page__admin);

        if(isNetworkAvailable()==false){
            Toast.makeText(Login_Page_Admin.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        Login_Details = FirebaseDatabase.getInstance().getReference();
        //Taking Views From the screen
        Webmail = findViewById(R.id.webmail_text);
        Password = findViewById(R.id.password_text);
        login_button = findViewById(R.id.btn_login);
        dialog = new ProgressDialog(Login_Page_Admin.this);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait");
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                username = Webmail.getText().toString();
                password = Password.getText().toString();

                Login_Details = FirebaseDatabase.getInstance().getReference();
                Login_Details = Login_Details.child("Admin");


                Login_Details.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        check_password = dataSnapshot.child("password").getValue(String.class);
                        user_name = dataSnapshot.child("username").getValue(String.class);
                        //Log.d("mytag", "fuck");
                        if (password.equals(check_password) && user_name.equals(username)) {
                            user = dataSnapshot.getValue(Admin.class);
                            dialog.hide();
                            updateSuccessUI();
                        } else {
                            dialog.hide();
                            Toast.makeText(Login_Page_Admin.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Login_Page_Admin.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                    }

                    private void updateSuccessUI() {
                        dialog.hide();
                        Intent I;
                        I = new Intent(getApplicationContext(), Student_Requests.class);
                        startActivity(I);
                        Login_Page_Admin.this.finish();

                    }
                });

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