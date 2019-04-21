# Placement_Portal
Group Assignment - 4 , 2nd year
I did the change







Technical Documentation
Placement Portal


GitHub Link :-

https://github.com/AranyaAryaman/Placement_Portal?fbclid=IwAR2NmaK5vYeECYXWmwMlT-p1znbGZT3bjJGgxbX9P33hurccdbhPojKDuwA
Instructions for Deployment

Download the given APK file.

Click on the file to install the app.

OR

Open the working code in your android app studio from the link given above

Enable developer option in your mobile phone.

Connect the phone to the pc, and Click on build and run on android studio and then run on your mobile via the ADK manager.




Content

Instruction for Deployment

Content

Preface

Hardware and Software Used for Developing

Team Distribution

Work Flow Chart

Database

UML Diagrams

Forms

Packages Used

Functions



Preface

The software is Developed on Android Studio 3.3.2 using Java and Google Firebase Database.

This software is Developed to make placement process easy and comfortable. GUI is made interactive by using several libraries and functions. The application provides common ground for Company, Student and Placement Administration to interact. Admin is the head of this system, every application request of students and company will be approved by him, admin will also allot time and venue for placement tests. Students and company will get the placement updates from the notices and notification sent by admin.

Hardware and Software Used for Developing




1. Android Studio 3.3.2 platform used for development
2. System Requirements
OS: Ubuntu

RAM: 4GB minimum

8GB recommended

Memory: 2GB of available hard disk

3. Google Firebase Database used
4. Advantages of using Firebase for App Development:

Real-time Database: Firebase has real-time and cloud-based database where you can store data in JSON and synchronized continuously to all connected clients.

Authentication: There are many apps that need identi_cation of a user to save user data in the cloud.
To provide an enhanced experience on various devices.

Hosting: Firebase provides fast, secure, static, and production-grade hosting for developers.

Storage: It is very useful when you want to create an app for storing and serving _les of users such and images and CV (for applying to the company).








Team Distribution


1. Sub-Team 1
Abhishek Jaiswal 170101002

Anubhav Tyagi 170101009

Vakul Gupta 170101076



2. Sub-Team 2
Keerti Harpavat 170101031

Utkarsh Santosh Mishra 170101083

Rishi Pathak 170101054

Devansh Gupta 170101022



3. Sub-Team 3
Aranya Aryaman 170101011

Sunny Kumar 170101068

Kadam Kiran 170101027

Autonu Kro 160101017



4. Sub-Team 4
Rakesh Gupta 170101050

Sai Kiran 170101039

Tikaram Meena 170101072

Aditya Vardhan Gara 170101003

Work Flow Chart

Student Part: -


























Admin Part: -
































Company Part: -



























DATABASE



Google Firebase Database is used as database.


Database Connectivity :-

Database Reference DB = Firebasedatabase.getInstance.getReference

Datebase Packages

AcademicDetails

Admin

Company

Data

Intern

Interns

Job

Jobs

JRF

JRF_application

Notices

Notices2company

Notifications

Notifications_Admin

Personal Details

RA

RegistrationDetails

Student










































ADMIN


























































ADMIN & STUDENT























































COMPANY
























































INTERNS























































JRF
























































NOTICE & NOTIFICATION






















































RA



























































STUDENT






















































NOTICE TO COMPANY


























































NOTICE TO STUDENTS

UML Diagrams





Standard language for specifying, visualizing, constructing, and documenting the artifacts of software systems, business modeling and other non-software systems.

The UML represents a collection of best engineering practices that have proven successful in the modeling of large and complex systems.

The UML is a very important part of developing object oriented software and the software development process.

The UML uses mostly graphical notations to express the design of software projects.

Using the UML helps project teams communicate, explore potential designs, and validate the architectural design of the software.




















1. Class Diagram: UML class diagrams show the classes of the system, their inter-relationships, and the operations and attributes of the classes

Explore domain concepts in the form of a domain model.

Analyze requirements in the form of a conceptual/analysis model.

