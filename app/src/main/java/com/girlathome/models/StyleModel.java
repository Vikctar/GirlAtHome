package com.girlathome.models;

/**
 * Created by steve on 5/16/17.
 */

public class StyleModel {
    private int id;
    private String name;
    private String date;
    private String time;
    private String dateimeT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
