package com.example.android.activity_inspector;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class MyActivity implements Serializable {

    private String activityName;
    private Date startTime;
    private Date endTime;

    public MyActivity(String s, Date t) {
        activityName = s;
        startTime = t;
    }

    @Override
    public String toString() {
        return "Activitiy = " + activityName + "   " + "Start Time = " + startTime.toString();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
