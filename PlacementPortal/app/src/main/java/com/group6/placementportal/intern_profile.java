package com.group6.placementportal;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.intern;

import java.util.ArrayList;

import static com.group6.placementportal.company_profile.isNumeric;


public class intern_profile extends AppCompatActivity {

    private String id;
    private String comp_name;
    private EditText profile;
    private EditText ctc;
    private EditText location;
    private EditText cpi;
    private EditText intern_requirements;
    private Interns intern_det;

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
    private intern j1;
    private ArrayList<Integer> selected_branches = new ArrayList<>();
    private String[] available_branches;
    private boolean[] checked_branches;
    private TextView branch;
    private Button branch_button;
    private long max_id = -1;
    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG = "intern_profile";
    public String file;
    private String dep;
    private String prevActivity;
    private int check = 0;
    private String intern_id;
    public intern_profile() {
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
        setContentView(R.layout.activity_intern_profile2);

        if(isNetworkAvailable()==false){
            Toast.makeText(intern_profile.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }

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
        intern_requirements = findViewById(R.id.intern_requirements);

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
            add_comp = FirebaseDatabase.getInstance().getReference("Interns");
            add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        intern_det = (Interns) dataSnapshot.child(id).getValue();
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
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(intern_profile.this);
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
                    Toast.makeText(intern_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                } else {
                    add_intern();

                }
            }
        });*/
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cpi.getText().toString().matches("\\d*\\.?\\d+")==false || isNumeric(cpi.getText().toString())==false){
                        cpi.setError("Invalid CPI");
                        return;
                    }
                    double cpi_double=Double.parseDouble(cpi.getText().toString());
                    if(cpi_double>10 || cpi_double<0){
                        cpi.setError("Invalid CPI");
                        return;
                    }
                    if(ctc.getText().toString().matches("\\d*\\.?\\d+")==false || isNumeric(ctc.getText().toString())==false){
                        ctc.setError("CTC can only be decimal number");
                        return;
                    }

                    if (profile.getText().toString().trim().equals("") || ctc.getText().toString().trim().equals("") || location.getText().toString().trim().equals("")||cpi.getText().toString().equals("") || dep.equals("") || intern_requirements.getText().toString().equals("")) {
                        Toast.makeText(intern_profile.this, "Can't leave any field empty", Toast.LENGTH_LONG).show();
                    }
                    else {


                        add_intern();
                        add_comp=FirebaseDatabase.getInstance().getReference("Company");
                        add_comp.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    if(dataSnapshot.child(id).hasChild("interns"))
                                    {
                                        intern_id=String.valueOf(dataSnapshot.child(id).child("interns").getChildrenCount());
                                    }
                                    else
                                    {
                                        intern_id="0";
                                    }
                                    comp_name=dataSnapshot.child(id).child("company_name").getValue().toString();
                                    j1.setIntern_id(intern_id);
                                    add_comp.child(id).child("interns").child(intern_id).setValue(j1);
                                    DatabaseReference add_comp1;
                                    add_comp1=FirebaseDatabase.getInstance().getReference("Interns");
                                    Interns new_intern=new Interns(id + "_" +  j1.getIntern_id(),j1.getProfile(),j1.getCtc(),j1.getLocation(),j1.getBrochure(),id,comp_name,j1.getDepartments(),j1.getCpi(),j1.getIntern_requirements());
                                    add_comp1.child(id + "_" + j1.getIntern_id()).setValue(new_intern);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });




                        Intent intern_list = new Intent(intern_profile.this, intern_list.class);
                        intern_list.putExtra("id",id);
                        finish();
                        startActivity(intern_list);
                    }

                }
            });
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(intern_profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED) {
                        selectPDF();
                    } else {
                        ActivityCompat.requestPermissions(intern_profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

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
                            Toast.makeText(intern_profile.this, "Removed successfully", Toast.LENGTH_LONG).show();
                            status.setText("No file selected");
                            remove.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(intern_profile.this, "Couldn't remove, please try after some time", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(intern_profile.this, "Select a file", Toast.LENGTH_SHORT).show();
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
        // Do cancel upload and remove uploaded file
        // Once selected to upload allow to not upload
        // Remove selected file in add_intern() function
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
                    if(dataSnapshot.child(id).hasChild("interns"))
                    {
                        intern_id=String.valueOf(dataSnapshot.child(id).child("interns").getChildrenCount());
                    }
                    else
                    {
                        intern_id="0";

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
        final StorageReference storageReference = ref.child("Brochures_for_intern").child(id).child(intern_id);
        storageReference.putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressDialog.hide();
                        String upload = uri.toString();
                        file=upload;
                        Toast.makeText(intern_profile.this,"File Upload Successful",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.setCancelable(true);
                Toast.makeText(intern_profile.this, "Failed to upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                if (currentProgress == 100) {
                    progressDialog.hide();
                    Toast.makeText(intern_profile.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
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
            Toast.makeText(intern_profile.this, "Please provide permission", Toast.LENGTH_LONG).show();
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
            Toast.makeText(intern_profile.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }

    public void add_intern() {
        check = 1;
        j1 = new intern("1", profile.getText().toString(), Float.parseFloat(ctc.getText().toString()), location.getText().toString(), file, Float.parseFloat(cpi.getText().toString()), dep, intern_requirements.getText().toString());
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
        intern_requirements.setEnabled(false);

        cpi.setText(String.valueOf(intern_det.getCutoff_cpi()));
        profile.setText(intern_det.getProfile());
        ctc.setText(String.valueOf(intern_det.getCtc()));
        location.setText(intern_det.getLocation());
        branch.setText(intern_det.getBranches());
        intern_requirements.setText(intern_det.getIntern_requirements());

        branch_button.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        upload.setText("View Selected File");
        select.setVisibility(View.INVISIBLE);

        status.setText(intern_det.getBrochure());
//getBrochure!=" " check
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(intern_det.getBrochure()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
