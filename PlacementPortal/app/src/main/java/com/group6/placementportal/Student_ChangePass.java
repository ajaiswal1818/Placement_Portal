package com.group6.placementportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group6.placementportal.DatabasePackage.Student;

public class Student_ChangePass extends AppCompatActivity {

    private EditText CurrPass ;
    private EditText NewPass ;
    private EditText NewPass2;

    private String Pass = "";
    private String NPass = "";
    private String NPass2 = "";

    private String Prev_Pass;
    private String Username;

    private Button Change_Button;
    private Student student;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__change_pass);

        CurrPass = findViewById(R.id.editCurrPass);
        NewPass = findViewById(R.id.editNewPass);
        NewPass2 = findViewById(R.id.editNewPass2);
//      Get student from bundle
//      student= Bundle data
        Prev_Pass = student.getPassword();
        Username = student.getWebmailID();

        Change_Button = findViewById(R.id.btn_Change_Pass);
        Change_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Pass = CurrPass.getText().toString();
                NPass = NewPass.getText().toString();
                NPass2 = NewPass2.getText().toString();

                if(Prev_Pass.equals(Pass)){
                    if(NPass2.equals(NPass)){
                        student.setPassword(NPass);
                        mDatabase.child("Student").child(Username).child("Password").setValue(NPass);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }



}
