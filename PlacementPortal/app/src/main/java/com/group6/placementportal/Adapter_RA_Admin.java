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

import com.group6.placementportal.DatabasePackage.RA_applications;

import java.util.ArrayList;

public class
Adapter_RA_Admin extends RecyclerView.Adapter<Adapter_RA_Admin.MyViewHolder> {

    Context context;
    ArrayList<RA_applications> profiles;

    public Adapter_RA_Admin(Context c , ArrayList<RA_applications> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_ra, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name_of_org.setText(profiles.get(position).getName_of_Org());
        holder.student_name.setText(profiles.get(position).getStudent_Name());
        holder.work_type.setText(profiles.get(position).getType_of_Job());
        holder.designations.setText(profiles.get(position).getDesignation());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RA_Approval_Profile.class);
                intent.putExtra("job_profile", profiles.get(position));
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
        TextView name_of_org,student_name,work_type,designations;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_of_org = itemView.findViewById(R.id.txt_name_of_org);
            student_name = itemView.findViewById(R.id.txt_Student_Name);
            work_type = itemView.findViewById(R.id.txt_nature_of_work);
            designations = itemView.findViewById(R.id.txt_designation);
            parentlayout = itemView.findViewById(R.id.cardview_ra);
        }
    }


}