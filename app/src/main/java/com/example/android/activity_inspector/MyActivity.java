package com.example.android.activity_inspector;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class MyActivity implements Serializable {

    private String activityName;
    private Date startTime;

    public MyActivity(String s, Date t) {
        activityName = s;
        startTime = t;
    }

    @Override
    public String toString() {
        return "Activitiy = " + activityName + "   " + "Start Time = " + startTime.toString();
    }
}
