package com.example.mypc.truyenoffline.entity;

import java.io.Serializable;

public class Total implements Serializable {

    public Total() {

    }
    public Total(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int id;
    private String title;
}
