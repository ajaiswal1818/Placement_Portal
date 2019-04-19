package com.group6.placementportal;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import com.group6.placementportal.DatabasePackage.AcademicDetails;
import com.group6.placementportal.DatabasePackage.PersonalDetails;
import com.group6.placementportal.DatabasePackage.Student;

public class Student_Complete_Profile extends AppCompatActivity {

    private EditText sec_per,sec_year,sec_board,highsec_per,highsec_year,highsec_board;
    private EditText univ_board,course;
    private EditText sem1cpi,sem1yr;
    private EditText sem2cpi,sem2yr;
    private EditText sem3cpi,sem3yr;
    private EditText sem4cpi,sem4yr;
    private EditText sem5cpi,sem5yr;
    private EditText sem6cpi,sem6yr;
    private EditText sem7cpi,sem7yr;
    private EditText sem8cpi,sem8yr;

    private EditText name,father,dob,gender,category,religion,state,address,mobile,phone,email;
    private Button save,back;


    private String Ssec_per,Ssec_year,Ssec_board,Shighsec_per,Shighsec_year,Shighsec_board;
    private String Suniv_board,Scourse;
    private String Ssem1cpi,Ssem1yr;
    private String Ssem2cpi,Ssem2yr;
    private String Ssem3cpi,Ssem3yr;
    private String Ssem4cpi,Ssem4yr;
    private String Ssem5cpi,Ssem5yr;
    private String Ssem6cpi,Ssem6yr;
    private String Ssem7cpi,Ssem7yr;
    private String Ssem8cpi,Ssem8yr;

    private String Sname,Sfather,Sdob,Sgender,Scategory,Sreligion,Sstate,Saddress,Smobile,Sphone,Semail;

    private DatabaseReference databaseReference;

