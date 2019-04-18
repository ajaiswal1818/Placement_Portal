package com.group6.placementportal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.CancellableTask;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.PathUtils;
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
import android.widget.ArrayAdapter;
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
import android.widget.Spinner;
import android.widget.TextView;
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

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class job_profile extends AppCompatActivity {

    private company c;
    private EditText profile;
    private EditText ctc;
    private EditText location;
    private EditText cpi;

    private Button add;
    private Button upload;
    private Button submit;
    private Button select;
    private Button remove;
    private TextView status;
    private DatabaseReference add_comp;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private Uri pdfUri;
    private ArrayList<job> j1;
    private ArrayList<Integer> selected_branches = new ArrayList<>();
    private String[] available_branches;
    private boolean[] checked_branches;
    private TextView branch;
    private Button branch_button;
    private long max_id = -1;
    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "job_profile";
    public String file;
    private String dep;
    private String prevActivity;
    private int check = 0;

    public job_profile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
            }
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_profile2);

        prevActivity = (String) getIntent().getSerializableExtra("PrevActivity");
        c = (company) getIntent().getSerializableExtra("MyClass");
        file = "";
        dep = "";
        j1 = c.getJobs();
        //String str1 = Integer.toString(c.getCompnany_id());
        /*Toast toast=Toast.makeText(getApplicationContext(),c.getSector(),Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();*/
        cpi = findViewById(R.id.cpi);
        profile = findViewById(R.id.profile);
        ctc = findViewById(R.id.ctc);
        location = findViewById(R.id.location);
        branch = findViewById(R.id.branch);
        branch_button = findViewById(R.id.branch_button);

        available_branches = getResources().getStringArray(R.array.Department);
        checked_branches = new boolean[available_branches.length];


        add = findViewById(R.id.add);
        upload = findViewById(R.id.upload);
        submit = findViewById(R.id.submit);
        remove = findViewById(R.id.remove);

        select = findViewById(R.id.select);
        status = findViewById(R.id.status);
        storage = FirebaseStorage.getInstance();
        //database=FirebaseDatabase.getInstance();
        add_comp = FirebaseDatabase.getInstance().getReference("Company");
        //String str= add_comp.child("Company").push().getKey();

        branch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(job_profile.this);
                mBuilder.setTitle("Departments");
                mBuilder.setMultiChoiceItems(available_branches, checked_branches, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            if (!selected_branches.contains(which)) {
                                selected_branches.add(which);
                            }
                        } else {
                            if (selected_branches.contains(which)) {
                                selected_branches.remove(selected_branches.indexOf(which));
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "";
                        int i;
                        for (i = 0; i < selected_branches.size() - 1; i++) {
                            dep += available_branches[selected_branches.get(i)] + ".";
                            str += available_branches[selected_branches.get(i)] + "\n";
                        }
                        dep += available_branches[selected_branches.get(i)] + ".";
                        str += available_branches[selected_branches.get(i)] + "\n";
                        branch.setText(str);
                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checked_branches.length; i++) {
                            checked_branches[i] = false;
                            dep = "";
                        }
                        selected_branches.clear();
                        branch.setText("");
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        add_comp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String key = dataSnapshot.child("Max_entry").getValue().toString();
                    max_id = Integer.parseInt(key);
                    /*Iterable<DataSnapshot> all_childs= dataSnapshot.getChildren();
                    for (DataSnapshot son : all_childs)
                    {
                        String key= son.getKey();
                        if(max_id < Integer.parseInt(key))
                        {
                            max_id=;
                        }
                    }*/
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("") || cpi.getText().toString().equals("") || dep.equals("")) {
                    Toast.makeText(job_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                } else {
                    add_job();
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profile.getText().toString().trim().equals("") && ctc.getText().toString().trim().equals("") && location.getText().toString().trim().equals("") && file.equals("") && cpi.getText().toString().equals("") && dep.equals("")) {
                    if (prevActivity.equals("company_profile")) {
                        c.setCompany_id(String.valueOf(max_id + 1));
                        add_comp.child("Max_entry").setValue(String.valueOf(max_id + 1));
                        add_comp.child(c.getCompany_id()).setValue(c);
                        Intent company_login = new Intent(job_profile.this, company_login.class);
                        finish();
                        startActivity(company_login);
                    } else {
                        add_comp.child(c.getCompany_id()).setValue(c);
                        Intent company_login = new Intent(job_profile.this, company_login.class);
                        finish();
                        startActivity(company_login);
                    }

                } else if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("")) {
                    Toast.makeText(job_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                } else {

                    add_job();
                    /*Intent job_profile=new Intent(company_profile.this, job_profile.class);
                    //job_profile.putExtra("MyClass",c);
                    startActivity(job_profile);*/
                    c.setJobs(j1);

                    if (prevActivity.equals("company_profile")) {
                        c.setCompany_id(String.valueOf(max_id + 1));
                        add_comp.child("Max_entry").setValue(String.valueOf(max_id + 1));
                        add_comp.child(c.getCompany_id()).setValue(c);
                        Intent company_login = new Intent(job_profile.this, company_login.class);
                        startActivity(company_login);
                    } else {
                        add_comp.child(c.getCompany_id()).setValue(c);
                        Intent company_login = new Intent(job_profile.this, company_login.class);
                        startActivity(company_login);
                    }
                }

            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(job_profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    selectPDF();
                } else {
                    ActivityCompat.requestPermissions(job_profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference storageReference = storage.getReference();
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(job_profile.this, "Removed successfully", Toast.LENGTH_LONG).show();
                        status.setText("No file selected");
                        remove.setVisibility(View.INVISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(job_profile.this, "Couldn't remove, please try after some time", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdfUri != null) {
                    uploadFile(pdfUri);

                } else {
                    Toast.makeText(job_profile.this, "Select a file", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadFile(Uri pdfUri) {
        // Do cancel upload and remove uploaded file
        // Once selected to upload allow to not upload
        // Remove selected file in add_job() function
        // Check cancelable
        // see that submit and add another are working logically correct
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File ... Please wait");
        progressDialog.setProgress(0);

        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));

        progressDialog.setIndeterminate(false);
                /*
                    Set the progress dialog non cancelable
                    It will disallow user's to cancel progress dialog by clicking outside of dialog
                    But, user's can cancel the progress dialog by cancel button
                 */
        progressDialog.setCancelable(false);
        //progressDialog.setCancelable(false);
        /*progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
                status.setText("No file selected");
                remove.setVisibility(View.INVISIBLE);
                return;
            }
        });*/

        progressDialog.show();

        //final String filename=System.currentTimeMillis() + "";
        final StorageReference storageReference = storage.getReference();
        storageReference.child("Company").putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = storageReference.child("Company").getDownloadUrl().toString();// taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();// taskSnapshot.getDownloadUrl().toString();
                file = url;
                /*DatabaseReference reference=database.getReference();

                reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(job_profile.this,"Uploaded successfully",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(job_profile.this,"Failed to upload",Toast.LENGTH_SHORT).show();

                    }
                });*/
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.setCancelable(true);
                Toast.makeText(job_profile.this, "Failed to upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                if (currentProgress == 100) {
                    progressDialog.hide();
                    Toast.makeText(job_profile.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
                   // remove.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPDF();
        } else
            Toast.makeText(job_profile.this, "Please provide permission", Toast.LENGTH_LONG).show();
    }

    private void selectPDF() {
        // user can select file using Intent
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData(); //The uri with the location of the file
            status.setText("File Selected : " + data.getData().getLastPathSegment());
        } else {
            Toast.makeText(job_profile.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }

    public void add_job() {
        check = 1;
        job j = new job(j1.size(), profile.getText().toString(), Float.parseFloat(ctc.getText().toString()), location.getText().toString(), file, Float.parseFloat(cpi.getText().toString()), dep);
        j1.add(j);
        profile.setText("");
        ctc.setText("");
        location.setText("");
    }

}
/*    public void getFile()
    {
        Intent intent = new Intent().setType("/").setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra("Filename",);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType();
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    String path= PathUtils.getPath();
                    try {
                        file=new File();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    //String path = FileUtils.getPath(this, uri);
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
*/
// String filepath = selectedfile.getPath().toString();
// Toast.makeText(this,filepath,Toast.LENGTH_LONG).show();
           /* if (filepath.equals("")){
                flag =0;
            }

            Toast.makeText(CourseMainPageProf.this,filepath,Toast.LENGTH_LONG).show();

            FileName.setText(filepath);*/



       /* upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });


        //FILE_SELECT_CODE = 0;


        //
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
*/
//Upload file and metadata to the path 'images/mountains.jpg'
   /*     FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();


        Uri file = Uri.fromFile(new File (getFile());
        Log.d("file", file.getPath());


        StorageReference riversRef = storageRef.child("firebase-storage");

        UploadTask uploadTask = riversRef.putFile(file);
        uploadTask = storageRef.child("images/" + file.getLastPathSegment()).putFile(file);

//Listen for state changes, errors, and completion of the upload.
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
        });
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




*/
  /*      public void ShowUploadFileDialogBox()
        {
            String TitleMaterial;
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
        }*/
// }