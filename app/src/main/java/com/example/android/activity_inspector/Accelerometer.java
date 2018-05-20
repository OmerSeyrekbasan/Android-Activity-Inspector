package com.example.android.activity_inspector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.io.File;
import java.sql.Time;
import java.util.Calendar;

public class Accelerometer implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAcc;
    private String TAG = "Accelerometer";
    private int walk = 0;
    private int stat = 0;
    private int run = 0;
    private String current = "Stationary";
    private String next = "Stationary";
    private int numNext = 0;
    private String filename = "activities";
    private Context context;




    public Accelerometer(Context c) {
        mSensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
        context = c;
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

            if (magnitude > 4.0) {
                activity("Running");
            } else if (magnitude > 1.0) {
                activity("Walking");
            } else activity("Stationary");

        }
    }

    public void activity(String type) {

       if (type == next) {
           numNext++;
       } else {
           next = type;
           numNext = 0;
       }

       if (numNext == 5) {
           MyActivity myActivity = new MyActivity(next, Calendar.getInstance().getTime());
           Log.d("TAG", next);
           FileManager.writeFile(filename, myActivity, context);
       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
