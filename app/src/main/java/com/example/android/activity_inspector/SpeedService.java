package com.example.android.activity_inspector;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SpeedService extends Service {
    public SpeedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
