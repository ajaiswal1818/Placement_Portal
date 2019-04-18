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
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import java.util.ArrayList;

public class job_adapter extends RecyclerView.Adapter<job_adapter.MyViewHolder> {

    Context context;
    ArrayList<job> job_array;
    private String id;

    public job_adapter(Context c , ArrayList<job> p, String id1)
    {
        context = c;
        job_array = p;
        this.id=id1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.jobcardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if(job_array.get(position)!=null)
        {
            holder.job.setText(String.valueOf(job_array.get(position).getJob_id()) + " "+ job_array.get(position).getProfile()); //
            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent job_profile=new Intent(context, job_profile.class);
                    job_profile.putExtra("MyClassID",id);
                    job_profile.putExtra("PrevActivity","recycleview");
                    //job_profile.putExtra("PrevActivity","job_list");
                    context.startActivity(job_profile);
                /*Intent intent = new Intent(context, Apply_For_Jobs.class);
                intent.putExtra("job_profile", job_array.get(position));
                context.startActivity(intent);*/
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(job_array==null)
        {
            return 0;
        }
        else return job_array.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView job;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            job = itemView.findViewById(R.id.job);
            parentlayout = itemView.findViewById(R.id.jobcard);
        }
    }
}