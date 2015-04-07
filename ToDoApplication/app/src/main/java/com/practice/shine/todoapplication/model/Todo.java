package com.practice.shine.todoapplication.model;

import java.util.Date;

/**
 * Created by Vinod Akkepalli on 01/03/15.
 */
public class Todo {
    int id;
    String note;
    int status;
    Date created_at;

    public Todo(){}

    public Todo(String note, Date created_at){
        this.note = note;
        this.created_at = created_at;
    }

    public Todo(String note, Date created_at, int status){
        this.note = note;
        this.created_at = created_at;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
