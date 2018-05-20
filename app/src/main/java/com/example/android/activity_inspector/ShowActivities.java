package com.example.android.activity_inspector;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import java.io.IOException;
import java.util.ArrayList;

public class ShowActivities extends AppCompatActivity implements ActivityAdapter.ItemClickListener {

    private ActivityAdapter adapter;
    private ArrayList<MyActivity> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activities);

        activities = FileManager.readFile( this);
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.show_activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActivityAdapter(this, activities);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position)
//                + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}