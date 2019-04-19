package com.group6.placementportal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class single_dialog_companyenrollments_0 extends DialogFragment {
    private Task<Void> reference;
    public single_dialog_companyenrollments_0(){

    }
    public String student;
    public String job_id;
    public int which_items;
    public CharSequence[] items;
    public int which_screen;
    public DatabaseReference reference1;
    public DatabaseReference reference2;
    public int notifications_count;
    public String list="";
    public boolean res=true;
    public Activity f;
    public single_dialog_companyenrollments_0(String s_id,String job,int pos,int screen){
        student=s_id;
        job_id=job;
        which_items=pos;
        which_screen=screen;

        if(which_screen==0){
            if(which_items==0){
                items=new CharSequence[]{"Promote to Technical Round","Reject"};
            }
            else if(which_items==1){
                items=new CharSequence[]{"Promote to Shortlisted","Reject"};

            }
            else if(which_items==2){
                items=new CharSequence[]{};

            }
            else if(which_items==3){
                items=new CharSequence[]{};

            }
        }
        else if(which_screen==1){
            if(which_items==0){
                items=new CharSequence[]{"Approve","Reject"};
            }
            else if(which_items==1){
                items=new CharSequence[]{"Approve changes( A notification will be sent)","Reject"};

            }
            else if(which_items==2){
                items=new CharSequence[]{"Approve changes(A notification will be sent)","Reject"};

            }
            else if(which_items==3){
                items=new CharSequence[]{};

            }
        }


    }

    public void add_selection(String jobs){
        DatabaseReference reference4=FirebaseDatabase.getInstance().getReference().child("Student").child(student).child("selected_for_job_ids");
        reference4.setValue(jobs);
    }
    public void send_notification(int n,int stage){
        Log.w("n........",Integer.toString(n+1));
        if(stage==0){
            reference1.child(Integer.toString(n+1)).child("Description").setValue("Your application for job with job_id: "+job_id+" has been accepted. Keep yourself update with the events of this job");
            reference1.child(Integer.toString(n+1)).child("Subject").setValue("Application Approved");

        }
        else if(stage==1){
            reference1.child(Integer.toString(n+1)).child("Description").setValue("You have been selected for Technical Round of job_id "+job_id+". Keep yourself update with the events of this job");
            reference1.child(Integer.toString(n+1)).child("Subject").setValue("Tech Round Updates");

        }

        else if(stage==2){
            reference1.child(Integer.toString(n+1)).child("Description").setValue("You have been shortlisted for job with job_id "+job_id+". Congratulations :)");
            reference1.child(Integer.toString(n+1)).child("Subject").setValue("Selection!");

        }
        else if(stage==3){
            reference1.child(Integer.toString(n+1)).child("Description").setValue("Your application has been rejected for job with job_id "+job_id+". This is for your information");
            reference1.child(Integer.toString(n+1)).child("Subject").setValue("Rejected");

        }
        reference1.child(Integer.toString(n+1)).child("Read").setValue("False");
        reference1.child(Integer.toString(n+1)).child("notification_ID").setValue(Integer.toString(n+1));
        get_list();

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

    public void set_student_list(String l){
        Log.w("list2 lll","list 2 is "+l);
        reference2=FirebaseDatabase.getInstance().getReference().child("Student").child(student).child("List_of_Notification_IDs");

        reference2.setValue(l);
    }

    public void mark_notified(){
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student);
        ref.child("is_notified").setValue("Yes");
    }
    public boolean check_if_already_notified(final int count, final int which, final boolean to_approve){

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("is_notified")){
                    res=true;
                    if(f!=null){
                        Toast.makeText(f, "No changes can be made now", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Log.w("f is","Null");
                    }


                }
                else{
                    mark_notified();
                    res=false;
                    if(to_approve){
                        send_notification(count,which);
                        Toast.makeText(f, "Approving the application request. Notification sent to the student", Toast.LENGTH_SHORT).show();
                        reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");

                    }
                    else{
                        Toast.makeText(f, "Rejecting the application request", Toast.LENGTH_SHORT).show();
                        reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Rejected");


                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return res;
    }

    public int option_number=100;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            f =(Activity) context;
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Log.d("yessssssssssss",student);
        builder.setTitle("").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                option_number=which;

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

              if(which_screen==0) {


                  if (which_items == 0) {
                      if (option_number == 0) {
                          Toast.makeText(getActivity(), "Shifting to Selected for Technical Round", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("1");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("No");

                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("is_notified").removeValue();


                      }

                      else if (option_number == 1) {
                          Toast.makeText(getActivity(), "Rejected", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("3");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("No");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("is_notified").removeValue();

                      }


                  }
                  if (which_items == 1) {
                       if (option_number == 0) {
                          Toast.makeText(getActivity(), "Shifting to Shortlisted", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("2");
                           reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("No");
                           reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("is_notified").removeValue();


                       } else if (option_number == 1) {
                          Toast.makeText(getActivity(), "Rejected", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("3");
                           reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("No");
                           reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("is_notified").removeValue();

                       }


                  }

                  if (which_items == 2) {
                     /* if (option_number == 0) {
                          Toast.makeText(getActivity(), "Shifting to Applied", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("0");

                      } else if (option_number == 1) {
                          Toast.makeText(getActivity(), "Shifting to Selected for Techincal Round", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("1");

                      } else if (option_number == 2) {
                          Toast.makeText(getActivity(), "Rejected", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("3");

                      }*/


                  }

                  if (which_items == 3) {
                      if (option_number == 0) {
                         Toast. makeText(getActivity(), "Shifting to Applied", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("0");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");


                      } else if (option_number == 1) {
                          Toast.makeText(getActivity(), "Shifting to Selected for Technical Round", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("1");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");

                      } else if (option_number == 2) {
                          Toast.makeText(getActivity(), "Shifting to Shortlisted", Toast.LENGTH_SHORT).show();
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Status").setValue("2");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");

                      }


                  }
              }

              else if(which_screen==1){
                  if(which_items==0){
                      if(option_number==0){
                          //approve
                       //   reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");
                          reference1=FirebaseDatabase.getInstance().getReference().child("Notifications");
                          reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  notifications_count= (int) dataSnapshot.getChildrenCount();
                                  Log.w("notifications",Integer.toString(notifications_count));
                                  check_if_already_notified(notifications_count,which_items,true);


                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });


                      }
                      else if(option_number==1){
                          //reject --> remove student and notify him
                         // Toast.makeText(getActivity(), "Rejecting the application ", Toast.LENGTH_SHORT).show();
                        //  reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Rejected");
                          check_if_already_notified(notifications_count,which_items,false);

                      }
                  }

                  else if(which_items==1 ){
                      if(option_number==0){
                          // notify
                          reference1=FirebaseDatabase.getInstance().getReference().child("Notifications");
                        //  reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");

                          reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  notifications_count= (int) dataSnapshot.getChildrenCount();
                                  Log.w("notifications",Integer.toString(notifications_count));

                                  check_if_already_notified(notifications_count,which_items,true);

                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });
                          Log.w("notifications before adding",Integer.toString(notifications_count));




                         // reference2.setValue(list);

                      }
                      else if(option_number==1){
                      //    reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Rejected");
                          check_if_already_notified(notifications_count,which_items,false);

                      }
                  }
                  else if(which_items==2){
                      if(option_number==0){
                          //notify
                          /*reference1=FirebaseDatabase.getInstance().getReference().child("Notifications");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");

                          reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  notifications_count= (int) dataSnapshot.getChildrenCount();
                                  Log.w("notifications",Integer.toString(notifications_count));
                                  send_notification(notifications_count,which_items);



                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });
                          Log.w("notifications before adding",Integer.toString(notifications_count));*/

                          // add selection into student
                          DatabaseReference reference3=FirebaseDatabase.getInstance().getReference().child("Student").child(student);
                          reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  String current_job_list= (String) dataSnapshot.child("selected_for_job_ids").getValue();

                                  if(current_job_list.isEmpty()){
                                      Log.w("yea","curr list"+current_job_list);
                                      current_job_list=job_id;
                                      add_selection(current_job_list);

                                  }
                                  else{
                                      Log.w("yea","curr list"+current_job_list);
                                      current_job_list=current_job_list+","+job_id;
                                      add_selection(current_job_list);
                                  }

                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });




                         // reference2.setValue(list);

                      }
                      else if(option_number==1){
                         // reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Rejected");

                      }
                  }
                  else if(which_items==3){
                      if(option_number==0){
                          //notify
                         /* reference1=FirebaseDatabase.getInstance().getReference().child("Notifications");
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Yes");

                          reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  notifications_count= (int) dataSnapshot.getChildrenCount();

                                  Log.w("notifications",Integer.toString(notifications_count));
                                  if(!check_if_already_notified()){
                                      send_notification(notifications_count,which_items);

                                  }



                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });*/
                         // Log.w("notifications before adding",Integer.toString(notifications_count));




                         // reference2.setValue(list);

                      }
                      else if(option_number==1){
                          reference = FirebaseDatabase.getInstance().getReference().child("Jobs").child(job_id).child("Applied Students").child(student).child("Approval").setValue("Rejected");

                      }
                  }
              }


            }

        });



        return builder.create();
       // return super.onCreateDialog(savedInstanceState);
    }


}
