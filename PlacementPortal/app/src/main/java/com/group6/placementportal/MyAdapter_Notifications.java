package com.group6.placementportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.Notifications;

import java.util.ArrayList;

public class MyAdapter_Notifications extends RecyclerView.Adapter<MyAdapter_Notifications.MyViewHolder> {

    Context context;
    ArrayList<Notifications> profiles;

    public MyAdapter_Notifications(Context c , ArrayList<Notifications> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_notifications,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.notice_topic.setText(profiles.get(position).getSubject());
        holder.notice_content.setText(profiles.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView notice_topic,notice_content;
        public MyViewHolder(View itemView) {
            super(itemView);
            notice_topic = (TextView) itemView.findViewById(R.id.txt_Subject);
            notice_content = (TextView) itemView.findViewById(R.id.txt_Description);
        }
    }
}