package com.example.android.activity_inspector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;


import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class Accelerometer implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAcc;
    private String TAG = "Accelerometer";
    private String current = "Empty";
    private String next = "Empty";
    private Queue<String> q;
    private Context context;



    public Accelerometer(Context c) {
        mSensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
        context = c;
        q = new LinkedList<String>();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
//            Log.d(TAG, "X Axis = " + String.valueOf(event.values[0]) + "Y Axis = " + String.valueOf(event.values[1]) +
//                    "Z Axis = " + String.valueOf(event.values[2]));
            Float sum = (event.values[0]*event.values[0]) + (event.values[1]*event.values[1])
                    + (event.values[2]*event.values[2]);
            Double magnitude =  Math.sqrt(sum);
//            Log.d(TAG, "Magnitude = " + magnitude);

//            Log.d(TAG, String.valueOf(magnitude));
            if (magnitude > 4.5) {
                activity("Running");
            } else if (magnitude > 1.5) {
                activity("Walking");
            } else {
                activity("Stationary");
            }

        }
    }

    public void activity(String type) {

//        Log.d(TAG, type);
        if (q.size() > 10)
            q.remove();
        q.add(type);
        String nextType = calcPercent();
//        Log.d(TAG, "Returned = " + nextType);
        if (!nextType.equals("NoType")) {
            if (!nextType.equals(current)) {
                MyActivity myActivity = new MyActivity(nextType, Calendar.getInstance().getTime());
                Location l = SpeedActivity.getCurrentLocation();
                if (l != null) {
                    Double lat = l.getLatitude();
                    Double lon = l.getLongitude();
                    myActivity.setStartLocation(String.valueOf(lat)+ "," + String.valueOf(lon));
                }

            Log.d("Current", current);
                current = nextType;
                FileManager.writeFile(myActivity, context);
                Log.d(TAG, nextType);
            }
        }


//       if (type == next) {
//           numNext++;
//       } else {
//           next = type;
//           numNext = 0;
//       }

//       if (numNext == 10 && current != next) {
//           MyActivity myActivity = new MyActivity(next, Calendar.getInstance().getTime());

//           Log.d("TAG", next);
//           current = next;
//           FileManager.writeFile(myActivity, context);
//       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public String calcPercent() {
        Log.d(TAG, q.toString());
        int running = 0;
        int walking = 0;
        int stat = 0;
        for (String s : q) {
//            Log.d(TAG, s);
            if(s.equals("Running")) {
                running++;
            } else if(s.equals("Walking")) {
                walking++;
            } else {
                stat++;
            }
        }

        if (running >3)
            return "Running";
        if (walking >3)
            return "Walking";
        if (stat > 3)
            return "Stationary";
        return "NoType";

    }

}
