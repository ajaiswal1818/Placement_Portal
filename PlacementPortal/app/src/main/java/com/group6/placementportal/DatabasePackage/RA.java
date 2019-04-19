package com.group6.placementportal.DatabasePackage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.group6.placementportal.R;
import com.microsoft.identity.client.PublicClientApplication;

import static android.app.Activity.RESULT_OK;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link JRF.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link JRF#newInstance} factory method to
// * create an instance of this fragment.
// */
public class RA extends Fragment {

    private PublicClientApplication sampleApp;
    private Student user;
    private Button uploadphoto;
    private Button uploadsign;
    private Button apply;
    private Uri pdfUriphoto,pdfUrisign;

    private ProgressDialog progressDialog;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private TextView file_name_photo;
    private TextView file_name_sign;
    private EditText name_org,designation,from_duration,to_duration,type_of_job;
    private String mnameorg,mdesignation,mfrom_duration,mto_duration,mtype_of_job;

    public RA() {
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadphoto = getView().findViewById(R.id.btn_uploadphoto);
        uploadsign =   getView().findViewById(R.id.btn_uploadsign);
        apply = getView().findViewById(R.id.btn_apply);
        file_name_photo = getView().findViewById(R.id.txt_uploadphoto);
        file_name_sign = getView().findViewById(R.id.txt_uploadsign);
        name_org = getView().findViewById(R.id.txt_name_of_org);
        designation = getView().findViewById(R.id.txt_designation);
        from_duration = getView().findViewById(R.id.txt_from);
        to_duration = getView().findViewById(R.id.txt_to);
        type_of_job = getView().findViewById(R.id.txt_nature_of_work);


        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPDFphoto();
            }
        });

        uploadsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPDFsign();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnameorg = name_org.getText().toString();
                mdesignation = designation.getText().toString();
                mfrom_duration = from_duration.getText().toString();
                mto_duration = to_duration.getText().toString();
                mtype_of_job = type_of_job.getText().toString();

                if(mnameorg==null){
                    return;
                }
                if(mdesignation==null){
                    return;
                }
                if(mfrom_duration==null){
                    return;
                }
                if(mto_duration==null){
                    return;
                }
                if(mtype_of_job==null){
                    return;
                }


            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (Student)getActivity().getIntent().getSerializableExtra("user");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();

    }

    private void getPDFsign() {
        //so if the permission is not available user will go to the screen to allow storage permission
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            selectPDFsign();
        }
        else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
        }
    }

    private void getPDFphoto() {
        //so if the permission is not available user will go to the screen to allow storage permission
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            selectPDFphoto();
        }
        else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDFphoto();
        }
        else if(requestCode==10 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPDFsign();
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
        }
    }

    private void selectPDFsign() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    private void selectPDFphoto() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,87);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUrisign = data.getData();
            file_name_sign.setText(data.getData().getLastPathSegment());
        }
        else if(requestCode==87 && resultCode==RESULT_OK && data!=null){
            pdfUriphoto = data.getData();
            file_name_photo.setText(data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(),"Select a file",Toast.LENGTH_SHORT).show();
        }
    }


    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFilesign(Uri data) {
        progressDialog = new ProgressDialog(getActivity().getApplicationContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Requesting Process");
        progressDialog.show();

        final StorageReference ref = mStorageReference.child("Uploads").child("RA").child(user.getWebmailID()).child("Sign");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Sign").setValue(upload);
                                Toast.makeText(getActivity().getApplicationContext(),"File Upload Successful",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.hide();
                Toast.makeText(getActivity().getApplicationContext(), "File Upload Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });

    }

    private void uploadFilephoto(Uri data) {
        progressDialog = new ProgressDialog(getActivity().getApplicationContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();

        final StorageReference ref = mStorageReference.child("Uploads").child("RA").child(user.getWebmailID()).child("Photo");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Photo").setValue(upload);
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Student_Name").setValue(user.getFullName());
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Name_of_Org").setValue(mnameorg);
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Designation").setValue(mdesignation);
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("From_Duration").setValue(mfrom_duration);
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("To_Duration").setValue(mto_duration);
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Type_of_Job").setValue(mtype_of_job);
                                mDatabaseReference.child("RA").child(user.getWebmailID()).child("Webmail").setValue(user.getWebmailID());
                                Toast.makeText(getActivity().getApplicationContext(),"File Upload Successful",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.hide();
                Toast.makeText(getActivity().getApplicationContext(), "File Upload Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ra, container, false);
    }


}
