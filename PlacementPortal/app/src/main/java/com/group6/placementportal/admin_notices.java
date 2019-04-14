package com.group6.placementportal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group6.placementportal.DatabasePackage.notices2company;

public class admin_notices extends AppCompatActivity {
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);

    }

    private EditText title;
    private EditText description;

    private notices2company new_notice;
    public void send_notice(View v){
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);

        if(description.getText()!=null && title.getText()!=null ){
            new_notice.setDescription(description.getText().toString());
            new_notice.setTitle(title.getText().toString());
        }

        db= FirebaseDatabase.getInstance().getReference("notices2company");
        db.setValue(new_notice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("Success!","Notice sent");
                }

                else{
                    Log.w("Failure:(","Notice not sent");
                }
            }
        });
    }
}
