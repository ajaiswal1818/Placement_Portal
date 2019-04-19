package com.group6.placementportal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.group6.placementportal.DatabasePackage.Jobs;
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.job;

import java.util.ArrayList;

public class approve_company_adapter extends RecyclerView.Adapter<approve_company_adapter.MyViewHolder> {

    Context context;
    ArrayList<company> company_array;
    private String id;
    private DatabaseReference reference;
    private String max_id;
    public approve_company_adapter(Context c , ArrayList<company> p)
    {
        context = c;
        company_array = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.approve_company_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if(company_array.get(position)!=null)
        {

            reference = FirebaseDatabase.getInstance().getReference().child("Company");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists() && dataSnapshot.child("Max_entry").exists())
                    {
                        max_id = dataSnapshot.child("Max_entry").getValue().toString();
                    }
                    else
                    {
                        Toast.makeText(context, "Oops ... something is wrong", Toast.LENGTH_SHORT).show();
                        max_id="-1";
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(context, "Oops ... something is wrong", Toast.LENGTH_SHORT).show();
                    max_id = "-1";
                }
            });

            holder.company_details.setText("Company id : " + company_array.get(position).getCompany_id() + "\n"+ "Company Name : "+company_array.get(position).getCompany_name() + "\n" + "Contact : " + company_array.get(position).getContact_no() + "\n" + "Email : "+ company_array.get(position).getEmail_address() + "\n"+ "HeadOffice : " + company_array.get(position).getHeadoffice()); //
            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Long.parseLong(max_id) < 0)
                    {
                        Toast.makeText(context, "Oops ... something is wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                    mBuilder.setTitle("Company Approval");
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reference.child(company_array.get(position).getCompany_id()).child("approved").setValue("Approved");
                            //reference.child("Max_entry").setValue(String.valueOf(Long.parseLong(max_id)+1));
                            dialog.dismiss();
                        }
                    });
                    mBuilder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reference.child(company_array.get(position).getCompany_id()).child("approved").setValue("Rejected");
                            dialog.dismiss();
                        }
                    });
                    mBuilder.setNeutralButton("Ask later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(company_array==null)
        {
            return 0;
        }
        else return company_array.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView company_details;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            company_details = itemView.findViewById(R.id.company_details);
            parentlayout = itemView.findViewById(R.id.approve_company_card);
        }
    }
}