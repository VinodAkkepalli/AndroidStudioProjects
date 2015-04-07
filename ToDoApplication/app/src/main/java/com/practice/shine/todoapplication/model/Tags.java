package com.practice.shine.todoapplication.model;

/**
 * Created by Vinod Akkepalli on 01/03/15.
 */
public class Tags {
    int id;
    String tag_name;

    public Tags(){}

    public Tags(String tag_name){
        this.tag_name = tag_name;
    }

    public Tags(int id, String tag_name){
        this.tag_name = tag_name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
