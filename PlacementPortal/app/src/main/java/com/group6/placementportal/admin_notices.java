package com.group6.placementportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.notices2company;

public class admin_notices extends AppCompatActivity {
    private DatabaseReference db=FirebaseDatabase.getInstance().getReference("notices2company");
    private EditText title;
    private EditText description;
    private notices2company new_notice=new notices2company();
    private double count_notices=0;
    private int tc;
    private String count="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);

      db.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              if(dataSnapshot.exists()){
                  if(dataSnapshot.hasChild("total_count")){
                      count=dataSnapshot.child("total_count").getValue().toString();
                      Log.d("total_count",count);
                      tc=Integer.parseInt(count);
                      tc++;
                      count=Integer.toString(tc);
                  }
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });

    }

    protected void onStart(){
        super.onStart();
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("total_count")){
                        count=dataSnapshot.child("total_count").getValue().toString();
                        Log.d("total_count",count);
                        tc=Integer.parseInt(count);
                        tc++;
                        count=Integer.toString(tc);
                    }

                }

               // db.child("total_count").setValue(Integer.toString(tc));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("total_count")){
                        count=dataSnapshot.child("total_count").getValue().toString();
                        Log.d("total_count",count);
                        tc=Integer.parseInt(count);
                        tc++;
                        count=Integer.toString(tc);
                    }

                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void open_recipients_tab(View v){
        Intent recipients=new Intent(admin_notices.this,recipients_tab.class);

        startActivity(recipients);
    }
    public void send_notice(View v){
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);

        if(description.getText()!=null && title.getText()!=null ){
            new_notice.setDescription(description.getText().toString());
            new_notice.setTitle(title.getText().toString());
        }

        db= FirebaseDatabase.getInstance().getReference("notices2company");

        db.child(Integer.toString(tc)).setValue(new_notice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d("Success!","Notice sent");
                    Toast.makeText(getApplicationContext(),"Notice Sent",Toast.LENGTH_SHORT).show();
                    title.setText("");
                    description.setText("");
                }

                else{
                    Log.w("Failure:(","Notice not sent");
                    Toast.makeText(getApplicationContext(),"Unable to send! Check your internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        db.child("total_count").setValue(count);
    }
}
