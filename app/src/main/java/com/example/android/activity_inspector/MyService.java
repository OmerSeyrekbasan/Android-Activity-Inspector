package com.example.android.activity_inspector;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    private Accelerometer mAcc;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mAcc = new Accelerometer(this);

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
