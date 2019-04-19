package com.example.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button Details;
    private Button Procedure;
    private Button Faq;
    private Button Contacts;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Details=(Button) findViewById(R.id.details);
        Faq=(Button) findViewById(R.id.faq);
        Procedure=(Button) findViewById(R.id.procedure);
        Contacts=(Button) findViewById(R.id.contacts);
        text = (TextView) findViewById(R.id.resume);
        text.setMovementMethod(LinkMovementMethod.getInstance());

        Procedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,procedure.class);
                startActivity(intent);
            }
        });

        Faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,faq.class);
                startActivity(intent);
            }
        });

        Contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,contacts.class);
                startActivity(intent);
            }
        });

        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,details.class);
                startActivity(intent);
            }
        });


    }
}
