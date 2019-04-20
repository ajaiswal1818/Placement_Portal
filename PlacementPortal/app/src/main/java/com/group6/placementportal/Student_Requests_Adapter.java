package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;

public class Student_Requests_Adapter extends RecyclerView.Adapter<Student_Requests_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Student> profiles;

    public Student_Requests_Adapter(Context c , ArrayList<Student> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_student_requests, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(profiles.get(position).getFullName());
        holder.webmail.setText(profiles.get(position).getWebmailID());
        holder.prog.setText(profiles.get(position).getProgramme());
        holder.dept.setText(profiles.get(position).getDepartment());
        holder.contact.setText(profiles.get(position).getContact());

        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Student_Profile_Approval.class);
                intent.putExtra("user",profiles.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,webmail,prog,dept,contact;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_Student_Name);
            webmail = itemView.findViewById(R.id.txt_webmail);
            prog = itemView.findViewById(R.id.txt_prog);
            dept = itemView.findViewById(R.id.txt_dept);
            contact = itemView.findViewById(R.id.txt_contact);
            parentlayout = itemView.findViewById(R.id.cardview_student_requests);
        }
    }
}