Depict the detailed design of object-oriented or object-based software.



















































2. Activity Diagram: Activity diagrams helps to describe the flow of control of the target system, such as the exploring complex business rules and operations, describing the use case also the business process. It is object-oriented equivalent of flow charts and data-flow diagrams (DFDs).


























3. State Machine Diagram: State machine diagrams can show the different states of an entity also how an entity responds to various events by changing from one state to another. The history of an entity can best be modeled by a finite state diagram.


4. Use Cases Diagram: Use cases diagrams describes the behavior of the target system from an external point of view. Use cases describe "the meat" of the actual requirements.

Use cases: A use case describes a sequence of actions that provide something of measurable value to an actor and is drawn as a horizontal ellipse.

Actors: An actor is a person, organization, or external system that plays a role in one or more interactions with your system. Actors are drawn as stick figures.

Associations: Associations between actors and use cases are indicated by solid lines. An association exists whenever an actor is involved with an interaction described by a use case.



































5. Sequence Diagram: Sequence diagrams models the collaboration of objects based on a time sequence. It shows how the objects interact with others in a particular scenario of a use case.






























FORMS


Form 1: Adapter JRF Admin


Packages Used android.content.Context

android.support.annotation.NonNull android.support.v7.widget.CardView android.support.v7.widget.RecyclerView android.view.LayoutInflater

android.view.View

android.view.ViewGroup

android.widget.TextView com.group6.placementportal.DatabasePackage.JRF_application java.util.ArrayList


Main Functions
onBindViewHolder()

MyViewHolder()




Form 2: Adapter_Selected_Preferences


Packages Used android.graphics.Color android.support.v4.view.MotionEventCompat android.view.MotionEvent java.util.ArrayList


java.util.Collection

java.util.Collections

java.util.List


Main Functions OnCreateViewHolder() OnBindViewHolder() getItemCount()




Form 3: Admin_ApproveNotice


Packages Used com.google.firebase.database.DataSnapshot com.google.firebase.database.DatabaseError com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.Query com.google.firebase.database.ValueEventListener com.group6.placementportal.DatabasePackage.Notices java.util.ArrayList;java.util.Collections

Main Functions onDataChange() rejectnotice() onCancelled() approvenotice()




Form 4: Admin_checkout_the_portal

Packages Used

android.support.v7.app.AppCompatActivity

android.util.Log

android.view.View

android.widget.Toast

com.google.firebase.database.DataSnapshot

com.google.firebase.database.DatabaseError

com.google.firebase.database.DatabaseReference

com.google.firebase.database.FirebaseDatabase

com.google.firebase.database.ValueEventListener



Main Functions set_student_list () update_count() send_notification()


Form 5: Admin_DashBoard


Packages Used android.content.Context; android.content.Intent; android.net.ConnectivityManager; android.net.NetworkInfo;
android.os.Bundle;

android.support.design.widget.NavigationView;

android.support.v4.view.GravityCompat;

android.support.v4.widget.DrawerLayout;

android.support.v7.app.ActionBarDrawerToggle;

android.support.v7.app.AppCompatActivity

android.support.v7.widget.Toolbar;

android.view.Menu;

android.view.MenuItem;android.widget.Toast;

com.google.firebase.database.DatabaseReference;



Main Functions IsNetworkAvailable() onBackPressed() onCreateOptionsMenu() onNavigationItemSelected()




Form 6: Admin_enrollment_adapter


Packages Used android.support.annotation.NonNull android.support.v4.app.FragmentActivity android.support.v4.app.FragmentManage android.support.v7.widget.CardView android.support.v7.widget.RecyclerVie android.util.Log;android.view.LayoutInflater android.view.MenuItem android.view.View android.view.ViewGroup android.view.WindowManager android.widget.PopupMenu android.widget.TextView
android.widget.Toast

com.google.firebase.database.DataSnapshot

com.google.firebase.database.DatabaseError

com.google.firebase.database.DatabaseReference

