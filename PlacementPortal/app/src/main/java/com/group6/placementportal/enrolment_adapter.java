package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.core.Tag;
import com.group6.placementportal.DatabasePackage.Student;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;

public class enrolment_adapter extends RecyclerView.Adapter<enrolment_adapter.MyViewHolder> {

    Context context;
    ArrayList<Student> students;

    public enrolment_adapter(Context c , ArrayList<Student> p)
    {
        context = c;
        students = p;
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
       /* holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //will show popup menu here

                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                return true;
                            case R.id.menu2:
                                //handle menu2 click
                                return true;
                            case R.id.menu3:
                                //handle menu3 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();

            }
        });*/
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(context, Apply_For_Student.class);
                intent.putExtra("job_profile", students.get(position));
                context.startActivity(intent); */
             // Log.d("Messgae","tag");



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
        TextView student_name;
        public MyViewHolder(View itemView) {
            super(itemView);
           /* company_name = itemView.findViewById(R.id.txt_company_name);
            job_profile = itemView.findViewById(R.id.txt_job_profile);
            job_location = itemView.findViewById(R.id.txt_job_location);*/
           parentlayout = itemView.findViewById(R.id.cardview_enrollments);
           student_name=itemView.findViewById(R.id.txt_student_name);


        }
    }
}