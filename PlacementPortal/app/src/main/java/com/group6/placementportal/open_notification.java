package com.group6.placementportal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class open_notification extends AppCompatActivity {

    TextView a,b;
    Button btn;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_notification);
        if(isNetworkAvailable()==false){
            Toast.makeText(open_notification.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        a= findViewById(R.id.sub);
        b =findViewById(R.id.des);
        btn= findViewById(R.id.pdf_download);



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
