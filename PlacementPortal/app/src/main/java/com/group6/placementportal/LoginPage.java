package com.group6.placementportal;

import android.app.Activity;
import android.app.ProgressDialog;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.placementportal.DatabasePackage.Student;
import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.MsalClientException;
import com.microsoft.identity.client.exception.MsalException;
import com.microsoft.identity.client.exception.MsalServiceException;
import com.microsoft.identity.client.exception.MsalUiRequiredException;
<<<<<<< HEAD

import org.json.JSONException;
import org.json.JSONObject;

=======

import org.json.JSONException;
import org.json.JSONObject;

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    private DatabaseReference Login_Details;
    private EditText Webmail;
    private EditText Password;
    private Button login_button;
    private String rollNo;
    private String password;
    private String check_password;
    private String WebmailID;
    private String RollNo;
    private String Programme;
    private String FullName;
    private Student user;
    private boolean firstTimeUser=false;
<<<<<<< HEAD

    private ProgressDialog dialog;

    /* Azure AD v2 Configs */
    final static String SCOPES [] = {"https://graph.microsoft.com/User.Read"};
    final static String MSGRAPH_URL = "https://graph.microsoft.com/v1.0/me";

    /* UI & Debugging Variables */
    private static final String TAG = LoginPage.class.getSimpleName();
    private String userData;
    Button callGraphButton;
//    Button signOutButton;

=======
    SharedPreferences sp;
    private int flag_2;
    private ProgressDialog dialog;

    /* Azure AD v2 Configs */
    final static String SCOPES [] = {"https://graph.microsoft.com/User.Read"};
    final static String MSGRAPH_URL = "https://graph.microsoft.com/v1.0/me";

    /* UI & Debugging Variables */
    private static final String TAG = LoginPage.class.getSimpleName();
    private String userData;
    Button callGraphButton;
//    Button signOutButton;

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
    /* Azure AD Variables */
    private PublicClientApplication sampleApp;
    private AuthenticationResult authResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

<<<<<<< HEAD
=======
        if(isNetworkAvailable()==false){
            Toast.makeText(LoginPage.this,"NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            return;
        }
        flag_2=getIntent().getIntExtra("flag",0);

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        callGraphButton = findViewById(R.id.callGraph);
        //signOutButton = findViewById(R.id.clearCache);
        dialog = new ProgressDialog(LoginPage.this);

        dialog.setMessage("Please Wait");

        Log.d("TAG",(callGraphButton!=null)+" ");
        callGraphButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();
                onCallGraphClicked();
            }
        });

//        signOutButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                onSignOutClicked();
//            }
//        });

        /* Configure your sample app and save state for this activity */
        sampleApp = null;
        if (sampleApp == null) {
            sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
        }

        /* Attempt to get a user and acquireTokenSilent
         * If this fails we do an interactive request
         */
        List<IAccount> accounts = null;

        try {
            accounts = sampleApp.getAccounts();

            if (accounts != null && accounts.size() == 1) {
                /* We have 1 account */
                sampleApp.acquireTokenSilentAsync(SCOPES, accounts.get(0), getAuthSilentCallback());
//                Toast.makeText(LoginPage.this,accounts.get(0).getUsername(), Toast.LENGTH_LONG).show();
            } else {
                /* We have no account or >1 account */
            }
        } catch (IndexOutOfBoundsException e) {
            Log.d(TAG, "Account at this position does not exist: " + e.toString());
        }

        Login_Details= FirebaseDatabase.getInstance().getReference();

        //Taking Views From the screen
        Webmail=findViewById(R.id.webmail_text);
        Password=findViewById(R.id.password_text);
        login_button=findViewById(R.id.btn_login);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(flag_2==1){
            sp.edit().putBoolean("logged",false).apply();
        }