    private Student user;
    private AcademicDetails academicDetails;
    private PersonalDetails personalDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_complete_profile);

        if(isNetworkAvailable()==false){
            Toast.makeText(Student_Complete_Profile.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        user = (Student)getIntent().getSerializableExtra("user");

        sec_per = findViewById(R.id.txt_sec_perc);
        sec_year = findViewById(R.id.txt_sec_yop);
        sec_board = findViewById(R.id.txt_sec_board);

        highsec_board=findViewById(R.id.txt_hsec_board);
        highsec_per=findViewById(R.id.txt_hsec_perc);
        highsec_year=findViewById(R.id.txt_hsec_yop);

        univ_board = findViewById(R.id.txt_g_ub);
        course = findViewById(R.id.txt_g_course);

        sem1cpi = findViewById(R.id.txt_g_cpi1);
        sem2cpi = findViewById(R.id.txt_g_cpi2);
        sem3cpi = findViewById(R.id.txt_g_cpi3);
        sem4cpi = findViewById(R.id.txt_g_cpi4);
        sem5cpi = findViewById(R.id.txt_g_cpi5);
        sem6cpi = findViewById(R.id.txt_g_cpi6);
        sem7cpi = findViewById(R.id.txt_g_cpi7);
        sem8cpi = findViewById(R.id.txt_g_cpi8);

        sem1yr = findViewById(R.id.txt_g_yop1);
        sem2yr = findViewById(R.id.txt_g_yop2);
        sem3yr = findViewById(R.id.txt_g_yop3);
        sem4yr = findViewById(R.id.txt_g_yop4);
        sem5yr = findViewById(R.id.txt_g_yop5);
        sem6yr = findViewById(R.id.txt_g_yop6);
        sem7yr = findViewById(R.id.txt_g_yop7);
        sem8yr = findViewById(R.id.txt_g_yop8);

        name= findViewById(R.id.txt_name);
        father = findViewById(R.id.txt_father_name);
        dob = findViewById(R.id.txt_dob);
        gender = findViewById(R.id.txt_gender);
        category = findViewById(R.id.txt_category);
        religion = findViewById(R.id.txt_religion);
        state = findViewById(R.id.txt_state);
        address = findViewById(R.id.txt_address);
        mobile = findViewById(R.id.txt_mobile);
        phone = findViewById(R.id.txt_phone);
        email = findViewById(R.id.txt_email);

        save = findViewById(R.id.btn_save);
        back = findViewById(R.id.btn_back);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        boolean status = checkUserProfileStatus();

        if (status){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Student_Complete_Profile.this);
            mBuilder.setTitle("Your previous details are still pending to be approved");
            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
            save.setVisibility(View.INVISIBLE);
            setTextBoxes();
        }
        else{
            setTextBoxes();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                checkData();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getData(){
        Ssec_per=sec_per.getText().toString();
        Ssec_board=sec_board.getText().toString();
        Ssec_year=sec_year.getText().toString();

        Shighsec_year=highsec_year.getText().toString();
        Shighsec_board=highsec_board.getText().toString();
        Shighsec_per=highsec_per.getText().toString();

        Suniv_board=univ_board.getText().toString();
        Scourse=course.getText().toString();

        Ssem1cpi=sem1cpi.getText().toString();
        Ssem2cpi=sem2cpi.getText().toString();
        Ssem3cpi=sem3cpi.getText().toString();
        Ssem4cpi=sem4cpi.getText().toString();
        Ssem5cpi=sem5cpi.getText().toString();
        Ssem6cpi=sem6cpi.getText().toString();
        Ssem7cpi=sem7cpi.getText().toString();
        Ssem8cpi=sem8cpi.getText().toString();

        Ssem1yr=sem1yr.getText().toString();
        Ssem2yr=sem2yr.getText().toString();
        Ssem3yr=sem3yr.getText().toString();
        Ssem4yr=sem4yr.getText().toString();
        Ssem5yr=sem5yr.getText().toString();
        Ssem6yr=sem6yr.getText().toString();
        Ssem7yr=sem7yr.getText().toString();
        Ssem8yr=sem8yr.getText().toString();

        Sname=name.getText().toString();
        Sfather=father.getText().toString();
        Sdob=dob.getText().toString();
        Sgender=gender.getText().toString();
        Scategory=category.getText().toString();
        Sreligion=religion.getText().toString();
        Sstate=state.getText().toString();
        Saddress=address.getText().toString();
        Smobile=mobile.getText().toString();
        Sphone=phone.getText().toString();
        Semail=email.getText().toString();
    }

    public void checkData(){
        if(Ssec_board.equals("")){
            sec_board.setError("Empty Field not allowed");
        }
        if(Ssec_per.equals("")){
            sec_per.setError("Empty Field not Allowed");
        }
        if(Ssec_year.equals("")){
            sec_year.setError("Empty Fields not allowed");
        }

        if(Shighsec_board.equals("")){
            highsec_board.setError("Empty Field not allowed");
        }
        if(Shighsec_per.equals("")){
            highsec_per.setError("Empty Field not Allowed");
        }
        if(Shighsec_year.equals("")){
            highsec_year.setError("Empty Fields not allowed");
        }

        if(Suniv_board.equals("")){
            univ_board.setError("Empty Field not Allowed");
        }
        if(Scourse.equals("")){
            course.setError("Empty Field not Allowed");
        }

        if(Ssem1cpi.equals("")){
            sem1cpi.setError("Empty Field not Allowed");
        }
        if(Ssem2cpi.equals("")){
            sem2cpi.setError("Empty Field not Allowed");
        }
        if(Ssem3cpi.equals("")){
            sem3cpi.setError("Empty Field not Allowed");
        }
        if(Ssem4cpi.equals("")){
            sem4cpi.setError("Empty Field not Allowed");
        }

        if(Ssem1yr.equals("")){
            sem1yr.setError("Empty Field not Allowed");
        }
        if(Ssem2yr.equals("")){
            sem2yr.setError("Empty Field not Allowed");
        }
        if(Ssem3yr.equals("")){
            sem3yr.setError("Empty Field not Allowed");
        }if(Ssem4yr.equals("")){
            sem4yr.setError("Empty Field not Allowed");
        }

        if(Ssem5cpi.equals("") && (!Ssem6cpi.equals("") || !Ssem7cpi.equals("") || !Ssem8cpi.equals("") || !Ssem5yr.equals("") || !Ssem6yr.equals("") || !Ssem7yr.equals("") || !Ssem8yr.equals(""))){
            sem5cpi.setError("Cannot fill details of furthur semester without filling previous info");
        }
        if(Ssem6cpi.equals("") && (Ssem7cpi.equals("") || !Ssem8cpi.equals("") || !Ssem6yr.equals("") || !Ssem7yr.equals("") || !Ssem8yr.equals(""))){
            sem6cpi.setError("Cannot fill details of furthur semester without filling previous info");
        }
        if(Ssem7cpi.equals("") && (!Ssem8cpi.equals("") || !Ssem7yr.equals("") || !Ssem8yr.equals(""))){
            sem7cpi.setError("Cannot fill details of furthur semester without filling previous info");
        }
        if(Ssem8cpi.equals("") && (!Ssem8yr.equals(""))){
            sem8cpi.setError("Cannot fill details of this semester");
        }

        if(Ssem5yr.equals("") && (!Ssem6yr.equals("") || !Ssem7yr.equals("") || !Ssem8yr.equals("") || !Ssem5cpi.equals("") || !Ssem6cpi.equals("") || !Ssem7cpi.equals("") || !Ssem8cpi.equals(""))){
            sem5yr.setText("Cannot fill details of furthur semester without filling previous info");
        }
        if(Ssem6yr.equals("") && (Ssem7yr.equals("") || !Ssem8yr.equals("") || !Ssem6cpi.equals("") || !Ssem7cpi.equals("") || !Ssem8cpi.equals(""))){
            sem6yr.setText("Cannot fill details of furthur semester without filling previous info");
        }
        if(Ssem7yr.equals("") && (!Ssem8yr.equals("") || !Ssem7cpi.equals("") || !Ssem8cpi.equals(""))){
            sem7yr.setText("Cannot fill details of furthur semester without filling previous info");
        }
        if(Ssem8yr.equals("") && (!Ssem8cpi.equals(""))){
            sem8yr.setText("Cannot fill details of this semester");
        }

        if(Sname.equals("")){
            name.setError("Required Field");
        }
        if(Sfather.equals("")){
            father.setError("Required Field");
        }
        if(Sdob.equals("")){
            dob.setError("Required Field");
        }
        if(Sgender.equals("")){
            gender.setError("Required Field");
        }
        if(Scategory.equals("")){
            category.setError("Required Field");
        }
        if(Sreligion.equals("")){
            religion.setError("Required Field");
        }
        if(Sstate.equals("")){
            state.setError("Required Field");
        }
        if(Saddress.equals("")){
            address.setError("Required Field");
        }
        if(Smobile.equals("")){
            mobile.setError("Required Field");
        }
        if(Sphone.equals("")){
            phone.setError("Required Field");
        }
        if(Semail.equals("")){
            email.setError("Required Field");
        }

    }

    public void setTextBoxes(){
        if(academicDetails==null){
            sec_per.setText("");
            sec_board.setText("");
            sec_year.setText("");

            highsec_year.setText("");
            highsec_board.setText("");
            highsec_per.setText("");

            univ_board.setText("");
            course.setText("");

            sem1cpi.setText("");
            sem2cpi.setText("");
            sem3cpi.setText("");
            sem4cpi.setText("");
            sem5cpi.setText("");
            sem6cpi.setText("");
            sem7cpi.setText("");
            sem8cpi.setText("");

            sem1yr.setText("");
            sem2yr.setText("");
            sem3yr.setText("");
            sem4yr.setText("");
            sem5yr.setText("");
            sem6yr.setText("");
            sem7yr.setText("");
            sem8yr.setText("");

        }
        else{
            sec_per.setText(academicDetails.getSec_perc());
            sec_board.setText(academicDetails.getSec_board());
            sec_year.setText(academicDetails.getSec_year());

            highsec_year.setText(academicDetails.getHighsec_year());
            highsec_board.setText(academicDetails.getHighsec_board());
            highsec_per.setText(academicDetails.getHighsec_perc());

            univ_board.setText(academicDetails.getUniv_board());
            course.setText(academicDetails.getCourse());

            if(academicDetails.getSem1cpi()==null){
                sem1cpi.setText("");
                sem1yr.setText("");
            }
            else{
                sem1cpi.setText(academicDetails.getSem1cpi());
                sem1yr.setText(academicDetails.getSem1date());
            }

            if(academicDetails.getSem2cpi()==null){
                sem2cpi.setText("");
                sem2yr.setText("");
            }
            else{
                sem2cpi.setText(academicDetails.getSem2cpi());
                sem2yr.setText(academicDetails.getSem2date());
            }

            if(academicDetails.getSem3cpi()==null){
                sem3cpi.setText("");
                sem3yr.setText("");
            }
            else{
                sem3cpi.setText(academicDetails.getSem3cpi());
                sem3yr.setText(academicDetails.getSem3date());
            }

            if(academicDetails.getSem4cpi()==null){
                sem4cpi.setText("");
                sem4yr.setText("");
            }
            else{
                sem4cpi.setText(academicDetails.getSem4cpi());
                sem4yr.setText(academicDetails.getSem4date());
            }

            if(academicDetails.getSem5cpi()==null){
                sem5cpi.setText("");
                sem5yr.setText("");
            }
            else{
                sem5cpi.setText(academicDetails.getSem5cpi());
                sem5yr.setText(academicDetails.getSem5date());
            }

            if(academicDetails.getSem6cpi()==null){
                sem6cpi.setText("");
                sem6yr.setText("");
            }
            else{
                sem6cpi.setText(academicDetails.getSem6cpi());
                sem6yr.setText(academicDetails.getSem6date());
            }

            if(academicDetails.getSem7cpi()==null){
                sem7cpi.setText("");
                sem7yr.setText("");
            }
            else{
                sem7cpi.setText(academicDetails.getSem7cpi());
                sem7yr.setText(academicDetails.getSem7date());
            }

            if(academicDetails.getSem8cpi()==null){
                sem8cpi.setText("");
                sem8yr.setText("");
            }
            else{
                sem8cpi.setText(academicDetails.getSem8cpi());
                sem8yr.setText(academicDetails.getSem8date());
            }
        }

        if(personalDetails==null){
            name.setText("");
            father.setText("");
            dob.setText("");
            gender.setText("");
            category.setText("");
            religion.setText("");
            state.setText("");
            address.setText("");
            mobile.setText("");
            phone.setText("");
            email.setText("");
        }
        else{
            name.setText(personalDetails.getName());
            father.setText(personalDetails.getFather_Name());
            dob.setText(personalDetails.getDOB());
            gender.setText(personalDetails.getGender());
            category.setText(personalDetails.getCategory());
            religion.setText(personalDetails.getReligion());
            state.setText(personalDetails.getState());
            address.setText(personalDetails.getAddress());
            mobile.setText(personalDetails.getMobile());
            phone.setText(personalDetails.getPhone());
            email.setText(personalDetails.getEmail());
        }
    }

    public boolean checkUserProfileStatus(){
        final boolean[] status = {true};
        databaseReference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("ProfilePending")){
                    String profileStatus = dataSnapshot.child("ProfilePending").getValue(String.class);
                    if(profileStatus.equals("Pending")){
                        status[0] = true;
                    }
                    else{
                        status[0]=false;
                        if(dataSnapshot.hasChild("AcademaicDetails")){
                            academicDetails = dataSnapshot.child("AcademicDetails").getValue(AcademicDetails.class);
                        }
                        if(dataSnapshot.hasChild("PersonalDetails")){
                            personalDetails = dataSnapshot.child("PersonalDetails").getValue(PersonalDetails.class);
                        }
                    }
                }
                else{
                    status[0]=false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                status[0] = true;
            }
        });

        return status[0];
    }

}
