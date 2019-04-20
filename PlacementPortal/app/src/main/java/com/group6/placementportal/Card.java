package com.group6.placementportal;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.Button;

public class Card{

    private String line1;
    private String line2;
<<<<<<< HEAD

    public Card(String line1, String line2) {

        this.line1 = line1;
        this.line2 = line2;
    }

=======
    private String line3;

    public Card(String line1, String line2,String line3) {

        this.line1 = line1;
        this.line2 = line2;
        this.line3=line3;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }


}