package com.example.liinc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchRideFrom;
    private EditText searchRideTo;
    private Button searchRideButton;
    private FirebaseFirestore firestore;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchRideFrom = view.findViewById(R.id.search_ride_from);
        searchRideTo = view.findViewById(R.id.search_ride_to);
        searchRideButton = view.findViewById(R.id.search_ride_button);

        searchRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = searchRideFrom.getText().toString();
                String to = searchRideTo.getText().toString();

                // Firestore query to get matching rides
                firestore.collection("rides")
                        .whereEqualTo("mGoingFrom", from)
                        .whereEqualTo("mGoingTo", to)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    List<Ride> rideList = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : querySnapshot) {
                                        Ride ride = document.toObject(Ride.class);
                                        rideList.add(ride);
                                    }

                                    // Start new activity to show matching rides
                                    // Start new activity to show matching rides
                                    Intent intent = new Intent(getActivity(), MatchingRidesActivity.class);
                                    intent.putParcelableArrayListExtra("rideList", (ArrayList<? extends Parcelable>) rideList);
                                    startActivity(intent);
                                } else {
                                    Log.d("Error", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });

        return view;
    }
}