<<<<<<< HEAD
=======
//        if(sp.getBoolean("logged",false)){
//            updateSuccessUI();
//        }

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                rollNo = Webmail.getText().toString();
                password = Password.getText().toString();
                Login_Details= FirebaseDatabase.getInstance().getReference();
                Login_Details = Login_Details.child("Student").child(rollNo);


               /* Login_Details.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        check_password = dataSnapshot.child("password").getValue(String.class);
                        if(password.equals(check_password)){
                            user = dataSnapshot.getValue(Student.class);
                            firstTimeUser = false;
                            dialog.hide();
                            Toast.makeText(LoginPage.this,user.getWebmailID(), Toast.LENGTH_LONG).show();
                            updateSuccessUI();
<<<<<<< HEAD
=======
                            sp.edit().putBoolean("logged",true).apply();
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
                        }
                        else{
                            dialog.hide();
                            Toast.makeText(LoginPage.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginPage.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                    }



<<<<<<< HEAD
                });*/

=======
                });
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6



            }
        });



    }

    //
    // Core Identity methods used by MSAL
    // ==================================
    // onActivityResult() - handles redirect from System browser
    // onCallGraphClicked() - attempts to get tokens for graph, if it succeeds calls graph & updates UI
    // onSignOutClicked() - Signs account out of the app & updates UI
    // callGraphAPI() - called on successful token acquisition which makes an HTTP request to graph
    //

    /* Handles the redirect from the System Browser */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        sampleApp.handleInteractiveRequestRedirect(requestCode, resultCode, data);
    }

    /* Use MSAL to acquireToken for the end-user
     * Callback will call Graph api w/ access token & update UI
     */
    private void onCallGraphClicked() {
        sampleApp.acquireToken(getActivity(), SCOPES, getAuthInteractiveCallback());
    }

    /* Clears an account's tokens from the cache.
     * Logically similar to "sign out" but only signs out of this app.
     */
