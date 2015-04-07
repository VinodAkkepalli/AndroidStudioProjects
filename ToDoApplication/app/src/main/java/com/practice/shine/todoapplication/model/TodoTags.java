package com.practice.shine.todoapplication.model;

/**
 * Created by Vinod Akkepalli on 01/04/15.
 */
public class TodoTags {
    int id;
    int todo_id;
    int tag_id;

    public TodoTags(){}

    public TodoTags(int todo_id, int tag_id){
        this.todo_id = todo_id;
        this.tag_id = tag_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
