package com.group6.placementportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.group6.placementportal.DatabasePackage.Notices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter_Notices extends RecyclerView.Adapter<MyAdapter_Notices.MyViewHolder> {

    Context context;
    ArrayList<Notices> profiles;

    public MyAdapter_Notices(Context c , ArrayList<Notices> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notice,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(profiles.get(position).getImageURL()).into(holder.notice_image);
        holder.notice_topic.setText(profiles.get(position).getTopic());
        holder.notice_content.setText(profiles.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView notice_topic,notice_content;
        ImageView notice_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            notice_image = itemView.findViewById(R.id.Notice_Image);
            notice_topic =itemView.findViewById(R.id.Notice_Topic);
            notice_content =itemView.findViewById(R.id.Notice_Content);
        }
    }
}