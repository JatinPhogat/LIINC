package com.example.liinc;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private YourTripsFragment yourTripsFragment;
    private PublishFragment publishFragment;
    private SearchFragment searchFragment;

    private NotificationsFragment notificationsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Initialize fragments
        yourTripsFragment = new YourTripsFragment();
        publishFragment = new PublishFragment();
        searchFragment = new SearchFragment();
        notificationsFragment = new NotificationsFragment();

        // Set the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,yourTripsFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.navigation_your_trips) {
                        selectedFragment = yourTripsFragment;
                    } else if (item.getItemId() == R.id.navigation_publish) {
                        selectedFragment = publishFragment;
                    } else if (item.getItemId() == R.id.navigation_search) {
                        selectedFragment = searchFragment;
                    } else if (item.getItemId() == R.id.navigation_notifications) {
                        selectedFragment = notificationsFragment;
                    }

                    // Load the selected fragment
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        return true;
                    }
                    return false;
                }
            };
}