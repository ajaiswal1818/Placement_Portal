package com.group6.placementportal.DatabasePackage;

public class Notices {

<<<<<<< HEAD
    private String Topic;
    private String Content;
=======
    private String topic;
    private String content;
    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6

    public Notices(){

    }

<<<<<<< HEAD
    public Notices(String id,String topic,String content){

        this.Topic = topic;
        this.Content = content;
    }

    public String getTopic(){return Topic;}

    public void setTopic(String topic){ Topic = topic; }

    public String getContent(){return Content;}

    public void setContent( String content) { Content = content; }
=======
    public Notices(String topics,String contents){

        this.topic = topics;
        this.content = contents;
    }

    public String getTopic(){return topic;}

    public void setTopic(String topics){ topic = topics; }

    public String getContent(){return content;}

    public void setContent( String contents) { content = contents; }
>>>>>>> e163f38a9195dbe1e94cd8f150a6c0cb43dd67f6
}
