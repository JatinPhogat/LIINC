package com.example.liinc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MatchingRidesAdapter extends RecyclerView.Adapter<MatchingRidesAdapter.MyViewHolder> {

    Context mContext;
    private ArrayList<Ride> mRideList;

    public MatchingRidesAdapter(Context mContext, ArrayList<Ride> mRideList) {
        this.mContext = mContext;
        this.mRideList = mRideList;
    }

    @NonNull
    @Override
    public MatchingRidesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.available_ride_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchingRidesAdapter.MyViewHolder holder, int position) {
        Ride ride = mRideList.get(position);
        holder. mgoingFromRideListChanger.setText(ride.mGoingFrom);
        holder. mgoingToLocationRideListChanger.setText(ride.mGoingTo);
        holder.mdateRideListChanger.setText(ride.mDate);
        holder. mtimeRideListChanger.setText(ride.mTime);
        holder. mvehicleNameRideListChanger.setText(ride.mVehicleName);
    }

    @Override
    public int getItemCount() {
        return mRideList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mgoingFromRideListChanger,mgoingToLocationRideListChanger,mdateRideListChanger,mtimeRideListChanger,mvehicleNameRideListChanger;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mgoingFromRideListChanger = itemView.findViewById(R.id.goingFromRideListChanger);
            mgoingToLocationRideListChanger = itemView.findViewById(R.id.goingToLocationRideListChanger);
            mdateRideListChanger = itemView.findViewById(R.id.dateRideListChanger);
            mtimeRideListChanger = itemView.findViewById(R.id.timeRideListChanger);
            mvehicleNameRideListChanger = itemView.findViewById(R.id.vehicleNameRideListChanger);
        }
    }
}