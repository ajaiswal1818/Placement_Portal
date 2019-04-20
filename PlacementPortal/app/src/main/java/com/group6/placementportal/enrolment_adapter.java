package com.group6.placementportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Student;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class enrolment_adapter extends RecyclerView.Adapter<enrolment_adapter.MyViewHolder> {

    Activity context;
    ArrayList<Student> students;
    String job;
    int which;
    ArrayList <String> cv;
    private static DatabaseReference reference;
    boolean is_job;
    public enrolment_adapter(Activity c , ArrayList<Student> p,String job_id,int pos,ArrayList <String> link,boolean job_or_not)
    {
        context = c;
        students = p;
        job=job_id;
        which=pos;
        cv=link;
        is_job=job_or_not;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.enrollment_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        /*holder.company_name.setText(students.get(position).getCompany_name());
        holder.job_profile.setText(students.get(position).getProfile());
        holder.job_location.setText(students.get(position).getLocation());*/
        holder.student_name.setText(students.get(position).getFullName());
        Log.w("adapter final", Integer.toString(position)+" "+students.get(position).getFullName());
        holder.student_rno.setText(students.get(position).getRollNo());
        holder.CV.setText(cv.get(position));


        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(context, Apply_For_Student.class);
                intent.putExtra("job_profile", students.get(position));
                context.startActivity(intent); */
             // Log.d("Messgae","tag");

                single_dialog_companyenrollments_0 menu=new single_dialog_companyenrollments_0(students.get(position).getWebmailID(),job,which,0,is_job);
                //FragmentManager f=context.getPackageManager();
               // menu.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                FragmentActivity f=(FragmentActivity) context;
                menu.show(f.getSupportFragmentManager(),"menu");

                //displaying the popup

            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        public View buttonViewOption;
        // public View parentlayout;
        /* TextView company_name,job_profile,job_location;*/
       CardView parentlayout;
        TextView student_name,student_rno,CV;
        public MyViewHolder(View itemView) {
            super(itemView);
           /* company_name = itemView.findViewById(R.id.txt_company_name);
            job_profile = itemView.findViewById(R.id.txt_job_profile);
            job_location = itemView.findViewById(R.id.txt_job_location);*/
           parentlayout = itemView.findViewById(R.id.cardview_enrollments);
           student_name=itemView.findViewById(R.id.txt_student_name);
            student_rno=itemView.findViewById(R.id.txt_student_rollno);
            CV=itemView.findViewById(R.id.txt_student_cv);

        }
    }
}