package com.group6.placementportal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.PrivilegedAction;

public class Apply_For_Jobs extends AppCompatActivity {

    private Uri pdfUri;

    //these are the views
    private TextView fileName;
    private ProgressDialog progressDialog;

    //the firebase objects for storage and database
    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_apply__for__jobs);


        //getting firebase objects
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

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
                    Toast.makeText(Apply_For_Jobs.this,"Select a File",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //this function will get the pdf from the storage
    private void getPDF() {
        //so if the permission is not available user will go to the screen to allow storage permission
        if(ContextCompat.checkSelfPermission(Apply_For_Jobs.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else{
            ActivityCompat.requestPermissions(Apply_For_Jobs.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else{
            Toast.makeText(Apply_For_Jobs.this,"Permission Denied",Toast.LENGTH_LONG).show();
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
            Toast.makeText(Apply_For_Jobs.this,"Select a file",Toast.LENGTH_SHORT).show();
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

        final StorageReference ref = mStorageReference.child("Uploads").child("1");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
                                mDatabaseReference.child("Upload").setValue(upload);
                                Toast.makeText(Apply_For_Jobs.this,"File Upload Successful",Toast.LENGTH_SHORT).show();
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