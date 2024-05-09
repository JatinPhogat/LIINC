package com.example.liinc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchingRidesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatchingRidesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_rides);

        recyclerView = findViewById(R.id.recycler_view23);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        ArrayList<Parcelable> parcelableArrayList = intent.getParcelableArrayListExtra("rideList");
        ArrayList<Ride> rideArrayList = new ArrayList<>();

        for (Parcelable parcelable : parcelableArrayList) {
            Ride ride = (Ride) parcelable;
            rideArrayList.add(ride);
        }

        adapter = new MatchingRidesAdapter(this, rideArrayList);
        recyclerView.setAdapter(adapter);
    }
}