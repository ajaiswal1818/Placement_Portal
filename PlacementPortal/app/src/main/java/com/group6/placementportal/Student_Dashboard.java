package com.group6.placementportal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notices;

import java.util.ArrayList;
import java.util.List;

public class Student_Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Student_Notices";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    //    private String mPostKey;
    private NoticeAdapter mAdapter;

    private TextView Topic;
    private TextView Content;

    private String strTopic;
    private String strContent;

    private RecyclerView mCommentsRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student__notices);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        fetchNotices();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // get Post key
//        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
//        if (mPostKey == null) {
//            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
//        }

        //Initialize Database

        mCommentsReference = FirebaseDatabase.getInstance().getReference()
                .child("Notices");

        // Initialize Views
        Topic = findViewById(R.id.Notice_Topic);
        Content = findViewById(R.id.Notice_Content);

        mCommentsRecycler = findViewById(R.id.recyclerNotices);

//        ValueEventListener postListener = new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Notices notice;
//
//                strTopic = "Nothing Here;";
//                strContent = "Nothing Here";
//
//                if (dataSnapshot.child("Notices").child("Topic").getValue() != null)
//                    strTopic = dataSnapshot.child("Notices").child("Topic").getValue().toString();
//                if (dataSnapshot.child("Notices").child("Content").getValue() != null)
//                    strContent = dataSnapshot.child("Notices").child("Content").getValue().toString();
//
//                notice = new Notices(strTopic, strContent);
//                // [START_EXCLUDE]
//                Topic.setText(strTopic);
//                Content.setText(strContent);
//
//                // [END_EXCLUDE]
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
//                Toast.makeText(Student_Dashboard.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        };
//        mCommentsReference.addValueEventListener(postListener);
//        // [END post_value_event_listener]
//
////         Keep copy of post listener so we can remove it when app stops
//        mPostListener = postListener;

        // Listen for comments
        mAdapter = new NoticeAdapter(this, mCommentsReference);
        mCommentsRecycler.setAdapter(mAdapter);
        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(this));

    }


    private static class NoticeViewHolder extends RecyclerView.ViewHolder {

        private TextView topicView;
        private TextView contentView;

        private NoticeViewHolder(View itemView) {
            super(itemView);

            topicView = itemView.findViewById(R.id.Notice_Topic);
            contentView = itemView.findViewById(R.id.Notice_Content);
        }
    }

    private static class NoticeAdapter extends RecyclerView.Adapter<NoticeViewHolder> {

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> mNoticeIds = new ArrayList<>();
        private List<Notices> mNotices = new ArrayList<>();

        private String Topic;
        private String Content;

        public NoticeAdapter(final Context context, DatabaseReference ref) {
            mContext = context;
            mDatabaseReference = ref;

            // Create child event listener
            // [START child_event_listener_recycler]
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                    // A new notices has been added, add it to the displayed list

                    Notices notices ;

                    Topic="Nothing Here;";
                    Content="Nothing Here";

                    if(dataSnapshot.child("Notices").child("Topic").getValue()!=null)Topic = dataSnapshot.child("Notices").child("Topic").getValue().toString();
                    if(dataSnapshot.child("Notices").child("Content").getValue()!=null)Content = dataSnapshot.child("Notices").child("Content").getValue().toString();

                    notices = new Notices(Topic,Content);

                    // [START_EXCLUDE]
                    // Update RecyclerView
                    mNoticeIds.add(dataSnapshot.getKey());
                    mNotices.add(notices);
                    notifyItemInserted(mNotices.size() - 1);
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                    // A notices has changed, use the key to determine if we are displaying this
                    // notices and if so displayed the changed notices.
                    Notices newNotice ;

                    Topic="Nothing Here;";
                    Content="Nothing Here";

                    if(dataSnapshot.child("Notices").child("Topic").getValue()!=null)Topic = dataSnapshot.child("Notices").child("Topic").getValue().toString();
                    if(dataSnapshot.child("Notices").child("Content").getValue()!=null)Content = dataSnapshot.child("Notices").child("Content").getValue().toString();

                    newNotice  = new Notices(Topic,Content);
                    String noticesKey = dataSnapshot.getKey();

                    // [START_EXCLUDE]
                    int noticesIndex = mNoticeIds.indexOf(noticesKey);
                    if (noticesIndex > -1) {
                        // Replace with the new data
                        mNotices.set(noticesIndex, newNotice);

                        // Update the RecyclerView
                        notifyItemChanged(noticesIndex);
                    } else {
                        Log.w(TAG, "onChildChanged:unknown_child:" + noticesKey);
                    }
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                    // A notices has changed, use the key to determine if we are displaying this
                    // notices and if so remove it.
                    String noticesKey = dataSnapshot.getKey();

                    // [START_EXCLUDE]
                    int noticesIndex = mNoticeIds.indexOf(noticesKey);
                    if (noticesIndex > -1) {
                        // Remove data from the list
                        mNoticeIds.remove(noticesIndex);
                        mNotices.remove(noticesIndex);

                        // Update the RecyclerView
                        notifyItemRemoved(noticesIndex);
                    } else {
                        Log.w(TAG, "onChildRemoved:unknown_child:" + noticesKey);
                    }
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                    // A notices has changed position, use the key to determine if we are
                    // displaying this notices and if so move it.
                    Notices notices ;

                    Topic="Nothing Here;";
                    Content="Nothing Here";

                    if(dataSnapshot.child("Notices").child("Topic").getValue()!=null)Topic = dataSnapshot.child("Notices").child("Topic").getValue().toString();
                    if(dataSnapshot.child("Notices").child("Content").getValue()!=null)Content = dataSnapshot.child("Notices").child("Content").getValue().toString();

                    notices = new Notices(Topic,Content);
                    String noticesKey = dataSnapshot.getKey();

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "postNotices:onCancelled", databaseError.toException());
                    Toast.makeText(mContext, "Failed to load noticess.",
                            Toast.LENGTH_SHORT).show();
                }
            };
            ref.addChildEventListener(childEventListener);
            // [END child_event_listener_recycler]

            // Store reference to listener so it can be removed on app stop
            mChildEventListener = childEventListener;
        }

        @Override
        public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.content_student__notices, parent, false);
            return new NoticeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
            Notices notices = mNotices.get(position);
            holder.topicView.setText(notices.getTopic());
            holder.contentView.setText(notices.getContent());
        }

        @Override
        public int getItemCount() {
            return mNotices.size();
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dash) {
            Intent i = new Intent(getApplicationContext(), Student_Dashboard.class);
            startActivity(i);

        } else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_prefr) {

        } else if (id == R.id.nav_company) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_edit_profile) {
            Intent i = new Intent(getApplicationContext(), Student_Profile.class);
            startActivity(i);

        } else if (id == R.id.nav_change_pass) {
            Intent i = new Intent(getApplicationContext(), Student_ChangePass.class);
            startActivity(i);

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
