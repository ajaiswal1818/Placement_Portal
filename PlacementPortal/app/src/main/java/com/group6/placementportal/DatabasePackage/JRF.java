package com.group6.placementportal.DatabasePackage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.RadioButton;
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
import com.group6.placementportal.Student_JRF;
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
public class JRF extends Fragment {

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
    private EditText application,programmingLanguage,year,project,post;
    private String mapplication,mprogrammingLanguage,myear,mproject,mpost;
    private RadioButton yesBtn,noBtn;

    public JRF() {
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadphoto = getView().findViewById(R.id.btn_uploadphoto);
        uploadsign =   getView().findViewById(R.id.btn_uploadsign);
        apply = getView().findViewById(R.id.btn_apply);
        file_name_photo = getView().findViewById(R.id.txt_uploadphoto);
        file_name_sign = getView().findViewById(R.id.txt_uploadsign);
        application =  getView().findViewById(R.id.editApplication);
        programmingLanguage = getView().findViewById(R.id.editProgLang);
        year = getView().findViewById(R.id.editYear);
        project = getView().findViewById(R.id.editapplyproject);
        post = getView().findViewById(R.id.editapplypost);
        yesBtn = getView().findViewById(R.id.rbtnyes);
        noBtn = getView().findViewById(R.id.rbtnno);


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
                mapplication = application.getText().toString();
                mprogrammingLanguage = programmingLanguage.getText().toString();
                myear = year.getText().toString();
                mproject = project.getText().toString();
                mpost = post.getText().toString();

                if(mapplication==null){
                    return;
                }
                if(mprogrammingLanguage==null){
                    return;
                }
                if(myear==null){
                    return;
                }
                if(mproject==null){
                    return;
                }
                if(mpost==null){
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
            file_name_sign.setText(data.getData().getLastPathSegment());
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

        final StorageReference ref = mStorageReference.child("Uploads").child("JRF").child(user.getWebmailID()).child("Sign");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Sign").setValue(upload);
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

        final StorageReference ref = mStorageReference.child("Uploads").child("JRF").child(user.getWebmailID()).child("Photo");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.hide();
                                String upload = uri.toString();
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Photo").setValue(upload);
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Student_Name").setValue(user.getFullName());
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Application_No").setValue(mapplication);
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("ProgrammingLanguages").setValue(mprogrammingLanguage);
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("YearandType").setValue(myear);
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("AppliedProject").setValue(mproject);
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Qualified").setValue("yes");
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Webmail").setValue(user.getWebmailID());
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
        return inflater.inflate(R.layout.fragment_jr, container, false);
    }


}
