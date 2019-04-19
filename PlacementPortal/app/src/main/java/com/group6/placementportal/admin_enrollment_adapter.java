package com.group6.placementportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.group6.placementportal.DatabasePackage.Student;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class admin_enrollment_adapter extends RecyclerView.Adapter<admin_enrollment_adapter.MyViewHolder> {

    Activity context;
    ArrayList<Student> students;
    String job;
    int which;
    public DatabaseReference reference;
    public void set_color(final int pos, final CardView ref){
        reference = FirebaseDatabase.getInstance().getReference().child("Jobs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(job).child("Applied Students").child(students.get(pos).getWebmailID()).child("Approval").getValue().equals("Yes")){
                    //    int colorId = 7;
                    //  int color = holder.parentlayout.getContext().getResources().getColor(colorId);

                    ref.setCardBackgroundColor(0xff2ecc71);
                    Log.w("Approval in set color","Yess");
                }
                else if(dataSnapshot.child(job).child("Applied Students").child(students.get(pos).getWebmailID()).child("Approval").getValue().equals("Rejected")){

                    ref.setCardBackgroundColor(0xffff0000);
                    Log.w("Approval in set color","Reject");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public admin_enrollment_adapter(Activity c , ArrayList<Student> p,String job_id,int pos)
    {
        context = c;
        students = p;
        job=job_id;
        which=pos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.enrollment_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        /*holder.company_name.setText(students.get(position).getCompany_name());
        holder.job_profile.setText(students.get(position).getProfile());
        holder.job_location.setText(students.get(position).getLocation());*/
        holder.student_name.setText(students.get(position).getFullName());
        reference = FirebaseDatabase.getInstance().getReference().child("Jobs");
        Log.w("reference adpater",reference.getRef().toString());
        Log.w("calling","calling set color");
        set_color(position,holder.parentlayout);

        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(context, Apply_For_Student.class);
                intent.putExtra("job_profile", students.get(position));
                context.startActivity(intent); */
                // Log.d("Messgae","tag");

                single_dialog_companyenrollments_0 menu=new single_dialog_companyenrollments_0(students.get(position).getWebmailID(),job,which,1);
                //FragmentManager f=context.getPackageManager();
                // menu.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                FragmentActivity f=(FragmentActivity) context;
                menu.show(f.getSupportFragmentManager(),"menu");


                //displaying the popup

            }

        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        public View buttonViewOption;
        // public View parentlayout;
        /* TextView company_name,job_profile,job_location;*/
        CardView parentlayout;
        TextView student_name;
        public MyViewHolder(View itemView) {
            super(itemView);
           /* company_name = itemView.findViewById(R.id.txt_company_name);
            job_profile = itemView.findViewById(R.id.txt_job_profile);
            job_location = itemView.findViewById(R.id.txt_job_location);*/
            parentlayout = itemView.findViewById(R.id.cardview_enrollments);
            student_name=itemView.findViewById(R.id.txt_student_name);


        }
    }
}