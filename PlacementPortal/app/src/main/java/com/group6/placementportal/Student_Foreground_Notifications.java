package com.group6.placementportal;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notifications;

public class Student_Foreground_Notifications extends IntentService {

    private DatabaseReference reference;
    private Notifications my_Notif;
    private int Notifications_ID = 234;
    int flag=0;
    public Student_Foreground_Notifications() {
        super("Foreground Notifications");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("foreground", "notifs showing");
        reference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        super.onStartCommand(intent,flags,startID);
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String user;
        if(intent == null){
            user = "vakul170101076";
            Log.d("User", "Currently Not Logged In ");
        }
        else {
            user = intent.getStringExtra("user");
            flag = intent.getIntExtra("flag",0);
            Log.d("User", "Currently Logged In ");
        }
            reference.child("Student").child(user).child("List_of_Notification_IDs").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    String Notif = dataSnapshot.getValue(String.class);
                    if(Notif!=null && !Notif.equals("")) {
                        String[] split_IDs = Notif.split("\\,");

                        Log.d("User", "Currently Logged In ");

                        final String index_Notif = split_IDs[split_IDs.length - 1];
                        reference.child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                my_Notif = dataSnapshot1.child(index_Notif).getValue(Notifications.class);
                                if(flag==0){
                                    flag=1;
                                }
                                else {
                                    showNotif(my_Notif);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }


    public void showNotif(Notifications notify){
        String textTitle = notify.getSubject();
        String textContent = notify.getDescription();

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            String CHANNEL_ID = "personal_notifications";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "personal_notifications")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        notification.notify(Notifications_ID,builder.build());
    }
}