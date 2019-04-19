package com.group6.placementportal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.group6.placementportal.DatabasePackage.Notices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeFromCompany extends AppCompatActivity {
    Button Choose,Load;
    EditText t1;
    EditText t2;
    ImageView image;
    Uri imageUri;
    ProgressBar mprogressbar;
    private StorageReference mstorage;
    DatabaseReference database;
    private  static final int PICK_IMAGE=100;
    private String downURL;

    ArrayList<Notices> noti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_from_company);

        if(isNetworkAvailable()==false){
            Toast.makeText(NoticeFromCompany.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

        image= (ImageView)findViewById(R.id.imageView);
        Choose= (Button)findViewById(R.id.Upload_Pic);
        Load= (Button)findViewById(R.id.Load);
        t1=(EditText)findViewById(R.id.Job_Title);
        t2= (EditText) findViewById(R.id.job_description);
        mprogressbar=(ProgressBar)findViewById(R.id.progressBar);

        FirebaseApp.initializeApp(this);
        mstorage= FirebaseStorage.getInstance().getReference();
        Firebase.setAndroidContext(this);


        Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);
            }
        });

        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t1.getText().toString().equals("")){
                    t1.setError("Field Required");
                    return;
                }
                if(t2.getText().toString().equals("")){
                    t2.setError("Field Required");
                    return;
                }
                Upload_file();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected  void  onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && requestCode == PICK_IMAGE && data!= null && data.getData() !=null ){
            imageUri=data.getData();
            if(imageUri!=null)Picasso.get().load(imageUri).into(image);

        }
    }

    private  String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private  void Upload_file(){
        if (imageUri!=null){
            final StorageReference filereference = mstorage.child("Upload_CompanyNotices").child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
            filereference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogressbar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(NoticeFromCompany.this, "UplaodSuccessfull", Toast.LENGTH_SHORT).show();
                            filereference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downURL = uri.toString();
                                }
                            });
                            final Notices new_notice = new Notices();
                            new_notice.setTopic(t1.getText().toString());
                            new_notice.setImageURL(downURL);
                            new_notice.setContent(t2.getText().toString());

                            database = FirebaseDatabase.getInstance().getReference("Notices");

                            String uploadId = database.push().getKey();
                            database.child(uploadId).setValue(new_notice);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(NoticeFromCompany.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0* taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            mprogressbar.setProgress((int) progress);
                        }
                    });
        }
        else{
            Toast.makeText(this,"No Image Selected",Toast.LENGTH_SHORT).show();
        }
    }

}