com.google.firebase.database.FirebaseDatabase

com.google.firebase.database.ValueEventListener

com.google.firebase.database.core.Tag

com.group6.placementportal.DatabasePackage.Student

android.support.v4.app.Fragment

java.util.ArrayList

android.app.PendingIntent.getActivity



Main Functions set_color() onDataChange() onCancelled() admin_enrollment_adapter() MyViewHolder() onBindViewHolder() getItemCount()


Form 7: Admin_enrollments_screen1


Packages Used android.content.Context android.net.ConnectivityManager android.net.NetworkInfo android.os.Bundle android.support.annotation.NonNull android.support.v7.app.AppCompatActivity android.support.v7.widget.LinearLayoutManager android.support.v7.widget.RecyclerView com.group6.placementportal.DatabasePackage.Jobs
java.util.ArrayList


Main Functions onCreate() onDataChange() approvenotice() isNetworkAvailable()


Form 8: Admin enrolments screen1 adapter


Packages Used android.content.Intent android.support.annotation.NonNull android.support.v7.widget.CardVie com.google.firebase.database.DataSnapshot com.google.firebase.database.DatabaseError com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener com.group6.placementportal.DatabasePackage.Jobs java.util.ArrayList





Main Functions MyViewHolder() onBindViewHolder() onDataChange() getItemCount()


Form 9: Admin Notice

Packages Used
android.Manifest

android.app.DownloadManager;android.app.ProgressDialog

android.content.Context

android.content.DialogInterface

android.content.Intent

android.content.pm.PackageManager

android.graphics.Color

android.graphics.drawable.ColorDrawable

android.net.ConnectivityManager

android.net.NetworkInfo

android.net.Uri

android.os.Bundle

android.support.annotation.NonNull

android.support.v4.app.ActivityCompat

android.support.v4.content.ContextCompat

android.support.v7.app.AlertDialog

android.support.v7.app.AppCompatActivity

android.util.Log

android.view.View

android.widget.Button

android.widget.EditText

android.widget.TextView

android.widget.Toast

com.google.android.gms.tasks.OnFailureListener

com.google.android.gms.tasks.OnSuccessListener

com.google.firebase.database.DataSnapshot

com.google.firebase.database.DatabaseError

com.google.firebase.database.DatabaseReference

com.google.firebase.database.FirebaseDatabase

com.google.firebase.database.ValueEventListener

com.google.firebase.storage.FirebaseStorage

com.google.firebase.storage.OnProgressListener

com.google.firebase.storage.StorageReference

com.google.firebase.storage.UploadTask

com.group6.placementportal.DatabasePackage.notices2compan java.util.ArrayList


Main Functions onCreate() onDataChange() isNetworkAvailable() uploadpdf() onRequestPermissionsResult() selectPDF() onActivityResult() downloadFiles() convert_to_array() add_to_company()





Form 10: Admin send notification


Packages Used android.content.Context android.content.Intent android.net.ConnectivityManager android.net.NetworkInfo android.os.Bundle android.support.v7.app.AppCompatActivity android.view.View android.widget.Button android.widget.Toast






Main Functions onCreate() isNetworkAvailable() onCancelled() approvenotice()



Form 11: Apply For Interns


Packages Used android.Manifest android.app.ProgressDialog android.net.NetworkInfo android.net.Uri android.os.Bundle com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener com.google.firebase.storage.FirebaseStorage com.google.firebase.storage.OnProgressListener com.google.firebase.storage.StorageReference com.google.firebase.storage.UploadTask com.group6.placementportal.DatabasePackage.Interns com.group6.placementportal.DatabasePackage.Student



Main Functions onDataChange() isNetworkAvailable() getPDF() onRequestPermissionsResult selectPDF() onActivityResult() uploadFile()







Form 12: Apply_For_Jobs

Packages Used

com.google.android.gms.tasks.OnFailureListener com.google.android.gms.tasks.OnSuccessListener com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Stud



