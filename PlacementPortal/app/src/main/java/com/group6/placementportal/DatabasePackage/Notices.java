package com.group6.placementportal.DatabasePackage;

public class Notices {

    private String Topic;
    private String Content;
    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Notices(){

    }

    public Notices(String topic,String content){

        this.Topic = topic;
        this.Content = content;
    }

    public String getTopic(){return Topic;}

    public void setTopic(String topic){ Topic = topic; }

    public String getContent(){return Content;}

    public void setContent( String content) { Content = content; }
}
