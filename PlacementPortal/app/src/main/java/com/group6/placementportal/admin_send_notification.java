package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class admin_send_notification extends AppCompatActivity {


    private Button send_notification;
    private Button send_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_send_notification);
        if(isNetworkAvailable()==false){
            Toast.makeText(admin_send_notification.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        send_notification =findViewById(R.id.send_notification);
        send_mail = findViewById(R.id.send_mail);

        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_send_notification.this,sending_emails.class);
                startActivity(intent);
            }
        });

        send_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notification_list=new Intent(admin_send_notification.this, list_notification.class);
                startActivity(notification_list);

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