Main Functions OnCreate() onDataChange() isNetworkAvailable() getPDF() onRequestPermissionsResult selectPDF() onActivityResult() uploadFile()






Form 13: Approve_company


Packages Used android.support.v7.app.AppCompatActivity; android.support.v7.widget.LinearLayoutManager; android.support.v7.widget.RecyclerView; android.widget.Toast;



Main Functions onCreate() onCancelled() isNetworkAvailable()




Form 14: Approve_company_adapter

Packages Used com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.company com.group6.placementportal.DatabasePackage.job


Main Functions MyViewHolder() OnBindViewHolder() onDataChange() onCancelled() getItemCount()


Form 15: Card


Packages Used android.content.Context android.support.annotation.NonNull android.util.AttributeSet android.widget.Button



Main Functions setline() getLine()




Form 16: CardArrayAdapter


Packages Used android.graphics.Bitmapandroid.graphics.BitmapFactory android.view.LayoutInflater

Main Functions CardArrayAdapter() Add() getCount() Bitmap()



Form 17: Company_Application_Slots


Packages Used android.widget.Spinner android.widget.Toast com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListene


Main Functions onCreate() onCancelled() onItemSelected() onNothingSelected() onItemSelected() isNetworkAvailable() convert_date() convert_slot() convert_cotimeno()


Form 18: Company_change_password




Packages Used android.os.Bundle android.support.v7.app.AppCompatActivity android.view.View

Main Functions OnCreate() setOnClickListener() onCancelled() isNetworkAvailable()




Form 19: Company dashboard


Packages Used android.support.v7.app.AppCompatActivity


Main Functions onAttach() onCreate() isNetworkAvailable()


Form 20: Company_details


Packages Used com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.company




Main Functions getPlace() setPlace() setId() getRound() setRound() getType() setType()
getDate()

setDate()

getSlot()

setSlot()


Form 21: Company_enrollments


Packages Used android.content.res.Resources.Theme android.support.design.widget.FloatingActionButton android.support.design.widget.Snackbar android.support.v4.app.Fragment android.support.v7.app.AppCompatActivity android.support.v7.widget.ThemedSpinnerAdapter andoid.support.v7.widget.LinearLayoutManager

Main Functions

onBackPressed()

onCreate()

onDataChange()

onCreateOptionsMenu()

onOptionsItemSelected()

MyAdapter()

getDropDownView()

onCreateView()


Form 22: Company_enrolments_screen1


Packages Used com.google.firebase.database.DataSnapsho com.google.firebase.database.DatabaseError

Main Functions
OnCreate()

onDataChange()

onCancelled()

isNetworkAvailable()


Form 23: Company_login


Packages Used android.support.v7.app.AppCompatActivity


Main Functions getUser() onDataChange() onCancelled() isNetworkAvailable()


Form 24: company_notices


Packages Used android.widget.AdapterView android.widget.ArrayAdapter android.widget.Spinner android.widget.TextView android.widget.Toast


Main Functions OnCreate() onDataChange() onCancelled() isNetworkAvailable()


Form 25: company_profile

Packages Used com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener


Main Functions onAttach() onDataChange() onCancelled() beforeTextChanged() onTextChanged() afterTextChanged() onTextChanged() isNetworkAvailable()


Form 26: Company_Slots


Packages Used com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase


Main Functions onCreate() isNetworkAvailable()


Form 27: Company_Slots_Admin


Packages Used java.util.ArrayList java.util.List


Main Functions OnCreate() onDataChange()
onCancelled()

isNetworkAvailable()


Form 28: Company_Slots_Admin_Adapter


Packages Used android.widget.RelativeLayout java.util.List


Main Functions onCreateViewHolder() getItemCount() MyviewHolder()


Form 29: Company_Slots_Admin_second


Packages Used android.widget.AdapterView android.widget.ArrayAdapter android.widget.Spinner android.widget.TextView android.widget.Toast com.google.firebase.database.DataSnapshot com.google.firebase.database.DatabaseError com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener






