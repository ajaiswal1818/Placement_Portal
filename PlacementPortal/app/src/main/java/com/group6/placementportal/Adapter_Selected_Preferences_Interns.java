package com.group6.placementportal;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.Student;
import com.group6.placementportal.helper.ItemTouchHelperAdapter;
import com.group6.placementportal.helper.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Paul Burke (ipaulpro)
 */
public class Adapter_Selected_Preferences_Interns extends RecyclerView.Adapter<Adapter_Selected_Preferences_Interns.MyViewHolder>{

    Context context;
    ArrayList<Interns> profiles;
    Student user;

    public Adapter_Selected_Preferences_Interns(Context c, ArrayList<Interns> p, Student student)
    {
        context = c;
        profiles = p;
        user = student;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_selected_preferences, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        holder.company_name.setText(profiles.get(position).getCompany_name());
        holder.job_profile.setText(profiles.get(position).getProfile());
        holder.job_location.setText(profiles.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder
    {
        TextView company_name,job_profile,job_location;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            company_name = itemView.findViewById(R.id.txt_company_name);
            job_profile = itemView.findViewById(R.id.txt_job_profile);
            job_location = itemView.findViewById(R.id.txt_job_location);
            parentlayout = itemView.findViewById(R.id.cardview_selected_preferences);
        }
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}