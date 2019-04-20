package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CardArrayAdapter extends RecyclerView.Adapter<CardArrayAdapter.MyViewHolder> {

    Context context;
    ArrayList<Card> card_array;
    private String id;

    public CardArrayAdapter(Context c , ArrayList<Card> p)
    {
        context = c;
        card_array = p;
        //this.id=id1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if(card_array.get(position)!=null)
        {
            holder.line1.setText("Title : " + String.valueOf(card_array.get(position).getLine1()));
            holder.line2.setText("Description" + String.valueOf(card_array.get(position).getLine2()));
            holder.line3.setText("Click here to download");

            final String str=String.valueOf(card_array.get(position).getLine3());
            if(!String.valueOf(card_array.get(position).getLine3()).equals(""))
            {
                holder.line3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(str));
                        context.startActivity(intent);
                    }
                });
            }
            else
            {
                holder.line3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"No file available",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

    @Override
    public int getItemCount() {
        if(card_array==null)
        {
            return 0;
        }
        else return card_array.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView line1;
        TextView line2;
        TextView line3;

        CardView parentlayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            line1 = itemView.findViewById(R.id.notice_title);
            line2 = itemView.findViewById(R.id.notice_description);
            line3 = itemView.findViewById(R.id.notice_pdf);
            parentlayout = itemView.findViewById(R.id.card_company_notice);
            line3.setPaintFlags(line3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
    }
}