Main Functions OnCreate() onDataChange() onCancelled()
onItemSelected()

onNothingSelected()


Form 30: Company_Slots_customclass


Packages Used com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener


Main Functions OnCreateViewHolder() setCompanyName() getCompanyId() Company_Slots_customclass()



Form 31: Encryption


Packages Used java.io.UnsupportedEncodingException java.security.InvalidAlgorithmParameterException java.security.InvalidKeyException java.security.MessageDigest


Main Functions Encryption() getDefault() encryptOrNull() encryptAsync() decryptOrNull() decryptAsync() Callback() Builder() getCharsetName()
getAlgorithm()

setAlgorithm()

getKeyAlgorithm()

setKeyAlgorithm()

getsetBase64Mode()

Base64Mode()

setIterationCount()

getSecureRandomAlgorithm()

setSecureRandomAlgorithm()

getSecureRandomv()

setIvParameterSpec()

setDigestAlgorithm()



Form 32: enrolment_adapter


Packages Used com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Student


Main Functions onCreateViewHolder() OnBindViewHolder() getItemCount() MyViewHolder()



Form 33: enrolments_screen1_adpater


Packages Used com.group6.placementportal.DatabasePackage.Jobs java.util.ArrayList


Main Functions MyViewHolder() OnBindViewHolder() onDataChange() onCancelled() getItemCount()


Form 34: ExpandableListAdapter


Packages Used java.util.HashMap java.util.List


Main Functions ExpandableListAdapter() getGroup() getChild() getGroupId() getChildId() hasStableIds() getGroupView() getChildView() isChildSelectable() getItemCount()


Form 35: GivePreference


Packages Used com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication java.util.ArrayList

java.util.List

Main Functions onCreate() isNetworkAvailable() AllowUsertogivePreferences() onCancelled() checkUserApplication() CAllDatabase() onBackPressed() onNavigationItemSelected() onSignOutClicked() updateSignedOutUI() onMove()

onSwiped()



Form 36: Help_Students


Packages Used java.util.List


Main Functions onBackPressed() onNavigationItemSelected() onSignOutClicked() updateSignedOutUI()

Form37: intern_adapter


Packages Used com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Interns com.group6.placementportal.DatabasePackage.company


Main Functions
OnCreateViewHolder()

OnBindViewHolder()

getItemCount()

MyViewHolder()


Form 38: intern_list


Packages Used com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener java.util.ArrayList



Main Functions onCreate() onDataChange() onCancelled() isNetworkAvailable()


Form 39: intern_profile


Packages Used com.google.firebase.storage.StorageReference com.google.firebase.storage.UploadTask com.group6.placementportal.DatabasePackage.Interns


Main Functions onDataChange() onCancelled() uploadpdf() onRequestPermissionsResult() selectPDF()

add_intern()
downloadFiles()



Form 40: job_adapter


Packages Used android.view.LayoutInflater android.view.View android.view.ViewGroup



Main Functions job_adapter() onCreateViewHolder() onBindViewHolder() getItemCount() MyViewHolder()

Form 41: job_list


Packages Used android.net.ConnectivityManager android.net.NetworkInfo android.os.Bundle


Main Functions onDataChange() onCancelled() isNetworkAvailable()


Form 42: job_profile

Packages Used com.google.firebase.storage.UploadTask com.group6.placementportal.DatabasePackage.Jobs

com.group6.placementportal.DatabasePackage.job java.util.ArrayList



Main Functions onDataChange() uploadpdf() onCancelled() onRequestPermissionsResult() add_job()



Form43: JRF_Application_Requests


Packages Used android.content.Context android.net.ConnectivityManager android.net.NetworkInfo

Main Functions onDataChange() onCancelled() onBackPressed() onCreateOptionsMenu() onNavigationItemSelected()


Form 44: JRF_Approval_Profile


Packages Used java.util.ArrayList java.util.HashMap java.util.List

Main Functions

JRF_Approval_Profile()
onDataChange()

