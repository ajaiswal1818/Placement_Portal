package com.group6.placementportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class sending_emails extends AppCompatActivity {

    EditText et_email,et_subject,et_message;
    Button b_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_emails);
        et_email=findViewById(R.id.email);
        et_subject=findViewById(R.id.subject);
        et_message=findViewById(R.id.message);
        b_send=findViewById(R.id.send);

        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = et_email.getText().toString();
                String subject = et_subject.getText().toString();
                String message = et_message.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("message/rfc822");

                startActivity(Intent.createChooser(intent,"Select Email app "));
            }
        });



    }
}
