package com.group6.placementportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class view_pdf extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pdf);

        WebView pdf_view_= findViewById(R.id.pdf_view_window);
        final ProgressBar progressBar=findViewById(R.id.pdf_progress);
        progressBar.setVisibility(View.VISIBLE);

        String url =getIntent().getSerializableExtra("url").toString();
        String final_url="http://drive.google.com/viewerng/viewer?embedded=true&url="+url;

        pdf_view_.getSettings().setJavaScriptEnabled(true);
        pdf_view_.getSettings().setBuiltInZoomControls(true);

        pdf_view_.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view,int newProgress){
                super.onProgressChanged(view,newProgress);
                //getSupportActionBar().setTitle("Loading...");
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                    //Toast.makeText(view_pdf.this,"")
                    //getSupportActionBar().setTitle(R.string.app_name);
                }
            }
        });
        pdf_view_.loadUrl(final_url);
    }
}