onCancelled()


Form 45: list_notification

Packages Used onCreate() onDataChange() onCancelled() isNetworkAvailable()


Form 46: Login_Page_Admin


Packages Used com.group6.placementportal.DatabasePackage.Admin


Main Functions onCreate() onDataChange() onCancelled() updateSuccessUI() isNetworkAvailable()


Form 47: LoginPage


Packages Used org.json.JSONException org.json.JSONObject java.util.HashMap java.util.List java.util.Map


Main Functions onCreate()
onDataChange()

onCancelled()

onActivityResult()

onCallGraphClicked()

onResponse()

onErrorResponse()

getHeaders()

VerifyUser()

onCancelled()

isNetworkAvailable()

getActivity()

onSuccess()


Form 48: MainLogin


Packages Used com.google.firebase.database.DatabaseReference


Main Functions onCreate() isNetworkAvailable()


Form 49 : MyAdapter


Packages Used com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Student



Main Functions MyAdapter () onCreateViewHolder()
onBindViewHolder()

getItemCount()

MyViewHolder()




Form 50: MyAdapter_intern


Packages Used com.group6.placementportal.DatabasePackage.Interns com.group6.placementportal.DatabasePackage.Student


Main Functions onCreateViewHolder() onBindViewHolder() getItemCount()


Form 51: MyAdapter_Notices


Packages Used com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Student com.group6.placementportal.DatabasePackage.Notices com.squareup.picasso.Picasso


Main Functions onCreateViewHolder() onBindViewHolder() getItemCount() MyViewHolder()



Form 52: MyAdapter_Notices_FromCompany

Packages Used com.group6.placementportal.DatabasePackage.Notices com.squareup.picasso.Picasso




Main functions OnItemClickListener() setOnItemClickListener() MyAdapter_Notices_FromCompany() onCreateViewHolder() onBindViewHolder() getItemCount()


MyViewHolder()



Form 53: Login_Page_Admin


Packages Used com.group6.placementportal.DatabasePackage.Admin

Main Functions

onCreate()

onDataChange()

onCancelled()

updateSuccessUI()

isNetworkAvailable()
Form 54: LoginPage










Packages Used com.microsoft.identity.client.AuthenticationCallback com.microsoft.identity.client.AuthenticationResult com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication com.microsoft.identity.client.exception.MsalClientException com.microsoft.identity.client.exception.MsalException com.microsoft.identity.client.exception.MsalServiceException com.microsoft.identity.client.exception.MsalUiRequiredException org.json.JSONException

org.json.JSONObject
java.util.HashMap
java.util.List
java.util.Map




Main Functions onCreate() onDataChange() onCancelled() onActivityResult() onCallGraphClicked() callGraphAPI() onResponse() onErrorResponse() isNetworkAvailable() updateSuccessUI() getActivity() onSuccess() onError()
onCancel()















Form 55: MainLogin











Packages Used com.google.firebase.database.DatabaseReference





Main Functions onCreate isNetworkAvailable











Form 56: MyAdapter


Packages Used

com.google.firebase.database.core.Tag com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Student java.util.ArrayList





Main Functions onCreateViewHolder() onBindViewHolder() getItemCount() MyViewHolder()











Form 57: MyAdapter_intern









Packages Used com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Student



Main Functions onCreateViewHolder() onBindViewHolder() getItemCount() MyViewHolder()













Form 58: MyAdapter_Notices













Packages Used com.group6.placementportal.DatabasePackage.Notice com.squareup.picasso.Picasso




Main Functions onCreateViewHolder() onBindViewHolder() getItemCount() MyViewHolder()














Form 59: MyAdapter_Notices_FromCompany













Packages Used com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Student java.util.ArrayList


Main Functions

MyAdapter_Notices_FromCompany() onCreateViewHolder() onBindViewHolder() getItemCount()
MyViewHolder()
















Form 60: MyAdapter_Notifications


Packages Used com.group6.placementportal.DatabasePackage.Notifications



