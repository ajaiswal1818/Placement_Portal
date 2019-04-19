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
import android.util.Log;
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
import com.group6.placementportal.DatabasePackage.notices2company;

import java.util.ArrayList;

public class admin_notices extends AppCompatActivity {
    private DatabaseReference db;
    private EditText title;
    private EditText description;
    private TextView status;
    private Button select;
    private Button upload;
    private Button send;
    private Button select_recepients;
    private Uri pdfUri;
    private DatabaseReference add_comp;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private StorageReference ref;
    private ProgressDialog progressDialog;
    private String file="";
    private String company_list;
    private notices2company new_notice;
    private String count="0";
    private ArrayList<Integer> selected_companies = new ArrayList<>();
    private String[] available_companies;
    private ArrayList<String> selected_companies_id =new ArrayList<>();
    private ArrayList<String> available_companies_arraylist = new ArrayList<>();
    private boolean[] checked_companies;
    private ProgressDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notices);

        if(isNetworkAvailable()==false){
            Toast.makeText(admin_notices.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }


        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        send=findViewById(R.id.send_button);
        select=findViewById(R.id.select_file);
        upload=findViewById(R.id.upload);
        select_recepients=findViewById(R.id.show_company);
        status=findViewById(R.id.status);
        f0();
        select_recepients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(admin_notices.this);
                mBuilder.setTitle("Select Companies");
               // mBuilder.setCancelable(false);
                mBuilder.setMultiChoiceItems(available_companies, checked_companies, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            if (!selected_companies.contains(which)) {
                                selected_companies.add(which);
                            }
                        } else {
                            if (selected_companies.contains(which)) {
                                selected_companies.remove(selected_companies.indexOf(which));
                            }
                        }
                    }
                });
               /* String Notif = dataSnapshot.getValue(String.class);
                String[] split_IDs = Notif.split("\\,");
*/
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = "";
                        int i;
                        for (i = 0; i < selected_companies.size(); i++) {
                            str = available_companies[selected_companies.get(i)];
                            String[] split_IDs = str.split("\\:");
                            str= split_IDs[0];
                            selected_companies_id.add(str);
                        }
                    }
                });
            /*    mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/
                mBuilder.setNegativeButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checked_companies.length; i++) {
                            checked_companies[i] = false;

                        }
                        selected_companies.clear();
                        selected_companies_id.clear();
                    }
                });
                mBuilder.setNeutralButton("Select all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < available_companies.length; i++) {
                            checked_companies[i] = true;
                            if (!selected_companies.contains(i)) {
                                selected_companies.add(i);
                            }
                        }
                        Log.d("c_id_size",String.valueOf(selected_companies.size()));
                        selected_companies_id.clear();
                        for (int i = 0; i < selected_companies.size(); i++) {
                            String str = available_companies[selected_companies.get(i)];
                            String[] split_IDs = str.split("\\:");
                            str= split_IDs[0];
                            selected_companies_id.add(str);
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().equals("")){
                    title.setError("Can't be empty");
                    return;
                }
                if(description.getText().toString().equals("")){
                    description.setError("Can't be empty");
                    return;
                }
                if(selected_companies_id.size()==0)
                {
                    Toast.makeText(admin_notices.this, "Select at least one company to send notice", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    company_list="";
                    for(String s : selected_companies_id)
                    {
                        String temp = s + ",";
                        company_list+=temp;
                    }
                    if (company_list != null && company_list.length() > 0 && company_list.charAt(company_list.length() - 1) == 'x') {
                        company_list = company_list.substring(0, company_list.length() - 1);
                    }
                    add_to_company();
                }

            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(admin_notices.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    selectPDF();
                } else {
                    ActivityCompat.requestPermissions(admin_notices.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);

                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdfUri != null) {
                    uploadFile(pdfUri);

                } else {
                    Toast.makeText(admin_notices.this, "Select a file", Toast.LENGTH_SHORT).show();
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


        uploadpdf(pdfUri);

    }
    public void uploadpdf(Uri pdfUri){
        add_comp = FirebaseDatabase.getInstance().getReference();
        ref= FirebaseStorage.getInstance().getReference();
        final StorageReference storageReference = ref.child("Notices to company").child(String.valueOf(Long.parseLong(count)+1));
        storageReference.putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressDialog.hide();
                        String upload = uri.toString();
                        file=upload;
                        Toast.makeText(admin_notices.this,"File Upload Successful",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.setCancelable(true);
                Toast.makeText(admin_notices.this, "Failed to upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                if (currentProgress == 100) {
                    progressDialog.hide();
                    Toast.makeText(admin_notices.this, "Successfully Uploaded", Toast.LENGTH_LONG).show();
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
            Toast.makeText(admin_notices.this, "Please provide permission", Toast.LENGTH_LONG).show();
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
            Toast.makeText(admin_notices.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
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
    public void convert_to_array()
    {
        available_companies = new String[available_companies_arraylist.size()];
        // ArrayList to Array Conversion
        for (int j = 0; j < available_companies_arraylist.size(); j++) {

            // Assign each value to String array
            available_companies[j] = available_companies_arraylist.get(j);
        }
        checked_companies = new boolean[available_companies.length];
        dialog1.setCancelable(true);
        dialog1.hide();
    }
    public void add_to_company()
    {
        add_comp=FirebaseDatabase.getInstance().getReference("notices2company");
        //add_comp.child("total_count").setValue(String.valueOf(Long.parseLong(count)+1));
        String key = add_comp.push().getKey();
        new_notice=new notices2company(key,title.getText().toString(),description.getText().toString(),company_list,file);
        add_comp.child(key).setValue(new_notice);

        db=FirebaseDatabase.getInstance().getReference("Company");
        for(String id : selected_companies_id)
        {
            db.child(id).child("notices").child(key).setValue(new_notice);
        }

    }
    public void f1()
    {

        //f2();
    }
    public void f2()
    {
        db=FirebaseDatabase.getInstance().getReference();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("Company"))
                    {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.child("Company").getChildren())
                        {
                            if(dataSnapshot1.hasChild("username") && dataSnapshot1.hasChild("company_name") && dataSnapshot1.hasChild("approved") && dataSnapshot1.child("approved").getValue().toString().equals("Approved"))
                            {
                                available_companies_arraylist.add(dataSnapshot1.child("username").getValue().toString() + ":" + dataSnapshot1.child("company_name").getValue().toString());
                            }
                        }
                        convert_to_array();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void f0()
    {
        dialog1 = new ProgressDialog(admin_notices.this);
        dialog1.setMessage("Please Wait");
        dialog1.setCancelable(false);
        dialog1.show();
        f2();
        /*db=FirebaseDatabase.getInstance().getReference();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("notices2company"))
                    {
                        if(dataSnapshot.hasChild("total_count")){
                            count=dataSnapshot.child("total_count").getValue().toString();
                            Log.w("total_count",count);
                            f2();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
}
