package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class Company_Slots_Admin_Adapter extends RecyclerView.Adapter<Company_Slots_Admin_Adapter.MyviewHolder >{
    List<Company_Slots_customclass> my_list;
    Context context;

    public Company_Slots_Admin_Adapter(List<Company_Slots_customclass> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_slot_customlist,viewGroup,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        final Company_Slots_customclass new_class = my_list.get(i);
        myviewHolder.name.setText(new_class.getCompanyName());
        myviewHolder.company_id.setText(new_class.getCompanyId());
        myviewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,Company_Slots_Admin_second.class);
                intent.putExtra("id",new_class.getCompanyId());
                intent.putExtra("name",new_class.getCompanyName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        TextView name,company_id;
        RelativeLayout relative;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            company_id=itemView.findViewById(R.id.id);
            relative=itemView.findViewById(R.id.relative);
        }
    }
}