package com.group6.placementportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group6.placementportal.DatabasePackage.Notifications_Admin;

import java.util.ArrayList;

public class notification_layout extends RecyclerView.Adapter<notification_layout.notificationViewHolder> {

    ArrayList<Notifications_Admin> ncard;
    Context context;

    public static class notificationViewHolder extends RecyclerView.ViewHolder {

        public TextView subject,description,pdflink;

        public notificationViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            description = itemView.findViewById(R.id.description);
            pdflink = itemView.findViewById(R.id.pdflink);
        }
    }

    public notification_layout(Context c, ArrayList<Notifications_Admin> p) {
        context = c;
        ncard = p;
    }

    @Override
    public notificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_card,viewGroup,false);
        notificationViewHolder evh = new notificationViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull notificationViewHolder notificationViewHolder, int i) {
        Notifications_Admin currentItem = ncard.get(i);

        notificationViewHolder.subject.setText(currentItem.getSubject());
        notificationViewHolder.description.setText(currentItem.getDescription());
        notificationViewHolder.pdflink.setText(currentItem.getPdflink());
    }

    @Override
    public int getItemCount() {
        return ncard.size();
    }



}