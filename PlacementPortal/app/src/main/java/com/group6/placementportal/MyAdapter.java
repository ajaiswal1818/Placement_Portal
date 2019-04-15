package com.group6.placementportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.Jobs;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Jobs> profiles;

    public MyAdapter(Context c , ArrayList<Jobs> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.company_name.setText(profiles.get(position).getCompany_name());
        holder.job_profile.setText(profiles.get(position).getProfile());
        holder.job_location.setText(profiles.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView company_name,job_profile,job_location;
        public MyViewHolder(View itemView) {
            super(itemView);
            company_name = (TextView) itemView.findViewById(R.id.txt_company_name);
            job_profile = (TextView) itemView.findViewById(R.id.txt_job_profile);
            job_location = (TextView) itemView.findViewById(R.id.txt_job_location);
        }
    }
}