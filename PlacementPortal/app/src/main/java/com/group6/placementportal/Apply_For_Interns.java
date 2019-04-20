package com.group6.placementportal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Student;

public class Apply_For_Interns extends AppCompatActivity {

    private Uri pdfUri;
    private Interns interns;
    private Student user;

    //these are the views
    private TextView fileName;
    private ProgressDialog progressDialog;

    //the firebase objects for storage and database
    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;

    //TextViews
    private TextView intern_profile,intern_requirements,salary,brochure,cutoff_cpi,intern_location,company_name,company_contact,company_email,company_headquarters;
    private Button btn_apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_apply__for__jobs);

        if(isNetworkAvailable()==false){
            Toast.makeText(Apply_For_Interns.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        btn_apply=findViewById(R.id.buttonUploadFIle);
        interns = (Interns) getIntent().getSerializableExtra("intern_profile");
        user = (Student) getIntent().getSerializableExtra("user");

        intern_profile = findViewById(R.id.job_profile);
        intern_requirements = findViewById(R.id.job_requirements);
        salary = findViewById(R.id.salary);
        brochure = findViewById(R.id.brochure);
        cutoff_cpi = findViewById(R.id.cutoff_cpi);
        intern_location = findViewById(R.id.job_location);
        company_name = findViewById(R.id.company_name);
        company_contact = findViewById(R.id.company_contact);
        company_email = findViewById(R.id.company_email);
        company_headquarters = findViewById(R.id.company_headquarters);

        intern_profile.setText(interns.getProfile());
        intern_requirements.setText(interns.getIntern_requirements());
        salary.setText(String.format("%f",interns.getCtc()));
        brochure.setText(interns.getBrochure());
        cutoff_cpi.setText(String.format("%f",interns.getCutoff_cpi()));
        intern_location.setText(interns.getLocation());




        //getting firebase objects
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.child("Student").child(user.getWebmailID()).hasChild("AcademicDetails")){
                    btn_apply.setEnabled(false);
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(Apply_For_Interns.this);
                    mBuilder.setTitle("Please Complete Your Profile");
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }
                else {
                    if(dataSnapshot.child("Interns").child(interns.getIntern_id()).child("Applied Students").hasChild(user.getWebmailID()) || dataSnapshot.child("Student").child(user.getWebmailID()).hasChild("has_given_preferences_intern")){
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Apply_For_Interns.this);
                        mBuilder.setTitle("You have already applied for this job");
                        mBuilder.setCancelable(false);
                        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();
                        btn_apply.setEnabled(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d("TAG",interns.getCompany_id()+" ");
        mDatabaseReference.child("Company").child(interns.getCompany_id()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String c_name = dataSnapshot.child("company_name").getValue(String.class);
                company_name.setText(dataSnapshot.child("company_name").getValue(String.class));
                company_contact.setText(dataSnapshot.child("contact_no").getValue(String.class));
                company_email.setText(dataSnapshot.child("email_address").getValue(String.class));
                company_headquarters.setText(dataSnapshot.child("headoffice").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //attaching listeners to views
        fileName = findViewById(R.id.editTextFileName);
        findViewById(R.id.buttonSelectFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPDF();
            }
        });
        findViewById(R.id.buttonUploadFIle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null) {
                    uploadFile(pdfUri);
                }
                else{
                    Toast.makeText(Apply_For_Interns.this,"Select a File",Toast.LENGTH_SHORT).show();
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

    //this function will get the pdf from the storage
    private void getPDF() {
        //so if the permission is not available user will go to the screen to allow storage permission
        if(ContextCompat.checkSelfPermission(Apply_For_Interns.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else{
            ActivityCompat.requestPermissions(Apply_For_Interns.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else{
            Toast.makeText(Apply_For_Interns.this,"Permission Denied",Toast.LENGTH_LONG).show();
        }
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri = data.getData();
            fileName.setText(data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(Apply_For_Interns.this,"Select a file",Toast.LENGTH_SHORT).show();
        }
    }


    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading...");
        progressDialog.setProgress(0);
        progressDialog.show();

        final StorageReference ref = mStorageReference.child("Uploads_Interns").child(interns.getIntern_id()).child(user.getWebmailID());
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
                                mDatabaseReference.child("Interns").child(interns.getIntern_id()).child("Applied Students").child(user.getWebmailID()).child("CV").setValue(upload);
                                mDatabaseReference.child("Interns").child(interns.getIntern_id()).child("Applied Students").child(user.getWebmailID()).child("Status").setValue("0");
                                mDatabaseReference.child("Interns").child(interns.getIntern_id()).child("Applied Students").child(user.getWebmailID()).child("Approval").setValue("No");
                                Toast.makeText(Apply_For_Interns.this,"File Upload Successful",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), "File Upload Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int progress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(progress);
            }
        });
    }
}