Main Functions MyAdapter_Notifications() onCreateViewHolder() onBindViewHolder() getItemCount() MyViewHolder()














Form61: MyExpandableListView









Packages Used android.content.Context android.util.AttributeSet android.view.ViewGroup android.widget.ExpandableListView



Main Functions MyExpandableListView() onMeasure()


Form62: NoticeFromCompany








Packages Used android.webkit.MimeTypeMap android.widget.ImageView com.google.firebase.storage.UploadTask com.group6.placementportal.DatabasePackage.Notices com.squareup.picasso.Picasso



Main Functions onCreate() isNetworkAvailable() onActivityResult() getFileExtension() Upload_file()








Form63: notification_card










Packages Used

package com.group6.placementportal


Main Functions notification_card() getText() setText3()















Form64: notification_layout









Packages Used android.view.LayoutInflater; android.view.View; android.view.ViewGroup; android.widget.TextView;


Main Functions notificationViewHolder() notification_layout() onBindViewHolder() getItemCount()










Form65: open_notification









Packages Used com.google.firebase.database.DatabaseReference





Main Functions onCreate() isNetworkAvailable()











Form66: recipients_tab








Packages Used com.google.firebase.database.DataSnapshot com.google.firebase.database.DatabaseError

com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener;

Main Functions onDataChange() onCreate() isNetworkAvailable()


















Form67: RecycleNameTouchHelper











Packages Used android.support.annotation.NonNull android.support.v7.widget.RecyclerView android.support.v7.widget.helper.ItemTouchHelper



Main Functions getMovementFlags() onMove() onSwiped()













Form68: Retrieving_Notifications










Packages Used

com.group6.placementportal



Main Functions getSubject() setSubject() getDescription() getNotification_id() getPdf_link() setPdf_link()






Form69: sending_emails




Packages Used android.view.View android.widget.Button android.widget.EditText android.widget.Toast



Main Functions onCreate() isNetworkAvailable()








Form70: Sending_Notifications











Packages Used com.google.android.gms.tasks.OnFailureListener com.google.android.gms.tasks.OnSuccessListener com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.storage.FirebaseStorage com.google.firebase.storage.OnProgressListener com.google.firebase.storage.StorageReference com.google.firebase.storage.UploadTask com.group6.placementportal.DatabasePackage.Notifications_Admin


Main Functions onCreate() isNetworkAvailable() onRequestPermissionsResult() onActivityResult() newNotification()










Form71: single_dialog_companyenrollments_0











Packages Use com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener




Main Functions add_selection() send_notification() get_list() onCancelled() set_student_list() onCreateDialog() onDataChange()






Form72: Student_Application_Forms








Packages Used com.group6.placementportal.DatabasePackage.JRF com.group6.placementportal.DatabasePackage.RA com.group6.placementportal.DatabasePackage.Student



Main Functions onCreate() isNetworkAvailable() PlaceholderFragment() onCreateView() SectionsPagerAdapter() getCount()











Form73: Student_ChangePassword






Packages Used com.google.firebase.database.DatabaseReference

com.google.firebase.database.FirebaseDatabase com.group6.placementportal.DatabasePackage.Student com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication java.util.List



Main Functions onCreate() isNetworkAvailable() onBackPressed() onCreateOptionsMenu() onNavigationItemSelected() onSignOutClicked() updateSignedOutUI()





Form74: Student_Complete_Profile









Packages Used com.group6.placementportal.DatabasePackage.AcademicDetails com.group6.placementportal.DatabasePackage.PersonalDetails com.group6.placementportal.DatabasePackage.Student




Main Functions onCreate() onClick() isNetworkAvailable() getData()
checkData()

InsertIntoDatabase()

setTextBoxes()

checkUserProfileStatus()

onDataChange()

onCancelled()










Form75: Student_Dashboard











Packages Used com.group6.placementportal.DatabasePackage.Student com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication java.util.ArrayList

java.util.Collections

java.util.List



