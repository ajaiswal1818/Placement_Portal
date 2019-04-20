package com.group6.placementportal;

<<<<<<< HEAD
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
=======
import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
<<<<<<< HEAD
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
=======
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.job;

import java.util.ArrayList;

import static com.group6.placementportal.company_profile.isNumeric;


public class job_profile extends AppCompatActivity {

    private String id;
    private String comp_name;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    private EditText profile;
    private EditText ctc;
    private EditText location;
    private EditText cpi;
<<<<<<< HEAD
=======
    private EditText job_requirements;
    private Jobs job_det;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    private Button add;
    private Button upload;
    private Button submit;
    private Button select;
    private Button remove;
    private TextView status;
    private DatabaseReference add_comp;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
<<<<<<< HEAD
    private ProgressDialog progressDialog;
    private Uri pdfUri;
    private ArrayList<job> j1;
=======
    private StorageReference ref;
    private ProgressDialog progressDialog;
    private Uri pdfUri;
    private job j1;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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
<<<<<<< HEAD

=======
    private String job_id;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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

<<<<<<< HEAD
        prevActivity = (String) getIntent().getSerializableExtra("PrevActivity");
        c = (company) getIntent().getSerializableExtra("MyClass");
        file = "";
        dep = "";
        j1 = c.getJobs();
        //String str1 = Integer.toString(c.getCompnany_id());
        /*Toast toast=Toast.makeText(getApplicationContext(),c.getSector(),Toast.LENGTH_SHORT);
=======
        if(isNetworkAvailable()==false){
            Toast.makeText(job_profile.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        prevActivity = (String) getIntent().getSerializableExtra("PrevActivity");
        id = getIntent().getStringExtra("MyClassID");
        file = "";
        dep = "";
        //String str1 = Integer.toString(id.getCompany_id());
        /*Toast toast=Toast.makeText(getApplicationContext(),id.getSector(),Toast.LENGTH_SHORT);
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        toast.setMargin(50,50);
        toast.show();*/
        cpi = findViewById(R.id.cpi);
        profile = findViewById(R.id.profile);
        ctc = findViewById(R.id.ctc);
        location = findViewById(R.id.location);
        branch = findViewById(R.id.branch);
<<<<<<< HEAD
=======
        job_requirements = findViewById(R.id.job_requirements);

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        branch_button = findViewById(R.id.branch_button);

        available_branches = getResources().getStringArray(R.array.Department);
        checked_branches = new boolean[available_branches.length];


<<<<<<< HEAD
        add = findViewById(R.id.add);
=======
        //add = findViewById(R.id.add);
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        upload = findViewById(R.id.upload);
        submit = findViewById(R.id.submit);
        remove = findViewById(R.id.remove);

        select = findViewById(R.id.select);
        status = findViewById(R.id.status);
        storage = FirebaseStorage.getInstance();
        //database=FirebaseDatabase.getInstance();
        add_comp = FirebaseDatabase.getInstance().getReference("Company");
        //String str= add_comp.child("Company").push().getKey();

<<<<<<< HEAD
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
=======

