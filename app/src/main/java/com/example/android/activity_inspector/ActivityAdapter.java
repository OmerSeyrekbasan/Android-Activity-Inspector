package com.example.android.activity_inspector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private ArrayList<MyActivity> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ActivityAdapter(Context context, ArrayList<MyActivity> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyActivity act = mData.get(position);


        holder.mActivityName.setText(act.getActivityName());
        holder.mActivityStart.setText(act.getStartTime().toString());
        holder.mActivityEnd.setText(act.getEndTime().toString());
        holder.mActivityId.setText(String.valueOf(position + 1));

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        Date start = act.getStartTime();
        Date end = act.getEndTime();

        long time1 = start.getTime();
        long time2 = end.getTime();

        long time = time2 - time1;

        if (time < -100) {
            holder.mActivityDur.setText("In Progress");
            holder.mActivityEnd.setText("---------");
        } else {
            String s = (new SimpleDateFormat("mm:ss:SSS")).format(new Date(time));
            holder.mActivityDur.setText(s);
        }






    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mActivityName;
        TextView mActivityStart;
        TextView mActivityEnd;
        TextView mActivityId;
        TextView mActivityDur;

        ViewHolder(View itemView) {
            super(itemView);
            mActivityName = itemView.findViewById(R.id.activity_name);
            mActivityStart = itemView.findViewById(R.id.start_time);
            mActivityEnd = itemView.findViewById(R.id.end_time);
            mActivityId = itemView.findViewById(R.id.activity_number);
            mActivityDur = itemView.findViewById(R.id.activity_duration);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public MyActivity getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}