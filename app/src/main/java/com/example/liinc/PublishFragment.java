package com.example.liinc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublishFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublishFragment newInstance(String param1, String param2) {
        PublishFragment fragment = new PublishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish, container, false);

        ImageButton dateButton = view.findViewById(R.id.ib_date);
        TextView dateTextView = view.findViewById(R.id.tv_date);
        dateButton.setTag(dateTextView); // Store the reference to the dateTextView in the dateButton
        Button findCopassengerButton= view.findViewById(R.id.findcopassengerbutton);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        findCopassengerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the EditText fields
                String goingFrom = ((EditText) view.findViewById(R.id.pub_goingfrom)).getText().toString();
                String goingTo = ((EditText) view.findViewById(R.id.pub_goingto)).getText().toString();
                String date = ((TextView) view.findViewById(R.id.tv_date)).getText().toString();
                String time = ((TextView) view.findViewById(R.id.tv_time)).getText().toString();
                int numOfPassengers = Integer.parseInt(((EditText) view.findViewById(R.id.pub_numofpassengers)).getText().toString());
                String vehicleName = ((EditText) view.findViewById(R.id.pub_vehiclename)).getText().toString();

                // Create a new document reference in the Firestore collection
                DocumentReference docRef = db.collection("rides").document();

                // Set the values for the fields in the document
                docRef.set(new Ride(goingFrom, goingTo, date, time, numOfPassengers, vehicleName));

                Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                                TextView dateTextView = (TextView) v.getTag(); // Get the reference to the dateTextView from the dateButton
                                dateTextView.setText(date);
                            }
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });

        ImageButton timeButton = view.findViewById(R.id.ib_time);
        TextView timeTextView = view.findViewById(R.id.tv_time);
        timeButton.setTag(timeTextView); // Store the reference to the timeTextView in the timeButton

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String time = hourOfDay + ":" + minute;
                                TextView timeTextView = (TextView) v.getTag(); // Get the reference to the timeTextView from the timeButton
                                timeTextView.setText(time);
                            }
                        },
                        hour, minute, false
                );

                timePickerDialog.show();
            }
        });

        return view;
    }
}