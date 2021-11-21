package com.example.fakecallingapp;

import android.text.format.Time;

public class CallLog {
    private String name;
    private String contact;
    private String time;
    private String date;

    public CallLog(String name, String contact, String time, String date) {
        this.name = name;
        this.contact = contact;
        this.time = time;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
