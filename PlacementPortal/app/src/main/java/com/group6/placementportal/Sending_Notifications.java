package com.group6.placementportal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.group6.placementportal.DatabasePackage.Notifications_Admin;

public class Sending_Notifications extends AppCompatActivity {

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private EditText subject;
    private EditText description;
    private Uri pdfUri;
    private TextView fileName;
    private ProgressDialog progressDialog;
    private Button send;
    long size ;
//    private Button select;
//    private Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending__notifications);

        if(isNetworkAvailable()==false){
            Toast.makeText(Sending_Notifications.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        subject= findViewById(R.id.subject);
        description=findViewById(R.id.description);
        send= findViewById(R.id.button_send);
        fileName = findViewById(R.id.editTextFileName);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference=FirebaseDatabase.getInstance().getReference("Notifications_Admin");



        findViewById(R.id.buttonSelectFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Sending_Notifications.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    selectPDF();
                } else {
                    ActivityCompat.requestPermissions(Sending_Notifications.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                }
            }
        });

//        findViewById(R.id.buttonUploadFIle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(pdfUri!=null) {
//                    Toast.makeText(Sending_Notifications.this,"Uploading Started...",Toast.LENGTH_LONG).show();
//                    uploadFile(pdfUri);
//                }
//                else{
//                    Toast.makeText(Sending_Notifications.this,"Select a File",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null) {
                    Toast.makeText(Sending_Notifications.this,"Uploading Started...",Toast.LENGTH_LONG).show();
                    uploadFile(pdfUri);
                }
                else{
                    Toast.makeText(Sending_Notifications.this,"Select a File",Toast.LENGTH_SHORT).show();
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

//    private void getPDF() {
//        //so if the permission is not available user will go to the screen to allow storage permission
//        if(ContextCompat.checkSelfPermission(Sending_Notifications.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
//            selectPDF();
//        }
//        else{
//            ActivityCompat.requestPermissions(Sending_Notifications.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
//        }
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDF();
        }
        else{
            Toast.makeText(Sending_Notifications.this,"Permission Denied",Toast.LENGTH_LONG).show();
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
            if (data.getData() != null) {
                //uploading the file
                pdfUri = data.getData();
                Toast.makeText(Sending_Notifications.this,"File has been Selected",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }

    }


    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading...");
        progressDialog.setProgress(30);
        progressDialog.show();

        final StorageReference ref = mStorageReference.child("Uploads").child("dummy");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
//                                mDatabaseReference.child("Notifications").setValue(upload);
                                newNotification(upload);
                                Toast.makeText(Sending_Notifications.this,"File Upload & Saving Successful",Toast.LENGTH_LONG).show();
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


    private void newNotification(String upload){

        String newSubject = subject.getText().toString().trim();
        String newDescription = description.getText().toString().trim();

        if(!TextUtils.isEmpty(newSubject) || !TextUtils.isEmpty(newDescription)){
            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    size = (long) dataSnapshot.getChildrenCount() ;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            String id = Long.toString(size +1);

            Notifications_Admin notification = new Notifications_Admin(newSubject,newDescription,upload,id);


            mDatabaseReference.child(id).setValue(notification);
            Toast.makeText(this,"Saving data Successful",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Write Subject & Description ",Toast.LENGTH_LONG).show();
        }
    }


    }

    


//uploadTask.addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception exception) {
//        // Handle unsuccessful uploads
//        Log.d("uploadFail", "" + exception);
//
//        }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//@Override
//public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//        sendNotification("upload backup", 1);
//
//        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//
//        Log.d("downloadUrl", "" + downloadUrl);
//        }
//        });