//    private void onSignOutClicked() {
//
//        /* Attempt to get a account and remove their cookies from cache */
//        List<IAccount> accounts = null;
//
//        try {
//            accounts = sampleApp.getAccounts();
//
//            if (accounts == null) {
//                /* We have no accounts */
//
//            } else if (accounts.size() == 1) {
//                /* We have 1 account */
//                /* Remove from token cache */
//                sampleApp.removeAccount(accounts.get(0));
//                updateSignedOutUI();
//
//            }
//            else {
//                /* We have multiple accounts */
//                for (int i = 0; i < accounts.size(); i++) {
//                    sampleApp.removeAccount(accounts.get(i));
//                }
//            }
//
//            Toast.makeText(getBaseContext(), "Signed Out!", Toast.LENGTH_SHORT)
//                    .show();
//
//        } catch (IndexOutOfBoundsException e) {
//            Log.d(TAG, "User at this position does not exist: " + e.toString());
//        }
//    }

    /* Use Volley to make an HTTP request to the /me endpoint from MS Graph using an access token */
    private void callGraphAPI() {
        Log.d(TAG, "Starting volley request to graph");

        /* Make sure we have a token to send to graph */
        if (authResult.getAccessToken() == null) {return;}

        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("key", "value");
        } catch (Exception e) {
            Log.d(TAG, "Failed to put parameters: " + e.toString());
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, MSGRAPH_URL,
                parameters,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                /* Successfully called graph, process data and send to UI */
                Log.d(TAG, "Response: " + response.toString());
                userData = response.toString();
                try {
                    FullName=response.getString("displayName");
                    WebmailID=response.getString("mail");
                    RollNo=response.getString("surname");
                    Programme=response.getString("jobTitle");
                    WebmailID = WebmailID.split("@")[0];
                    firstTimeUser = VerifyUser(WebmailID);
                    Log.d(TAG,WebmailID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /* update the UI to post call graph state */


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.hide();
                Log.d(TAG, "Error: " + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authResult.getAccessToken());
                return headers;
            }
        };

        Log.d(TAG, "Adding HTTP GET to Queue, Request: " + request.toString());

        request.setRetryPolicy(new DefaultRetryPolicy(
                3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private boolean VerifyUser(final String webmailID){
        final boolean[] isSignUp = {false};
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase = mDatabase.child("Student").child(webmailID);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.hasChild("password");
                isSignUp[0]= (!value);
                Log.d(TAG,!value+" f"+isSignUp[0]);
                firstTimeUser=(!value);
                if(value){
                    user = dataSnapshot.getValue(Student.class);
                }
                updateSuccessUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return isSignUp[0];
    }

    //
    // Helper methods manage UI updates
    // ================================
    // updateGraphUI() - Sets graph response in UI
    // updateSuccessUI() - Updates UI when token acquisition succeeds
    // updateSignedOutUI() - Updates UI when app sign out succeeds
    //

    /* Sets the graph response */
//    private void updateGraphUI(JSONObject graphResponse) {
//        TextView graphText = findViewById(R.id.graphData);
//        graphText.setText(graphResponse.toString());
//    }
<<<<<<< HEAD
=======
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    /* Set the UI for successful token acquisition data */
    private void updateSuccessUI(){
        dialog.hide();
        Intent I;
        Log.d(TAG,firstTimeUser + " ");
        if(!firstTimeUser){
            I = new Intent(getApplicationContext(),Student_Dashboard.class);
            I.putExtra("user",user);
        }else{
<<<<<<< HEAD
            I = new Intent(getApplicationContext(),Student_Profile.class);
=======
            I = new Intent(getApplicationContext(),Student_Register.class);
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
            I.putExtra("fullName",FullName);
            I.putExtra("Webmail",WebmailID);
            I.putExtra("rollNo",RollNo);
            I.putExtra("programme",Programme);
        }
        startActivity(I);

    }

    /* Set the UI for signed out account */
//    private void updateSignedOutUI() {
//        callGraphButton.setVisibility(View.VISIBLE);
//        signOutButton.setVisibility(View.INVISIBLE);
//        findViewById(R.id.welcome).setVisibility(View.INVISIBLE);
//        findViewById(R.id.graphData).setVisibility(View.INVISIBLE);
//        ((TextView) findViewById(R.id.graphData)).setText("No Data");
//    }

    //
    // App callbacks for MSAL
    // ======================
    // getActivity() - returns activity so we can acquireToken within a callback
    // getAuthSilentCallback() - callback defined to handle acquireTokenSilent() case
    // getAuthInteractiveCallback() - callback defined to handle acquireToken() case
    //

    public Activity getActivity() {
        return this;
    }

    /* Callback used in for silent acquireToken calls.
     * Looks if tokens are in the cache (refreshes if necessary and if we don't forceRefresh)
     * else errors that we need to do an interactive request.
     */
    private AuthenticationCallback getAuthSilentCallback() {
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(AuthenticationResult authenticationResult) {
                /* Successfully got a token, call graph now */
                Log.d(TAG, "Successfully authenticated");

                /* Store the authResult */
                authResult = authenticationResult;

                /* call graph */
                callGraphAPI();

//                /* update the UI to post call graph state */
//                updateSuccessUI();
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                dialog.hide();
                Log.d(TAG, "Authentication failed: " + exception.toString());

                if (exception instanceof MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception instanceof MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                } else if (exception instanceof MsalUiRequiredException) {
                    /* Tokens expired or no session, retry with interactive */
                }
            }

            @Override
            public void onCancel() {
                /* User canceled the authentication */
                dialog.hide();
                Log.d(TAG, "User cancelled login.");
            }
        };
    }

    /* Callback used for interactive request.  If succeeds we use the access
     * token to call the Microsoft Graph. Does not check cache
     */
    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(AuthenticationResult authenticationResult) {
                /* Successfully got a token, call graph now */
                Log.d(TAG, "Successfully authenticated");
                Log.d(TAG, "ID Token: " + authenticationResult.getIdToken());

                /* Store the auth result */
                authResult = authenticationResult;

                /* call graph */
                callGraphAPI();
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                dialog.hide();
                Log.d(TAG, "Authentication failed: " + exception.toString());

                if (exception instanceof MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception instanceof MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                }
            }

            @Override
            public void onCancel() {
                dialog.hide();
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.");
            }
        };
    }
}