package com.example.android.activity_inspector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SpeedActivity extends AppCompatActivity {

    TextView speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        speed = (TextView) findViewById(R.id.speed);



    }
}
