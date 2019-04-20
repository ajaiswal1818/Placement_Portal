package com.group6.placementportal;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.res.Resources.Theme;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.Student;
import com.group6.placementportal.DatabasePackage.company;

import java.util.ArrayList;

import static com.group6.placementportal.R.id.enrolments_recycler;
import static com.group6.placementportal.company_enrollments.PlaceholderFragment.*;

public class company_enrollments extends AppCompatActivity {
    private static DatabaseReference reference;
    private DatabaseReference ref_jobs;
    public  RecyclerView recyclerView;
    private  ArrayList<Student> list;
    private  com.group6.placementportal.enrolment_adapter adapter;
    private admin_enrollment_adapter adapter1;
    public Student p;
    public int screen;
    public boolean is_job;
    public boolean to_show;
    public company c;
    public void set_adapter(String job_id,int position){
        if(is_job){
            screen=getIntent().getIntExtra("Screen",0);
            if(screen==0){
                ArrayList <String> cv=getIntent().getStringArrayListExtra("cv");
                boolean is_job=getIntent().getBooleanExtra("is_job",true);
                // adapter = new enrolment_adapter(company_enrollments.this,list,job_id,position,cv,is_job);
                Log.d("adapter","changed");
                recyclerView=findViewById(R.id.enrolments_recycler);
                adapter=new enrolment_adapter(company_enrollments.this,list,job_id,position,cv,is_job);

                if(recyclerView!=null){
                    recyclerView.clearDisappearingChildren();
                    recyclerView.setLayoutManager((new LinearLayoutManager(company_enrollments.this)));
                    recyclerView.setAdapter(adapter);
                }

            }
            else if(screen==1){
                ArrayList <String> cv=getIntent().getStringArrayListExtra("cv");
                boolean is_job=getIntent().getBooleanExtra("is_job",true);

                adapter1=new admin_enrollment_adapter(company_enrollments.this,list,job_id,position,cv,is_job);
                recyclerView=findViewById(R.id.enrolments_recycler);
                if(recyclerView!=null){
                    recyclerView.clearDisappearingChildren();
                    recyclerView.setLayoutManager((new LinearLayoutManager(company_enrollments.this)));
                    recyclerView.setAdapter(adapter1);
                }


            }
        }
        else{
            screen=getIntent().getIntExtra("Screen",0);
            if(screen==0){
                ArrayList <String> cv=getIntent().getStringArrayListExtra("cv");
                boolean is_job=getIntent().getBooleanExtra("is_job",true);
                // adapter = new enrolment_adapter(company_enrollments.this,list,job_id,position,cv,is_job);
                Log.d("adapter","changed");
                recyclerView=findViewById(R.id.enrolments_recycler);
                adapter=new enrolment_adapter(company_enrollments.this,list,job_id,position,cv,is_job);
                if(recyclerView!=null){
                    recyclerView.clearDisappearingChildren();
                    recyclerView.setLayoutManager((new LinearLayoutManager(company_enrollments.this)));
                    recyclerView.setAdapter(adapter);
                }


            }
            else if(screen==1){
                ArrayList <String> cv=getIntent().getStringArrayListExtra("cv");
                boolean is_job=getIntent().getBooleanExtra("is_job",true);

                adapter1=new admin_enrollment_adapter(company_enrollments.this,list,job_id,position,cv,is_job);
                recyclerView=findViewById(R.id.enrolments_recycler);
                if(recyclerView!=null){
                    recyclerView.clearDisappearingChildren();

                    recyclerView.setLayoutManager((new LinearLayoutManager(company_enrollments.this)));
                    recyclerView.setAdapter(adapter1);
                }



            }
        }

    }

