package com.group6.placementportal.DatabasePackage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
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
import com.group6.placementportal.GivePreference;
import com.group6.placementportal.R;
import com.microsoft.identity.client.PublicClientApplication;

import java.io.File;

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
    private Uri pdfUriphoto, pdfUrisign;

    private ProgressDialog progressDialog;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private TextView file_name_photo;
    private TextView file_name_sign;
    private EditText application, programmingLanguage, year, project, post;
    private String mapplication, mprogrammingLanguage, myear, mproject, mpost, mqualifies;
    private RadioButton yesBtn, noBtn;
    private boolean has_applied = false;

    public JRF() {
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadphoto = getView().findViewById(R.id.btn_uploadphoto);
        uploadsign = getView().findViewById(R.id.btn_uploadsign);
        apply = getView().findViewById(R.id.btn_apply);
        file_name_photo = getView().findViewById(R.id.txt_uploadphoto);
        file_name_sign = getView().findViewById(R.id.txt_uploadsign);
        application = getView().findViewById(R.id.editApplication);
        programmingLanguage = getView().findViewById(R.id.editProgLang);
        year = getView().findViewById(R.id.editYear);
        project = getView().findViewById(R.id.editapplyproject);
        post = getView().findViewById(R.id.editapplypost);
        yesBtn = getView().findViewById(R.id.rbtnyes);
        noBtn = getView().findViewById(R.id.rbtnno);

        mDatabaseReference.child("Student").child(user.getWebmailID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("ProfilePending")) {
                    String var = dataSnapshot.child("ProfilePending").getValue(String.class);
                    if (var.equals("Pending")) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());

                        mBuilder.setTitle("Your Profile Request is Pending");
                        mBuilder.setCancelable(false);
                        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();
                        apply.setEnabled(false);
                    }
                }
                else if (!dataSnapshot.hasChild("AcademicDetails")) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                    mBuilder.setTitle("Complete Your Profile First");
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                    apply.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
                mDatabaseReference.child("JRF").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        has_applied = dataSnapshot.hasChild(user.getWebmailID());
                        if (has_applied) {
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                            mBuilder.setTitle("You have already applied");
                            mBuilder.setCancelable(false);
                            mBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();
                        } else {

                            mapplication = application.getText().toString();
                            mprogrammingLanguage = programmingLanguage.getText().toString();
                            myear = year.getText().toString();
                            mproject = project.getText().toString();
                            mpost = post.getText().toString();
                            if (yesBtn.isChecked()) {
                                mqualifies = "yes";
                            } else if (noBtn.isChecked()) {
                                mqualifies = "no";
                            }

                            if (mapplication.equals("")) {
                                application.setError("Required");
                                return;
                            }
                            if (mprogrammingLanguage.equals("")) {
                                programmingLanguage.setError("Required");
                                return;
                            }
                            if (myear.equals("")) {
                                year.setError("Required");
                                return;
                            }
                            if (mproject.equals("")) {
                                project.setError("Required");
                                return;
                            }
                            if (mpost.equals("")) {
                                post.setError("Required");
                                return;
                            }
                            if (mqualifies == null) {
                                Toast.makeText(getActivity(), "Choose either yes or no", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (pdfUrisign != null && pdfUriphoto != null) {
                                uploadFilesign(pdfUrisign);
                                uploadFilephoto(pdfUriphoto);
                            } else {
                                Toast.makeText(getContext(), "Select File", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (Student) getActivity().getIntent().getSerializableExtra("user");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();

    }

    private void getPDFsign() {
        //so if the permission is not available user will go to the screen to allow storage permission
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            selectPDFsign();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        }
    }

    private void getPDFphoto() {
        //so if the permission is not available user will go to the screen to allow storage permission
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            selectPDFphoto();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPDFphoto();
        } else if (requestCode == 10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPDFsign();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
        }
    }

    private void selectPDFsign() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    private void selectPDFphoto() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 87);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUrisign = data.getData();
            file_name_sign.setText(data.getData().getLastPathSegment());
        } else if (requestCode == 87 && resultCode == RESULT_OK && data != null) {
            pdfUriphoto = data.getData();
            file_name_photo.setText(data.getData().getLastPathSegment());
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Select a file", Toast.LENGTH_SHORT).show();
        }
    }


    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFilesign(Uri data) {

        final StorageReference ref = mStorageReference.child("Uploads").child("JRF").child(user.getWebmailID()).child("Sign");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String upload = uri.toString();
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).child("Sign").setValue(upload);
                                Toast.makeText(getActivity(), "File Upload Successful", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "File Upload Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });

    }

    private void uploadFilephoto(Uri data) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Please Wait");
        progressDialog.show();

        final JRF_applications jrf_applications = new JRF_applications(mapplication, mprogrammingLanguage, myear, mproject, mpost, mqualifies, user.getFullName(), "", "", user.getWebmailID());

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
                                mDatabaseReference.child("JRF").child(user.getWebmailID()).setValue(jrf_applications);
                                Toast.makeText(getActivity(), "File Upload Successful", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.hide();
                Toast.makeText(getActivity(), "File Upload Failed", Toast.LENGTH_LONG).show();
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
