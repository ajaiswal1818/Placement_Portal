package com.group6.placementportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Notices;
import com.group6.placementportal.DatabasePackage.company;

import java.util.ArrayList;
import java.util.List;

public class Company_Application_Slots extends AppCompatActivity {

    private String id;
    private EditText t1;
    private Spinner s1;
    private Spinner s2;
    private Spinner s3;
    private Button load;
    ArrayList[][] Interview_Slots= new ArrayList[3][8];
    ArrayList[][] online_Slots= new ArrayList[15][8];
    ArrayList[][] technical_Slots= new ArrayList[15][8];
    ArrayList[][] workshop_Slots= new ArrayList[15][8];

    List<String> Type_array = new ArrayList<String>();
    List<String> date1_array = new ArrayList<String>();
    List<String> date2_array = new ArrayList<String>();

    String date_allocated;
    String time_allocated;
    String type_allocated;

    String date_selected;
    String time_selected;
    String type_selected;
    String place_allocated;



    List<String> slots= new ArrayList<String>() ;
    int type_select;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__application__slots);

        this.id=(String) getIntent().getStringExtra("id");
        t1=(EditText)findViewById(R.id.Round);
        s1=(Spinner) findViewById(R.id.type);
        s2=(Spinner) findViewById(R.id.date);
        s3=(Spinner) findViewById(R.id.slots);
        load=(Button) findViewById(R.id.Load);


        Type_array.add(0,"Choose Type");
        Type_array.add("Technical_Round");
        Type_array.add("Workshop");
        Type_array.add("Online_Round");
        Type_array.add("Interview");

        String[] arraySpinner = new String[] {
                "Technical_Round","WorkShop","Online_Round","Interview"
        };

        String[] date_1 = new String[] {
                "16/11/2019","17/11/2019","18/11/2019","19/11/2019","20/11/2019","21/11/2019","22/11/2019","23/11/2019","24/11/2019","25/11/2019","26/11/2019","27/11/2019","28/11/2019","29/11/2019","30/11/2019"
        };
        String[] date_2 = new String[] {
                "1/12/2019","2/12/2019","3/12/2019"
        };

        final String[] time = new String[] {
                "2:00-5:00","5:00-8:00","8:00-11:00","11:00-14:00","14:00-17:00","17:00-20:00","20:00-23:00","23:00-2:00"
        };

        for (int i=0;i<15;i++){
            for (int j=0;j<8;j++){
                online_Slots[i][j]= new ArrayList();
                technical_Slots[i][j]= new ArrayList();
                workshop_Slots[i][j]= new ArrayList();
            }
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<8;j++){
                Interview_Slots[i][j]= new ArrayList();
            }
        }
        database = FirebaseDatabase.getInstance().getReference().child("Company");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    if (dataSnapshot1.hasChild("Application_Slots")){
                        DataSnapshot Application = dataSnapshot1.child("Application_Slots");
                        for (DataSnapshot dataSnapshot2: Application.getChildren()){
                            company_details new_company_data= new company_details();
                            new_company_data= Application.child(dataSnapshot2.getKey()).getValue(company_details.class);
                            type_allocated=new_company_data.getType();
                            date_allocated=new_company_data.getDate();
                            time_allocated=new_company_data.getSlot();

                            int date_no=convert_date(date_allocated)-16;
                            int time_no=convert_slot(time_allocated);

                            if(type_allocated.equals("Technical_Round"))technical_Slots[date_no][time_no].add(1);
                            else if (type_allocated.equals("Workshop"))workshop_Slots[date_no][time_no].add(1);
                            else if (type_allocated.equals("Online_Round"))online_Slots[date_no][time_no].add(1);
                            else if (type_allocated.equals("Interview"))Interview_Slots[date_no][time_no].add(1);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Type_array);
        final ArrayAdapter<String> date1_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, date_1);
        final ArrayAdapter<String> date2_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, date_2);
        final ArrayAdapter<String> time_box = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, time);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date1_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date2_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_box.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==1 | position==3 | position ==2){
                    type_select=position;
                    s2.setAdapter(date1_Adapter);
                    if(position==1)type_selected="Technical_Round";
                    else if (position==2)type_selected="Workshop";
                    else type_selected="Online_Round";
                }
                else{
                    type_select=4;
                    type_selected="Interview";
                    s2.setAdapter(date2_Adapter);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slots.clear();
                if (position==0)date_selected="16/11/2019";
                else if (position==1)date_selected="17/11/2019";
                else if (position==2)date_selected="18/11/2019";
                else if (position==3)date_selected="19/11/2019";
                else if (position==4)date_selected="20/11/2019";
                else if (position==5)date_selected="21/11/2019";
                else if (position==6)date_selected="22/11/2019";
                else if (position==7)date_selected="23/11/2019";
                else if (position==8)date_selected="24/11/2019";
                else if (position==9)date_selected="25/11/2019";
                else if (position==10)date_selected="26/11/2019";
                else if (position==11)date_selected="27/11/2019";
                else if (position==12)date_selected="28/11/2019";
                else if (position==13)date_selected="29/11/2019";
                else date_selected="30/11/2019";

                for (int i=0;i<8;i++) {
                    if (type_select==1) {
                        if (technical_Slots[position][i].size()<2) slots.add(convert_timeno(i));
                    }
                    else if (type_select==3) {
                        if (online_Slots[position][i].size()<2) slots.add(convert_timeno(i));
                    }
                    else if (type_select==2) {
                        if (workshop_Slots[position][i].size()<2) slots.add(convert_timeno(i));
                    }
                    else if (type_select==4) {
                        if (Interview_Slots[position][i].size()<6) {
                            slots.add(convert_timeno(i));
                        }
                    }
                }
                ArrayAdapter<String> new_list = new ArrayAdapter<String>(Company_Application_Slots.this,android.R.layout.simple_spinner_item,slots);
                new_list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s3.setAdapter(new_list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) time_selected="2:00-5:00";
                else if (position==1)time_selected="5:00-8:00";
                else if (position==2)time_selected="5:00-8:00";
                else if (position==3)time_selected="5:00-8:00";
                else if (position==4)time_selected="5:00-8:00";
                else if (position==5)time_selected="5:00-8:00";
                else if (position==6)time_selected="5:00-8:00";
                else time_selected="23:00-2:00";

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String Round;
                int date_new= convert_date(date_selected)-16;
                int time_new=convert_slot(time_selected);
                if (type_select==1){
                    if (technical_Slots[date_new][time_new].size()==0)place_allocated="TECH_ROOM1";
                    else
                        place_allocated="TECH_ROOM2";
                }
                else if (type_select==2){
                    if (workshop_Slots[date_new][time_new].size()==0)place_allocated="WORKSHOP_ROOM1";
                    else
                        place_allocated="WORKSHOP_ROOM2";
                }
                else if (type_select==3){
                    place_allocated="NULL";
                }
                else{
                    if (technical_Slots[date_new][time_new].size()==0)place_allocated="TECH_ROOM1";
                    else if (Interview_Slots[date_new][time_new].size()==1)place_allocated="INTERVIEW_ROOM2";
                    else if (Interview_Slots[date_new][time_new].size()==2)place_allocated="INTERVIEW_ROOM3";
                    else if (Interview_Slots[date_new][time_new].size()==3)place_allocated="INTERVIEW_ROOM4";
                    else if (Interview_Slots[date_new][time_new].size()==4)place_allocated="INTERVIEW_ROOM5";
                    else place_allocated="INTERVIEW_ROOM6";

                }

                Round = t1.getText().toString();
                if (Round.equals(""))Toast.makeText(Company_Application_Slots.this,"Round Should Be Selected",Toast.LENGTH_SHORT).show();
                else{
                    company_details new_company= new company_details();
                    new_company.setRound(Round);
                    new_company.setType(type_selected);
                    new_company.setDate(date_selected);
                    new_company.setSlot(time_selected);
                    new_company.setId(id);
                    new_company.setPlace(place_allocated);

                    database.child(id).child("Application_Slots").child(Round).setValue(new_company);
                    Toast.makeText(Company_Application_Slots.this,"Successful.Allocated Place is:"+place_allocated,Toast.LENGTH_SHORT).show();

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }
            }
        });

    }

    public int convert_date(String input){
        String changed= input.substring(0,2);
        int return_value= Integer.parseInt(changed);
        return return_value;
    }

    public int convert_slot(String input){
        if (input.equals("2:00-5:00"))return 0;
        else if (input.equals("5:00-8:00"))return 1;
        else if (input.equals("8:00-11:00"))return 2;
        else if (input.equals( "11:00-14:00"))return 3;
        else if (input.equals( "14:00-17:00"))return 4;
        else if (input.equals("17:00-20:00"))return 5;
        else if (input.equals("20:00-23:00"))return 6;
        else return 7;
    }

    public String convert_timeno(int input){
        if (input==0)return "2:00-5:00";
        else if (input==1)return  "5:00-8:00";
        else if (input==2)return  "8:00-11:00";
        else if (input==3)return  "11:00-14:00";
        else if (input==4)return  "14:00-17:00";
        else if (input==5)return  "17:00-20:00";
        else if (input==6)return  "20:00-23:00";
        else return "23:00-2:00";

    }
}
