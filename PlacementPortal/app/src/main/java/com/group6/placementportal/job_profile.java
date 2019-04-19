package com.group6.placementportal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.CancellableTask;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.support.v7.widget.RecyclerView;
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

import static com.group6.placementportal.Student_Profile.isNumeric;


public class job_profile extends AppCompatActivity {

    private String id;
    private String comp_name;
    private EditText profile;
    private EditText ctc;
    private EditText location;
    private EditText cpi;
    private EditText job_requirements;
    private Jobs job_det;

    private Button add;
    private Button upload;
    private Button submit;
    private Button select;
    private Button remove;
    private TextView status;
    private DatabaseReference add_comp;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private StorageReference ref;
    private ProgressDialog progressDialog;
    private Uri pdfUri;
    private job j1;
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
    private String job_id;
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
        id = getIntent().getStringExtra("MyClassID");
        file = "";
        dep = "";
        //String str1 = Integer.toString(id.getCompany_id());
        /*Toast toast=Toast.makeText(getApplicationContext(),id.getSector(),Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();*/
        cpi = findViewById(R.id.cpi);
        profile = findViewById(R.id.profile);
        ctc = findViewById(R.id.ctc);
        location = findViewById(R.id.location);
        branch = findViewById(R.id.branch);
        job_requirements = findViewById(R.id.job_requirements);

        branch_button = findViewById(R.id.branch_button);

        available_branches = getResources().getStringArray(R.array.Department);
        checked_branches = new boolean[available_branches.length];


        //add = findViewById(R.id.add);
        upload = findViewById(R.id.upload);
        submit = findViewById(R.id.submit);
        remove = findViewById(R.id.remove);

        select = findViewById(R.id.select);
        status = findViewById(R.id.status);
        storage = FirebaseStorage.getInstance();
        //database=FirebaseDatabase.getInstance();
        add_comp = FirebaseDatabase.getInstance().getReference("Company");
        //String str= add_comp.child("Company").push().getKey();


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
                            int i;
                            for (i = 0; i < selected_branches.size() - 1; i++) {
                                dep += available_branches[selected_branches.get(i)] + ".";
                                str += available_branches[selected_branches.get(i)] + "\n";
                            }
                            dep += available_branches[selected_branches.get(i)];
                            str += available_branches[selected_branches.get(i)];
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

            add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
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
     /*   add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("") || cpi.getText().toString().equals("") || dep.equals("")) {
                    Toast.makeText(job_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                } else {
                    add_job();
                }
            }
        });*/
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cpi.getText().toString().matches("\\d+\\.?\\d+")==false ){
                        cpi.setError("Invalid CPI");
                        return;
                    }
                    double cpi_double=Double.parseDouble(cpi.getText().toString());
                    if(cpi_double>10 || cpi_double<0){
                        cpi.setError("Invalid CPI");
                        return;
                    }
                    if(ctc.getText().toString().matches("\\d+\\.?\\d+")==false ){
                        ctc.setError("CTC can only be decimal number");
                        return;
                    }

                    if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("")||cpi.getText().toString().equals("") || dep.equals("") || job_requirements.getText().toString().equals("")) {
                        Toast.makeText(job_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
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
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });




                        Intent job_list = new Intent(job_profile.this, job_list.class);
                        job_list.putExtra("id",id);
                        finish();
                        startActivity(job_list);
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
    }

    private void uploadFile(final Uri pdfUri) {
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

        cpi.setText(String.valueOf(job_det.getCutoff_cpi()));
        profile.setText(job_det.getProfile());
        ctc.setText(String.valueOf(job_det.getCtc()));
        location.setText(job_det.getLocation());
        branch.setText(job_det.getBranches());
        job_requirements.setText(job_det.getJob_requirements());

        branch_button.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        upload.setText("View Selected File");
        select.setVisibility(View.INVISIBLE);

        status.setText(job_det.getBrochure());
//getBrochure!=" " check
        if(!(status.getText().toString().equals("")))
        {
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);*/
                   // Uri uri = Uri.parse(job_det.getBrochure());
                    Intent view_pdf = new Intent(job_profile.this, view_pdf.class);
                    view_pdf.putExtra("url",job_det.getBranches());
                    startActivity(view_pdf);
                }

            });
        }
        else
        {

        }

    }
}