Main Functions onCreate() onDataChange() onCancelled() isNetworkAvailable() onBackPressed() onNavigationItemSelected()
onSignOutClicked()

updateSignedOutUI()















Form76: Student_Foreground_Notification









Packages Used android.util.Log com.google.firebase.database.DataSnapshot com.google.firebase.database.DatabaseError com.google.firebase.database.DatabaseReference




Main Functions onCreate() onHandleIntent() onDataChange() onCancelled() showNotif()




Form77: Student_JRF







Packages Used com.google.android.gms.tasks.OnFailureListener com.google.android.gms.tasks.OnSuccessListener com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.storage.FirebaseStorage com.google.firebase.storage.OnProgressListener com.google.firebase.storage.StorageReference com.google.firebase.storage.UploadTask com.group6.placementportal.DatabasePackage.Student com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication




Main Functions onCreate() isNetworkAvailable() getPDFsign() getPDFphoto() onRequestPermissionsResult() selectPDFsign() selectPDFphoto() onActivityResult() uploadFilesign() onProgress() uploadFilephoto() uploadFilephoto() onCreateOptionsMenu() onNavigationItemSelected() onSignOutClicked() updateSignedOutUI()







Form78: Student_Notifications









Packages Used com.google.firebase.database.ValueEventListener com.group6.placementportal.DatabasePackage.Notifications com.group6.placementportal.DatabasePackage.Student com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication import java.util.ArrayList

import java.util.Collections import java.util.List





Main Functions onCreate() onDataChange() isNetworkAvailable() onBackPressed() onCreateOptionsMenu() onNavigationItemSelected() onNavigationItemSelected() updateSignedOutUI()
Form79: Student_Profile









Packages Used android.content.Context android.content.Intent com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase




Main Functions isNumeric() onCreate() isNetworkAvailable()










Form80: Student_Profile_Approval









Packages Used com.group6.placementportal.DatabasePackage.AcademicDetails com.group6.placementportal.DatabasePackage.Data com.group6.placementportal.DatabasePackage.Notifications com.group6.placementportal.DatabasePackage.PersonalDetails com.group6.placementportal.DatabasePackage.Student





Main Functions onCreate() onDataChange() onCancelled() isNetworkAvailable() initData()




Form81: student_RA








Packages Used android.support.v7.app.AppCompatActivity android.widget.Toast




Main Functions onCreate() isNetworkAvailable()




Form82: Student_Requests

Packages Used com.google.firebase.database.DataSnapshot com.google.firebase.database.DatabaseError com.google.firebase.database.DatabaseReference com.google.firebase.database.FirebaseDatabase com.google.firebase.database.ValueEventListener com.group6.placementportal.DatabasePackage.Student



Main Functions onCreate() onDataChange() onCancelled() isNetworkAvailable() onBackPressed() onCreateOptionsMenu() onNavigationItemSelected()








Form83: Student_Requests_Adapter









Packages Used com.group6.placementportal.DatabasePackage.Student




Main Functions

Student_Requests_Adapter()
onCreateViewHolder()

onBindViewHolder()

getItemCount()

MyViewHolder()








Form84: View_Interns









Packages Used com.group6.placementportal.DatabasePackage.Interns com.group6.placementportal.DatabasePackage.Student com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication java.util.ArrayList

java.util.List




Main Functions onCreate() onDataChange() onCancelled() isNetworkAvailable() onBackPressed() onNavigationItemSelected() onSignOutClicked() updateSignedOutUI()




Form85: View_Jobs









Packages Used com.google.firebase.database.ValueEventListener com.group6.placementportal.DatabasePackage.Jobs com.group6.placementportal.DatabasePackage.Student com.microsoft.identity.client.IAccount com.microsoft.identity.client.PublicClientApplication java.util.ArrayList

java.util.List


Main Functions onCreate() onDataChange() onCancelled() isNetworkAvailable() onBackPressed() onNavigationItemSelected() onSignOutClicked() updateSignedOutUI()




Thank You
