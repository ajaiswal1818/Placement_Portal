package com.group6.placementportal.DatabasePackage;

public class Notices {

    private String Topic;
    private String Content;

    public Notices(){

    }

    public Notices(String id,String topic,String content){

        this.Topic = topic;
        this.Content = content;
    }

    public String getTopic(){return Topic;}

    public void setTopic(String topic){ Topic = topic; }

    public String getContent(){return Content;}

    public void setContent( String content) { Content = content; }
}
