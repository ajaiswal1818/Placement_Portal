package com.group6.placementportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sending_emails extends AppCompatActivity {

    EditText et_email,et_subject,et_message;
    private DatabaseReference db;
    Button b_send;
    Button b_select;
    String[] listItems;
    boolean[] checkedItems;
    private ArrayList<String> selected_students_id =new ArrayList<>();
    private ArrayList<String> available_students_arraylist = new ArrayList<>();
    // private ArrayList<Integer> selected_companies = new ArrayList<>();
    private ProgressDialog dialog1;
//    private ProgressDialog progressDialog;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_emails);
        et_email=findViewById(R.id.email);
        et_subject=findViewById(R.id.subject);
        et_message=findViewById(R.id.message);
        b_send=findViewById(R.id.send);
        b_select=findViewById(R.id.select);
        f0();

        if(isNetworkAvailable()==false){
            Toast.makeText(sending_emails.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        listItems = getResources().getStringArray(R.array.student_name);
        checkedItems = new boolean[listItems.length];

        b_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(sending_emails.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked) {
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            }
                        }else{
                                if(mUserItems.contains(position)) {
                                    mUserItems.remove(position);
                                }
                        }
                    }
                });


                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + "; ";
                            }
                        }
                        et_email.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;

                        }
                        mUserItems.clear();
                        selected_students_id.clear();
                        et_email.setText("");
//                        for(int i=0; i< mUserItems.size();i++){
//                            checkedItems[i] = false;
//                            mUserItems.clear();
//                            et_email.setText("");
//                            mUserItems.clear();
//                        }
                    }
                });

                mBuilder.setNeutralButton("Select all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < listItems.length; i++) {
                            checkedItems[i] = true;
                            if (!mUserItems.contains(i)) {
                                mUserItems.add(i);
                            }
                        }
                       // Log.d("c_id_size",String.valueOf(mUserItems.size()));
                        selected_students_id.clear();
                        for (int i = 0; i < mUserItems.size(); i++) {
                            String str = listItems[mUserItems.get(i)];
                            String[] split_IDs = str.split("\\:");
                            str= split_IDs[0];
                            selected_students_id.add(str);
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = et_email.getText().toString();
                String subject = et_subject.getText().toString();
                String message = et_message.getText().toString();

                if (to.trim().equals("")) {
                    Toast.makeText(sending_emails.this, "Select at-least one from the Builder", Toast.LENGTH_SHORT).show();
                }
                else if (subject.trim().equals("")) {
                    Toast.makeText(sending_emails.this, "Select a subject", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    intent.setType("message/rfc822");

                    startActivity(Intent.createChooser(intent, "Select Email app "));
                }
            }
        });



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void convert_to_array()
    {
        listItems = new String[available_students_arraylist.size()];
        // ArrayList to Array Conversion
        for (int j = 0; j < available_students_arraylist.size(); j++) {

            // Assign each value to String array
            listItems[j] = available_students_arraylist.get(j);
        }
        checkedItems = new boolean[listItems.length];
        dialog1.setCancelable(true);
        dialog1.hide();
    }



    public void f2()
    {
        db= FirebaseDatabase.getInstance().getReference();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("Student"))
                    {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.child("Student").getChildren())
                        {
                            if(dataSnapshot1.hasChild("fullName") && dataSnapshot1.hasChild("webmailID") && dataSnapshot1.hasChild("has_given_preferences") && dataSnapshot1.child("has_given_preferences").getValue().toString().equals("Completed"))
                            {
                                available_students_arraylist.add(dataSnapshot1.child("webmailID").getValue().toString()+ "@iitg.ac.in");
                            }
                        }
                        convert_to_array();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(sending_emails.this,"Error in Fetching List, Populate the List in case there are none",Toast.LENGTH_LONG).show();
            }
        });

    }


    public void f0()
    {
        dialog1 = new ProgressDialog(sending_emails.this);
        dialog1.setMessage("Please Wait");
        dialog1.setCancelable(false);
        dialog1.show();
        f2();
        /*db=FirebaseDatabase.getInstance().getReference();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("notices2company"))
                    {
                        if(dataSnapshot.hasChild("total_count")){
                            count=dataSnapshot.child("total_count").getValue().toString();
                            Log.w("total_count",count);
                            f2();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
}