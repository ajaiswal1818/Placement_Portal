package com.group6.placementportal;

import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;




import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;




public class job_profile extends AppCompatActivity {

    private company c;
    private EditText profile;
    private EditText ctc;
    private EditText location;
    private Button add;
    private Button upload;
    private Button submit;

    public job_profile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //c= (company) getIntent().getSerializableExtra("MyClass");
        /*upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
            }
        });*/
        profile = findViewById(R.id.profile);
        ctc=findViewById(R.id.ctc);
        location=findViewById(R.id.location);
       // job j= new job(1,profile.getText().toString(),Float.parseFloat(ctc.getText().toString()),location.getText().toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_profile2);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // File or Blob
       /* File file = new File(Uri);

        file = Uri.fromFile(new File("path/to/mountains.jpg"));

// Create the file metadata
        metadata = new StorageMetadata.Builder()
                .setContentType("image/jpeg")
                .build();

// Upload file and metadata to the path 'images/mountains.jpg'
        uploadTask = storageRef.child("images/"+file.getLastPathSegment()).putFile(file, metadata);

// Listen for state changes, errors, and completion of the upload.
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                System.out.println("Upload is " + progress + "% done");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("Upload is paused");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle successful uploads on complete
                // ...
            }
        });*/
/*
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        Uri file = Uri.fromFile(new File("C:\\Users\\harpa\\Downloads"));
        Log.d("file", file.getPath());


        StorageReference riversRef = storageRef.child("Child");

        UploadTask uploadTask = riversRef.putFile(file);
        */
/*
// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("uploadFail", "" + exception);

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                sendNotification("upload backup", 1);

                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                Log.d("downloadUrl", "" + downloadUrl);
            }
        });





        public void ShowUploadFileDialogBox()
        String TitleMaterial;
        {
            View dialogViewFile;
            final int[] flag = {0};
            Uri selectedfile;
            selectedfile = null;





            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            dialogViewFile = inflater.inflate(R.layout.add_file_dialog_box, null);
            dialogBuilder.setView(dialogViewFile);
//        getFile();

            dialogBuilder.setTitle("Doraemon");
            final AlertDialog b = dialogBuilder.create();
            b.show();

            editTextName = dialogViewFile.findViewById(R.id.editTextName);
            FileName = dialogViewFile.findViewById(R.id.FileName);
            buttonSelectFile = dialogViewFile.findViewById(R.id.buttonSelectFile);
            buttonAddClass = dialogViewFile.findViewById(R.id.buttonAddClass);

            TitleMaterial = editTextName.getText().toString();

            buttonSelectFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[0] =1;
                    String filepath = "";
                    FileName.setText("");
                    getFile();

                }
            });

            if (flag[0] == 0) {
                buttonAddClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CourseMainPageProf.this, "First Select File", Toast.LENGTH_LONG).show();
//                    uploadFile();
                    }
                });

            }
        }




        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 123 && resultCode == RESULT_OK) {
                Uri selectedfile = data.getData(); //The uri with the location of the file
                String filepath = selectedfile.getPath().toString();
                // Toast.makeText(this,filepath,Toast.LENGTH_LONG).show();
                if (filepath.equals("")){
                    flag =0;
                }

                Toast.makeText(CourseMainPageProf.this,filepath,Toast.LENGTH_LONG).show();

                FileName.setText(filepath);



                buttonAddClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final StorageReference storageRef = storage.getReference();

                        // add course id in front of file path
                        mountainsRef = storageRef.child(selectedfile.getLastPathSegment());
                        uploadTask = mountainsRef.putFile(selectedfile);

                        // failure and success listeners
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(CourseMainPageProf.this,"File could not be uploaded",Toast.LENGTH_SHORT).show();
                                // Handle unsuccessful uploads
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                // ...
                                Toast.makeText(CourseMainPageProf.this,"File uploaded successfully.",Toast.LENGTH_SHORT).show();


                                //------------------------
                                // getting download url for file
                                getDownloadUrl(mountainsRef,uploadTask);

                            }
                        });
                    }
                });

            }

        }

        public void getDownloadUrl(final StorageReference mountainsRef,UploadTask uploadTask)
        {
//        final String[] url = new String[1];
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return mountainsRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
//                    url[0] = downloadUri.toString();
                        Toast.makeText(CourseMainPageProf.this,"Download Url:"+downloadUri.toString(),Toast.LENGTH_LONG).show();
                        Log.v(TAG,"Download Url:"+downloadUri.toString());
                        //----------------
                        // taking values from title and file url to be stored in firebase
                        if(TitleMaterial==""){Toast.makeText(CourseMainPageProf.this,"Please fill the title of class",Toast.LENGTH_SHORT).show();}
                        else {

                            EditText ClassTitle = dialogViewFile.findViewById(R.id.editTextName);
                            TextView FileName = dialogViewFile.findViewById(R.id.FileName);
                            databaseReference = FirebaseDatabase.getInstance().getReference();
                            CourseMaterial courseMaterial = new CourseMaterial(ClassTitle.getText().toString()
                                    ,downloadUri.toString(), FileName.getText().toString(),Calendar.getInstance().getTime());
                            String key=databaseReference.child("Courses").child(getIntent().getStringExtra("CourseID")).child("Events").push().getKey();
                            databaseReference.child("Courses").child(getIntent().getStringExtra("CourseID")).child("Course Material").child(key).setValue(courseMaterial);

                        }

                    } else {
                        Toast.makeText(CourseMainPageProf.this,"File could not be successfully uploaded",Toast.LENGTH_SHORT).show();
                        // Handle failures
                        // ...
                    }
                }
            });


//        StorageReference httpsReference = storage.getReferenceFromUrl(url[0]);
        }
    }*/

    }
   /* public void getFile()
    {
        Intent intent = new Intent().setType("/").setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra("Filename",);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
    }
*/
}