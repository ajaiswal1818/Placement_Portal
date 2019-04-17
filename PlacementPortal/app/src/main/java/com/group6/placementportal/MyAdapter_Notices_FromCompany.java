package com.group6.placementportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.Notices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter_Notices_FromCompany extends RecyclerView.Adapter<MyAdapter_Notices_FromCompany.MyViewHolder> {

    Context context;
    ArrayList<Notices> profiles;

    private OnItemClickListener pressed;

    public interface OnItemClickListener{
        void rejectnotice(int position);

        void approvenotice(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        pressed = listener;
    }



    public MyAdapter_Notices_FromCompany(Context c , ArrayList<Notices> p)
    {
        context = c;
        profiles = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notice_fromcompany,parent,false),pressed);
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
        Button approve_notice;
        Button reject_notice;

        public MyViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            notice_image = itemView.findViewById(R.id.Notice_Image);
            notice_topic =itemView.findViewById(R.id.Notice_Topic);
            notice_content =itemView.findViewById(R.id.Notice_Content);

            approve_notice = itemView.findViewById(R.id.btn_approve);
            reject_notice = itemView.findViewById(R.id.btn_reject);

            approve_notice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if( position != RecyclerView.NO_POSITION){
                            listener.approvenotice(position);
                        }
                    }
                }
            });

            reject_notice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if( position != RecyclerView.NO_POSITION){
                            listener.rejectnotice(position);
                        }
                    }
                }
            });
        }
    }
}