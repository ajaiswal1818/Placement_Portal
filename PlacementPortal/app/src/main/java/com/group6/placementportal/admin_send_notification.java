package com.group6.placementportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notifications;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;
import java.util.Collections;

public class admin_send_notification extends AppCompatActivity {


    private Button send_notification;
    private Button send_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_send_notification);
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
}