    public void check_if_to_be_shown(final Student p,String job_id,final int position){
        if(is_job){
            ref_jobs=FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(p.getWebmailID());
            Log.d("jobI-d",job_id);
            to_show=false;
            ref_jobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                    // Student p_copy=p;


                    String pos_db="";
                    pos_db = (String) dataSnapshot2.child("Status").getValue();
                    String approval_status="";
                    approval_status = (String) dataSnapshot2.child("Approval").getValue();
                    Log.d("pos_db and position",pos_db+"   "+Integer.toString(position));
                    screen=getIntent().getIntExtra("Screen",0);
                    if(pos_db!=null && approval_status!=null && pos_db.equals(Integer.toString(position) ) && ( (screen==0 && approval_status.equals("Yes") ) || screen==1 ) ){

                        to_show=true;

                        list.add(p);
                        Log.w("lev2 check if to be shown",p.getWebmailID());
                        String job_id1=getIntent().getStringExtra("Job");
                        set_adapter(job_id1,position);
                        //recyclerView.onFinishTemporaryDetach();


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            ref_jobs=FirebaseDatabase.getInstance().getReference().child("Interns").child(job_id).child("Applied Students").child(p.getWebmailID());
            Log.d("jobI-d",job_id);
            to_show=false;
            ref_jobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                    // Student p_copy=p;


                    String pos_db="";
                    pos_db = (String) dataSnapshot2.child("Status").getValue();
                    String approval_status="";
                    approval_status = (String) dataSnapshot2.child("Approval").getValue();
                    Log.d("pos_db and position",pos_db+"   "+Integer.toString(position));
                    screen=getIntent().getIntExtra("Screen",0);
                    if(pos_db!=null && approval_status!=null && pos_db.equals(Integer.toString(position) ) && ( (screen==0 && approval_status.equals("Yes") ) || screen==1 ) ){

                        to_show=true;

                        list.add(p);
                        Log.w("lev2 check if to be shown",p.getWebmailID());
                        String job_id1=getIntent().getStringExtra("Job");
                        set_adapter(job_id1,position);
                        //recyclerView.onFinishTemporaryDetach();


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        set_adapter(job_id,position);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        screen=getIntent().getIntExtra("Screen",0);
        if(screen==0){

                Intent act=new Intent(this,company_enrolments_screen1.class);
                act.putExtra("is_job",is_job);
                this.c=(company) getIntent().getSerializableExtra("company");
                act.putExtra("MyClass",c);
                startActivity(act);
                company_enrollments.this.finish();




        }
        else{
          //  Intent act=new Intent(this,company_dashboard.class);
            //finish();
            //startActivity(act);
            Intent act=new Intent(this,admin_enrollments_screen1.class);
            act.putExtra("is_job",is_job);

            startActivity(act);
            company_enrollments.this.finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_enrollments);


        if(isNetworkAvailable()==false){
            Toast.makeText(company_enrollments.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Students Applied",
                        "Selected After Technical Round",
                        "Final Shortlisted",
                        "Rejected"
                }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                is_job=getIntent().getBooleanExtra("is_job",true);
                screen=getIntent().getIntExtra("Screen",0);

                if(is_job){
                    recyclerView=findViewById(R.id.enrolments_recycler);
                    final String job_id=getIntent().getStringExtra("Job");
                    reference = FirebaseDatabase.getInstance().getReference().child("Student");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            recyclerView=findViewById(R.id.enrolments_recycler);
                            list = new ArrayList<Student>();
                            Log.w("Interns","yeaa ");
                            ArrayList <String> s_list= getIntent().getStringArrayListExtra("MyClass");
                            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                            {

                                p = dataSnapshot1.getValue(Student.class);
                                if(s_list.contains(p.getWebmailID())     ){
                                    Log.w("added a student lev1 in jobs",p.getWebmailID());
                                    check_if_to_be_shown(p,job_id,position);




                                }



                            }
                            set_adapter(job_id,position);
                            if(list.isEmpty()){
                                Log.d("list","empty");
                            }
                            else{
                                Log.d("list","not empty");
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(company_enrollments.this, "Ooopsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    recyclerView=findViewById(R.id.enrolments_recycler);
                    final String job_id=getIntent().getStringExtra("Job");
                    reference = FirebaseDatabase.getInstance().getReference().child("Student");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            recyclerView=findViewById(R.id.enrolments_recycler);
                            list = new ArrayList<Student>();
                            Log.w("Interns!!!!","yeaa ");
                            ArrayList <String> s_list= getIntent().getStringArrayListExtra("MyClass");
                            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                            {

                                p = dataSnapshot1.getValue(Student.class);
                                if(s_list.contains(p.getWebmailID()) ){
                                    Log.w("added a student lev1",p.getWebmailID());
                                    check_if_to_be_shown(p,job_id,position);





                                }



                            }
                            set_adapter(job_id,position);



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(company_enrollments.this, "Ooopsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_company_enrollments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_company_enrollments, container, false);
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            TextView check=(TextView) rootView.findViewById(R.id.check_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            check.setText("YUPPP!!");*/
         //   recyclerView=(RecyclerView) rootView.findViewById(enrolments_recycler);

            return rootView;
        }
    }
}
