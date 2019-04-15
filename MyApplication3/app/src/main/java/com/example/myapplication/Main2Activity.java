package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Firebase database;
    List<company_details> list= new ArrayList<company_details>();  //Array class to store all company details applied
    ArrayList[][] Slots= new ArrayList[60][8];                     //Array of Array lists
    List<company_details> listA= new ArrayList<company_details>();
    List<company_details> listB= new ArrayList<company_details>();
    List<company_details> listC = new ArrayList<company_details>();
    List<company_details> listD = new ArrayList<company_details>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Fetch the information from the database and store in a array of classes
        Firebase.setAndroidContext(this);
        database = new Firebase("https://placement-portal-89289.firebaseio.com/Company");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot csnapshot: dataSnapshot.getChildren()) {
                    if (csnapshot.hasChild("Application_Slots")) {
                        DataSnapshot Application = csnapshot.child("Application_Slots");
                        for (DataSnapshot ccsnapshot :Application.getChildren()){
                            list.add(Application.child(ccsnapshot.getKey()).getValue(company_details.class));
                        }

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        // Allocating Array List to every slot,So that many companyies can be allocated to each slot.
        for (int i=0;i<60;i++){
            for (int j=0;j<8;j++){
                Slots[i][j]= new ArrayList();
            }
        }
    }

    public String sixtyConverter(String s){
//        int temp1,temp2;
//        temp1=temp2=0;
//        for(int i=0;i<s.length();i++){
//            if(Character.toString(s.charAt(i)).equals("/")){
//                temp1=i;
//                break;
//            }
//        }
//        for(int j=temp1+1;j<s.length();j++){
//            if(Character.toString(s.charAt(j)).equals("/")){
//                temp2=j;
//                break;
//            }
//        }
        String s1,s2;
        s1=s2="";
        s1 += s.substring(0,2);
        s2 += s.substring(3,5);
        if (s2.equals("02")){
            return Integer.toString(30 + Integer.parseInt(s1));
        }
        else if (s2.equals("03")){
            return s1;
        }
        else{
            System.out.println("Only October and November are allowed dates.");
            return "0";
        }
    }

    public String EightConverter(String s){
        int temp1=0;
/*
for(int i=0;i<s.length()-1;i++){
if(Character.toString(s.charAt(i)).equals("-")){
temp1=i;
break;
}
}
*/


String s1,s2;
s1=s2="";
//s1 += s.substring(0,temp1);
//s2 += s.substring(temp1+1);
s1 += s.substring(0,2);
s2 += s.substring(3);
        if(s1.equals("15")&& s2.equals("17")){
            return "0";
        }
        else if(s1.equals("17") && s2.equals("19")){
            return "1";
        }
        else if(s1.equals("19") && s1.equals("21")){
            return "2";
        }
/*
else if(s1=="11" && s2=="14"){
return "3";
}
else if(s1=="15" && s2=="17"){
return "4";
}
else if(s1=="17" && s2=="19"){
return "5";
}
else if(s1=="19" && s2=="21"){
return "6";
}
else if(s1=="23" && s2=="02"){
return "7";
}
*/
        else{
            System.out.println("The Time given is Wrong, ERROR ");
            return "-1";
        }
    }

    public void Allocate_click(View view){
        Toast.makeText(this,"Total No of Companies Applied is :"+list.size(),Toast.LENGTH_SHORT).show();
        for (company_details o : list){
            Toast.makeText(this,"The companies applied are: "+ o.getCompany_id(),Toast.LENGTH_LONG).show(); //used for debugging

o.setPref1_date(sixtyConverter(o.getPref1_date()));
o.setPref2_date(sixtyConverter(o.getPref2_date()));
o.setPref3_date(sixtyConverter(o.getPref3_date()));
o.setPref1_time(EightConverter(o.getPref1_time()));
o.setPref2_time(EightConverter(o.getPref2_time()));
o.setPref3_time(EightConverter(o.getPref3_time()));

            if(o.getCompany_rank().toString().equals("A") ){
                listA.add(o);
            }
            if(o.getCompany_rank().toString().equals("B") ){
                listB.add(o);
             //   Toast.makeText(this,"B group company added: "+o.getCompany_id(),Toast.LENGTH_LONG).show();
             }
            if(o.getCompany_rank().toString().equals("C") ){
                listC.add(o);
               // Toast.makeText(this,"C group company added: "+o.getCompany_id(),Toast.LENGTH_LONG).show();
            }
            if(o.getCompany_rank().toString().equals("D") ){
                listD.add(o);
                //Toast.makeText(this,"D group company added: "+o.getCompany_id(),Toast.LENGTH_LONG).show();
            }
        }

        for(company_details oa : listA){
           // for debugging ___ Toast.makeText(this,"A group company added: "+ oa.getCompany_id()+" "+oa.getPref1_date()+" "+oa.getPref1_time(),Toast.LENGTH_LONG).show();
            if(Slots[Integer.parseInt(oa.getPref1_date())][Integer.parseInt(oa.getPref1_time())].size()==0){
                oa.setResult_date(oa.getPref1_date());
                oa.setResult_time(oa.getPref1_time());
                Toast.makeText(this,"A group company added: "+oa.getCompany_id()+" " +oa.getResult_date()+" " +oa.getResult_time() ,Toast.LENGTH_LONG).show();
            }
            else if(Slots[Integer.parseInt(oa.getPref2_date())][Integer.parseInt(oa.getPref2_time())].size()==0){
                oa.setResult_date(oa.getPref2_date());
                oa.setResult_time(oa.getPref2_time());
            }
            else if(Slots[Integer.parseInt(oa.getPref3_date())][Integer.parseInt(oa.getPref3_time())].size()==0){
                oa.setResult_date(oa.getPref3_date());
                oa.setResult_time(oa.getPref3_time());
            }
            else{
                oa.setResult_date("NOT ALLOCATED");
                oa.setResult_time("NOT ALLOCATED");
            }
        }

        for(company_details ob : listB){
            //Toast.makeText(this,"B group company added: "+ob.getCompany_id(),Toast.LENGTH_LONG).show();
            if(Slots[Integer.parseInt(ob.getPref1_date())][Integer.parseInt(ob.getPref1_time())].size()==0){
                ob.setResult_date(ob.getPref1_date());
                ob.setResult_time(ob.getPref1_time());
            }
            else if(Slots[Integer.parseInt(ob.getPref2_date())][Integer.parseInt(ob.getPref2_time())].size()==0){
                ob.setResult_date(ob.getPref2_date());
                ob.setResult_time(ob.getPref2_time());
            }
            else if(Slots[Integer.parseInt(ob.getPref3_date())][Integer.parseInt(ob.getPref3_time())].size()==0){
                ob.setResult_date(ob.getPref3_date());
                ob.setResult_time(ob.getPref3_time());
            }
            else{
                ob.setResult_date("NOT ALLOCATED");
                ob.setResult_time("NOT ALLOCATED");
            }
        }

        for(company_details oc : listC){
           //  Toast.makeText(this,"C group company added: "+oc.getCompany_id(),Toast.LENGTH_LONG).show();
            if(Slots[Integer.parseInt(oc.getPref1_date())][Integer.parseInt(oc.getPref1_time())].size()==0){
                oc.setResult_date(oc.getPref1_date());
                oc.setResult_time(oc.getPref1_time());
            }
            else if(Slots[Integer.parseInt(oc.getPref2_date())][Integer.parseInt(oc.getPref2_time())].size()==0){
                oc.setResult_date(oc.getPref2_date());
                oc.setResult_time(oc.getPref2_time());
            }
            else if(Slots[Integer.parseInt(oc.getPref3_date())][Integer.parseInt(oc.getPref3_time())].size()==0){
                oc.setResult_date(oc.getPref3_date());
                oc.setResult_time(oc.getPref3_time());
            }
            else{
                oc.setResult_date("NOT ALLOCATED");
                oc.setResult_time("NOT ALLOCATED");
            }
        }

        for(company_details od : listD) {
         //   Toast.makeText(this, "D group company added: " + od.getCompany_id(), Toast.LENGTH_LONG).show();
            if(Slots[Integer.parseInt(od.getPref1_date())][Integer.parseInt(od.getPref1_time())].size()==0){
                od.setResult_date(od.getPref1_date());
                od.setResult_time(od.getPref1_time());
            }
            else if(Slots[Integer.parseInt(od.getPref2_date())][Integer.parseInt(od.getPref2_time())].size()==0){
                od.setResult_date(od.getPref2_date());
                od.setResult_time(od.getPref2_time());
            }
            else if(Slots[Integer.parseInt(od.getPref3_date())][Integer.parseInt(od.getPref3_time())].size()==0){
                od.setResult_date(od.getPref3_date());
                od.setResult_time(od.getPref3_time());
            }
            else{
                od.setResult_date("NOT ALLOCATED");
                od.setResult_time("NOT ALLOCATED");
            }
        }

    }

}
