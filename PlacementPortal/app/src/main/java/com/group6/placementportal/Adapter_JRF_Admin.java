package com.group6.placementportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.JRF_applications;

import java.util.ArrayList;

public class
Adapter_JRF_Admin extends RecyclerView.Adapter<Adapter_JRF_Admin.MyViewHolder> {

    Context context;
    ArrayList<JRF_applications> profiles;

    public Adapter_JRF_Admin(Context c , ArrayList<JRF_applications> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_jrf, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.application_no.setText(profiles.get(position).getApplication_No());
        holder.student_name.setText(profiles.get(position).getStudent_Name());
        holder.project.setText(profiles.get(position).getAppliedProject());
        holder.post.setText(profiles.get(position).getAppliedPost());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, Apply_For_Jobs.class);
//                intent.putExtra("job_profile", profiles.get(position));
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView application_no,student_name,project,post;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            application_no = itemView.findViewById(R.id.txt_application_no);
            student_name = itemView.findViewById(R.id.txt_Student_Name);
            project = itemView.findViewById(R.id.txt_project);
            post = itemView.findViewById(R.id.txt_post);
            parentlayout = itemView.findViewById(R.id.cardview_jrf);
        }
    }
}