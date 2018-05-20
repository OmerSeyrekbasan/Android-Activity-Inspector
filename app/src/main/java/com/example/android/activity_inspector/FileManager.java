package com.example.android.activity_inspector;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {

    private static final String TAG = "FileManager";

    public static void writeFile(String filename, MyActivity myActivity,Context context) {

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

        Log.d(TAG, list.toString());

        list.add(myActivity);
//        list.clear();
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

    public static ArrayList<MyActivity> readFile(String filename,Context context) {

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

}
