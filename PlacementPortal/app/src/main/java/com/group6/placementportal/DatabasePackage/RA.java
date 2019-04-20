package com.group6.placementportal.DatabasePackage;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.group6.placementportal.JRF_Application_Requests;
import com.group6.placementportal.JRF_Approval_Profile;
import com.group6.placementportal.R;
import com.microsoft.identity.client.PublicClientApplication;

import java.util.Calendar;

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
    private Uri pdfUriphoto, pdfUrisign;

    private ProgressDialog progressDialog;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private TextView file_name_photo, from_duration, to_duration;
    private TextView file_name_sign;
    private EditText name_org, designation, type_of_job;
    private String mnameorg, mdesignation, mtype_of_job, mfrom_duration, mto_duration;
    private boolean has_applied=false;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1;

    public RA() {
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadphoto = getView().findViewById(R.id.btn_uploadphoto);
        uploadsign = getView().findViewById(R.id.btn_uploadsign);
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
//        to_duration.setEnabled(false);
//        from_duration.setEnabled(false);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mytag", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                to_duration.setText(date);
            }
        };
        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mytag", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                from_duration.setText(date);
            }
        };
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

        to_duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
            }
        });
        from_duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDatabaseReference.child("RA").addListenerForSingleValueEvent(new ValueEventListener() {
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
                            mnameorg = name_org.getText().toString();
                            mdesignation = designation.getText().toString();
                            mfrom_duration = from_duration.getText().toString();
                            mto_duration = to_duration.getText().toString();
                            mtype_of_job = type_of_job.getText().toString();

                            if (mnameorg.equals("")) {
                                name_org.setError("Required");
                                return;
                            }
                            if (mdesignation.equals("")) {
                                designation.setError("Required");
                                return;
                            }
                            if (mfrom_duration.equals("")) {
                                from_duration.setError("Required");
                                return;
                            }
                            if (mto_duration.equals("")) {
                                to_duration.setError("Required");
                                return;
                            }
                            if (mtype_of_job.equals("")) {
                                type_of_job.setError("Required");
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
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            user = (Student) getActivity().getIntent().getSerializableExtra("user");

            mDatabaseReference = FirebaseDatabase.getInstance().getReference();
            mStorageReference = FirebaseStorage.getInstance().getReference();

        }

        private void getPDFsign () {
            //so if the permission is not available user will go to the screen to allow storage permission
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                selectPDFsign();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
            }
        }

        private void getPDFphoto () {
            //so if the permission is not available user will go to the screen to allow storage permission
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                selectPDFphoto();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectPDFphoto();
            } else if (requestCode == 10 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectPDFsign();
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }

        private void selectPDFsign () {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 86);
        }

        private void selectPDFphoto () {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 87);
        }


        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
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
        private void uploadFilesign (Uri data){

            final StorageReference ref = mStorageReference.child("Uploads").child("RA").child(user.getWebmailID()).child("Sign");
            ref.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String upload = uri.toString();
                                    mDatabaseReference.child("RA").child(user.getWebmailID()).child("Sign").setValue(upload);
                                    Toast.makeText(getActivity().getApplicationContext(), "File Upload Successful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getActivity().getApplicationContext(), "File Upload Failed", Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });

        }

        private void uploadFilephoto (Uri data){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Please Wait");
            progressDialog.show();
            final RA_applications ra_applications=new RA_applications(mnameorg,mdesignation,mfrom_duration,mto_duration,mtype_of_job,user.getFullName(),"","",user.getWebmailID());
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
                                    mDatabaseReference.child("RA").child(user.getWebmailID()).setValue(ra_applications);
                                    Toast.makeText(getActivity().getApplicationContext(), "File Upload Successful", Toast.LENGTH_SHORT).show();
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
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_ra, container, false);
        }


    }
