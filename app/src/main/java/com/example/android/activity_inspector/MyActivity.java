package com.example.android.activity_inspector;

import android.location.Location;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class MyActivity implements Serializable {

    private String activityName;
    private Date startTime;
    private Date endTime;
    private String startLocation;
    private String endLocation;
    private Double speed;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

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

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }
}