        if(prevActivity.equals("recycleview"))
        {
            add_comp = FirebaseDatabase.getInstance().getReference("Jobs");
            add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        job_det = dataSnapshot.child(id).getValue(Jobs.class);
                        allot();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            upload.setText("Upload file");
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
                            dep="";
                            int i;
                            if(selected_branches.size()>=1)
                            {
                                for (i = 0; i < selected_branches.size() - 1; i++) {
                                    dep += available_branches[selected_branches.get(i)] + ".";
                                    str += available_branches[selected_branches.get(i)] + "\n";
                                }
                                dep += available_branches[selected_branches.get(i)];
                                str += available_branches[selected_branches.get(i)];
                                branch.setText(str);
                            }
                            else
                            {
                                dep="";
                                str="";
                            }
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

            add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String key = dataSnapshot.child("Max_entry").getValue().toString();
                        max_id = Integer.parseInt(key);

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                    /*Iterable<DataSnapshot> all_childs= dataSnapshot.getChildren();
                    for (DataSnapshot son : all_childs)
                    {
                        String key= son.getKey();
                        if(max_id < Integer.parseInt(key))
                        {
                            max_id=;
                        }
                    }*/
<<<<<<< HEAD
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
=======
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
     /*   add.setOnClickListener(new View.OnClickListener() {
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
            @Override
            public void onClick(View v) {
                if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("") || cpi.getText().toString().equals("") || dep.equals("")) {
                    Toast.makeText(job_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                } else {
                    add_job();
                }
            }
<<<<<<< HEAD
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profile.getText().toString().trim().equals("") && ctc.getText().toString().trim().equals("") && location.getText().toString().trim().equals("") && file.equals("") && cpi.getText().toString().equals("") && dep.equals("")) {
                    if (prevActivity.equals("company_profile")) {
                        c.setCompnany_id(String.valueOf(max_id + 1));
                        add_comp.child("Max_entry").setValue(String.valueOf(max_id + 1));
                        add_comp.child(c.getCompnany_id()).setValue(c);
                        Intent company_login = new Intent(job_profile.this, company_login.class);
                        finish();
                        startActivity(company_login);
                    } else {
                        add_comp.child(c.getCompnany_id()).setValue(c);
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
                        c.setCompnany_id(String.valueOf(max_id + 1));
                        add_comp.child("Max_entry").setValue(String.valueOf(max_id + 1));
                        add_comp.child(c.getCompnany_id()).setValue(c);
                        Intent company_login = new Intent(job_profile.this, company_login.class);
                        startActivity(company_login);
                    } else {
                        add_comp.child(c.getCompnany_id()).setValue(c);
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
=======
        });*/
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((cpi.getText().toString().matches("\\d+\\.?\\d+") ==false && cpi.getText().toString().matches("\\d")==false)){
                        cpi.setError("Invalid CPI");
                        return;
                    }
                    double cpi_double=Double.parseDouble(cpi.getText().toString());
                    if(cpi_double>10 || cpi_double<0){
                        cpi.setError("Invalid CPI");
                        return;
                    }
                    if((ctc.getText().toString().matches("\\d+\\.?\\d+") ==false && ctc.getText().toString().matches("\\d")==false)){
                        ctc.setError("CTC can only be decimal number");
                        return;
                    }

                    if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("")||cpi.getText().toString().equals("") || dep.equals("") || job_requirements.getText().toString().equals("")) {
                        Toast.makeText(job_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(file.equals(""))
                    {
                        Toast.makeText(job_profile.this, "Please upload a pdf file", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {


                        add_job();
                        add_comp=FirebaseDatabase.getInstance().getReference("Company");
                        add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    if(dataSnapshot.child(id).hasChild("jobs"))
                                    {
                                        job_id=String.valueOf(dataSnapshot.child(id).child("jobs").getChildrenCount());
                                    }
                                    else
                                    {
                                        job_id="0";
                                    }
                                    comp_name=dataSnapshot.child(id).child("company_name").getValue().toString();
                                    j1.setJob_id(job_id);
                                    add_comp.child(id).child("jobs").child(job_id).setValue(j1);
                                    DatabaseReference add_comp1;
                                    add_comp1=FirebaseDatabase.getInstance().getReference("Jobs");
                                    Jobs new_job=new Jobs(id + "_" +  j1.getJob_id(),j1.getProfile(),j1.getCtc(),j1.getLocation(),j1.getBrochure(),id,comp_name,j1.getDepartments(),j1.getCpi(),j1.getJob_requirements());
                                    add_comp1.child(id + "_" + j1.getJob_id()).setValue(new_job);
                                    Toast.makeText(job_profile.this, "Submitted", Toast.LENGTH_LONG).show();




                                    Intent job_list = new Intent(job_profile.this, job_list.class);
                                    job_list.putExtra("id",id);
                                    finish();
                                    startActivity(job_list);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





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
                        Toast.makeText(job_profile.this, "Select a pdf file", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void uploadFile(final Uri pdfUri) {
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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
<<<<<<< HEAD
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
=======


        add_comp=FirebaseDatabase.getInstance().getReference("Company");
        add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if(dataSnapshot.child(id).hasChild("jobs"))
                    {
                        job_id=String.valueOf(dataSnapshot.child(id).child("jobs").getChildrenCount());
                    }
                    else
                    {
                        job_id="0";

                    }
                    uploadpdf(pdfUri);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    public void uploadpdf(Uri pdfUri){
        add_comp = FirebaseDatabase.getInstance().getReference();
        ref=FirebaseStorage.getInstance().getReference();
        final StorageReference storageReference = ref.child("Brochures_for_job").child(id).child(job_id);
        storageReference.putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressDialog.hide();
                        String upload = uri.toString();
                        file=upload;
                        Toast.makeText(job_profile.this,"File Upload Successful",Toast.LENGTH_LONG).show();
                    }
                });
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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
<<<<<<< HEAD
                    Toast.makeText(job_profile.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
                   // remove.setVisibility(View.VISIBLE);
=======
                   // Toast.makeText(job_profile.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
                    // remove.setVisibility(View.VISIBLE);
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
<<<<<<< HEAD
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
=======
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
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
<<<<<<< HEAD
            Toast.makeText(job_profile.this, "Please select a file", Toast.LENGTH_SHORT).show();
=======
            Toast.makeText(job_profile.this, "Please select a pdf file", Toast.LENGTH_SHORT).show();
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        }
    }

    public void add_job() {
        check = 1;
<<<<<<< HEAD
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
=======
        j1 = new job("1", profile.getText().toString(), Float.parseFloat(ctc.getText().toString()), location.getText().toString(), file, Float.parseFloat(cpi.getText().toString()), dep, job_requirements.getText().toString());
        profile.setText("");
        ctc.setText("");
        location.setText("");
        cpi.setText("");
        dep="";
    }
    public void downloadFiles(Context context, String Filename, String FileDestination, String url)
    {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, FileDestination, Filename);
        downloadManager.enqueue(request);
    }

    public void allot()
    {
        cpi.setEnabled(false);
        profile.setEnabled(false);
        ctc.setEnabled(false);
        location.setEnabled(false);
        branch.setEnabled(false);
        job_requirements.setEnabled(false);

        cpi.setText("CPI : " + String.valueOf(job_det.getCutoff_cpi()));
        profile.setText("Profile : " + job_det.getProfile());
        ctc.setText("CTC : " + String.valueOf(job_det.getCtc()));
        location.setText("Location : " + job_det.getLocation());
        branch.setText("Branches : " + job_det.getBranches());
        job_requirements.setText("Job Requirements : " + job_det.getJob_requirements());

        branch_button.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        upload.setVisibility(View.INVISIBLE);
        //upload.setText("View File");
        select.setVisibility(View.INVISIBLE);

        status.setText(job_det.getBrochure());
        status.setTextColor(Color.BLUE);
        status.setPaintFlags(status.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//getBrochure!=" " check
        if(!(job_det.getBrochure().equals("")))
        {
            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(job_det.getBrochure()));
                    startActivity(intent);
                   // Uri uri = Uri.parse(job_det.getBrochure());
                   /* Intent view_pdf = new Intent(job_profile.this, view_pdf.class);
                    view_pdf.putExtra("url",job_det.getBranches());
                    startActivity(view_pdf);*/
                }

            });
        }
        else
        {

        }

    }
}
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
