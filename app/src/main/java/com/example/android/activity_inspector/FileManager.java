package com.example.android.activity_inspector;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class FileManager {

    private static final String TAG = "FileManager";
    private static final String filename = "activities";

    public static void writeFile(MyActivity myActivity,Context context) {

        ArrayList<MyActivity> list = new ArrayList<MyActivity>();
        MyActivity tmp;
        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            list = (ArrayList<MyActivity>) is.readObject();
            is.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, list.toString());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(100);
        myActivity.setEndTime(c.getTime());
        if (list.size() > 0) {
            tmp = list.get(list.size() - 1);
            tmp.setEndTime(Calendar.getInstance().getTime());
            list.remove(list.size() - 1);
            long time1 = tmp.getStartTime().getTime();
            long time2 = tmp.getEndTime().getTime();

            long time = time2 - time1;

            Location l = SpeedActivity.getCurrentLocation();
            if (l != null) {
                Double lat = l.getLatitude();
                Double lon = l.getLongitude();
                tmp.setEndLocation(String.valueOf(lat)+ "," + String.valueOf(lon));
            }



            if (time >1000)
                list.add(tmp);


        }



        list.add(myActivity);

        try {

            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(list);
            fos.flush();
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<MyActivity> readFile(Context context) {

        ArrayList<MyActivity> list = new ArrayList<MyActivity>();

        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            list = (ArrayList<MyActivity>) is.readObject();
            is.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void clearList(Context context) {
        ArrayList<MyActivity> list = new ArrayList<MyActivity>();
        MyActivity tmp;
        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            list = (ArrayList<MyActivity>) is.readObject();
            is.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        list.clear();
        try {

            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(list);
            fos.flush();
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
