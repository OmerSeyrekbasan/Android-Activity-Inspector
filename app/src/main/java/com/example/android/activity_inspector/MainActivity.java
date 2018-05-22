package com.example.android.activity_inspector;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private Accelerometer mAcc;
    private Button mReadButton;
    private Button mLogButton;
    private Button mClearButton;
    private Button mStopButton;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent i = new Intent(MainActivity.this, MyService.class);
        startService(i);

        mReadButton = (Button) findViewById(R.id.readContents);
        mLogButton = (Button) findViewById(R.id.showSpeed);
        mClearButton = (Button) findViewById(R.id.clearList);
        mStopButton = (Button) findViewById(R.id.stopService);

        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SpeedActivity.class);
                startService(i);
            }
        });

        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowActivities.class);
                startActivity(i);
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager.clearList(MainActivity.this);
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                stopService(i);
            }
        });

    }

}
