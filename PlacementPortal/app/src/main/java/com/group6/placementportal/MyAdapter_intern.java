/*
package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.core.Tag;
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Student;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Interns> profiles;
    Student user;

    public MyAdapter(Context c , ArrayList<Interns> p, Student student)
    {
        context = c;
        profiles = p;
        user = student;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.company_name.setText(profiles.get(position).getCompany_name());
        holder.intern_profile.setText(profiles.get(position).getProfile());
        holder.intern_location.setText(profiles.get(position).getLocation());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Apply_For_Interns.class);
                intent.putExtra("intern_profile", profiles.get(position));
                intent.putExtra("user", user);
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
        TextView company_name,intern_profile,intern_location;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.txt_company_name);
            intern_profile = itemView.findViewById(R.id.txt_intern_profile);
            intern_location = itemView.findViewById(R.id.txt_intern_location);
            parentlayout = itemView.findViewById(R.id.cardview_apply_interns);
        }
    }
}*/
