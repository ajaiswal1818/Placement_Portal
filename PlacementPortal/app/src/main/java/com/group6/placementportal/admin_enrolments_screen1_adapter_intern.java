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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Interns;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;

import java.util.ArrayList;

public class admin_enrolments_screen1_adapter_intern extends RecyclerView.Adapter<admin_enrolments_screen1_adapter_intern.MyViewHolder> {

    Context context;
    ArrayList<Interns> profiles;
    private static DatabaseReference reference;
    ArrayList <String> cv;
    company com;
    public admin_enrolments_screen1_adapter_intern(Context c , ArrayList<Interns> p)
    {
        context = c;
        profiles = p;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_enrolments_job, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // holder.company_name.setText(profiles.get(position).getCompany_name());
        holder.job_profile.setText(profiles.get(position).getProfile());
        holder.job_id.setText(profiles.get(position).getIntern_id());
        holder.company.setText(profiles.get(position).getCompany_name());
        // holder.job_location.setText(profiles.get(position).getLocation());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String job_id=profiles.get(position).getIntern_id();
                //intent.putExtra("job_profile", profiles.get(position));
                // final String job_id=Integer.toString(position);

                reference = FirebaseDatabase.getInstance().getReference().child("Interns").child(profiles.get(position).getIntern_id()).child("Applied Students");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> slist=new ArrayList<>();
                        cv=new ArrayList<>();
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            String s_id=dataSnapshot1.getKey();
                            slist.add(s_id);
                            cv.add((String) dataSnapshot1.child("CV").getValue());

                        }
                        Intent intent = new Intent(context, company_enrollments.class);
                        intent.putExtra("MyClass",slist);
                        String job_id=profiles.get(position).getIntern_id();

                        Log.w("going to final enrollments as intern",job_id);
                        intent.putExtra("Job",job_id);
                     //   cv=(String) dataSnapshot.child("CV").getValue();
                        intent.putExtra("cv",cv);
                        intent.putExtra("is_job",false);
                        intent.putExtra("Screen",1);
                        intent.putExtra("company",com);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView company_name,job_profile,job_id,company;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            //  company_name = itemView.findViewById(R.id.txt_company_name);
            job_profile = itemView.findViewById(R.id.txt_job_profile);
            //job_location = itemView.findViewById(R.id.txt_job_location);
            job_id=itemView.findViewById(R.id.txt_job_id);
            company=itemView.findViewById(R.id.txt_company_names);


            parentlayout = itemView.findViewById(R.id.cardview_enrolments_job);
        }
    }
}