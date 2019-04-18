package com.group6.placementportal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class admin_checkout_the_portal extends AppCompatActivity {
    public DatabaseReference reference,reference2,reference1,reference3;
    public String student,list;
    public int notifications_count;

    public void set_student_list(String l){
        Log.w("list2 lll","list 2 is "+l);
        reference2=FirebaseDatabase.getInstance().getReference().child("Student").child(student).child("List_of_Notification_IDs");

        reference2.setValue(l);
    }

    public void get_list(){
        reference2=FirebaseDatabase.getInstance().getReference().child("Student").child(student).child("List_of_Notification_IDs");

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list= (String) dataSnapshot.getValue();


                Log.w("list initially",list);
                if(!list.isEmpty()){
                    list=list +","+Integer.toString(notifications_count+1);

                }
                else{
                    list=Integer.toString(notifications_count+1);

                }
                Log.w("list updated",list);
                set_student_list(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        Log.w("list1","list1 is "+list);


    }

    public void send_notification(int n,String job_id){
        Log.w("n........",Integer.toString(n+1));
        reference1=FirebaseDatabase.getInstance().getReference().child("Notifications");
        reference1.child(Integer.toString(n+1)).child("Description").setValue("You have been shorlisted for the job with job_id "+job_id+". Congratulations!!");


        reference1.child(Integer.toString(n+1)).child("Subject").setValue("Selected!!! ");
        reference1.child(Integer.toString(n+1)).child("Read").setValue("False");
        reference1.child(Integer.toString(n+1)).child("notification_ID").setValue(Integer.toString(n+1));
        get_list();

    }

    public void update_count(final String final_id){
        reference2=FirebaseDatabase.getInstance().getReference().child("Notifications");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifications_count= (int) dataSnapshot.getChildrenCount();
                send_notification(notifications_count,final_id);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    public void the_checkout(View v){
        DatabaseReference reference;
        reference=FirebaseDatabase.getInstance().getReference().child("checked_out");
        reference.setValue("Yes");
        reference= FirebaseDatabase.getInstance().getReference().child("Student");

        




        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                   if(dataSnapshot1.child("has_given_preferences").exists() && dataSnapshot1.child("preferences").exists() && dataSnapshot1.child("selected_for_job_ids").exists()){
                       if(dataSnapshot1.child("has_given_preferences").getValue().equals("Completed")){
                           String sorted_preferences= (String) dataSnapshot1.child("preferences").getValue();
                           String companies_selected=(String) dataSnapshot1.child("selected_for_job_ids").getValue();
                           String[] preferences=sorted_preferences.split(",");
                           String[] companies_final=companies_selected.split(",");

                           int counter;
                           int c=0;
                           String final_id="";
                           for(counter=0;counter<preferences.length;counter++){
                               for(c=0;c<companies_final.length;c++){
                                   if(companies_final[c].equals(preferences[counter])){
                                       final_id=companies_final[c];
                                       student=dataSnapshot1.getKey();
                                       update_count(final_id);

                                       break;
                                   }
                               }

                               if(  c<companies_final.length && counter<preferences.length &&  companies_final[c].equals(preferences[counter])){
                                   break;
                               }
                           }





                       }
                   }

               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_checkout_the_portal);

    }

}
