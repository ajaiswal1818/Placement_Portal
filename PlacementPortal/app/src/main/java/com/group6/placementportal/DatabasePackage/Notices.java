package com.group6.placementportal.DatabasePackage;

public class Notices {

    private String topic;
    private String content;
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

        this.topic = topic;
        this.content = content;
    }

    public String getTopic(){return topic;}

    public void setTopic(String topics){ topic = topics; }

    public String getContent(){return content;}

    public void setContent( String contents) { content = contents; }
}
