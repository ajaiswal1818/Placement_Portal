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
import com.group6.placementportal.DatabasePackage.company;
import com.group6.placementportal.DatabasePackage.intern;

import java.util.ArrayList;

public class intern_adapter extends RecyclerView.Adapter<intern_adapter.MyViewHolder> {

    Context context;
    ArrayList<intern> intern_array;
    private String id;

    public intern_adapter(Context c , ArrayList<intern> p, String id1)
    {
        context = c;
        intern_array = p;
        this.id=id1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.interncardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if(intern_array.get(position)!=null)
        {
            holder.intern.setText(String.valueOf(intern_array.get(position).getIntern_id()) + " "+ intern_array.get(position).getProfile()); //
            holder.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intern_profile=new Intent(context, intern_profile.class);
                    intern_profile.putExtra("MyClassID",id + "_"+ intern_array.get(position).getIntern_id());
                    intern_profile.putExtra("PrevActivity","recycleview");
                    //intern_profile.putExtra("PrevActivity","intern_list");
                    context.startActivity(intern_profile);
                /*Intent intent = new Intent(context, Apply_For_Interns.class);
                intent.putExtra("intern_profile", intern_array.get(position));
                context.startActivity(intent);*/
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(intern_array==null)
        {
            return 0;
        }
        else return intern_array.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView intern;
        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            intern = itemView.findViewById(R.id.intern);
            parentlayout = itemView.findViewById(R.id.interncard);
        }
    }
}