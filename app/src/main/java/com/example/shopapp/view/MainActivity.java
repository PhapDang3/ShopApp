package com.example.shopapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.shopapp.R;
import com.example.shopapp.fragment.HomeFragment;
import com.example.shopapp.fragment.NotificationsFragment;
import com.example.shopapp.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    // TODO: Handle home action
                    if (savedInstanceState == null) {
                        // Khởi tạo FragmentManager và FragmentTransaction
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Thay thế FrameLayout bằng HomeFragment
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.fragment_container, homeFragment);

                        // Hoàn thành giao dịch
                        fragmentTransaction.commit();
                    }
                    return true;
                } else if (itemId == R.id.nav_notifications) {
                    // TODO: Handle search action
                    if (savedInstanceState == null) {
//                        // Khởi tạo FragmentManager và FragmentTransaction
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        // Thay thế FrameLayout
//                        NotificationsFragment notificationsFragment = new NotificationsFragment();
//                        fragmentTransaction.replace(R.id.fragment_container, notificationsFragment);
//
//                        // Hoàn thành giao dịch
//                        fragmentTransaction.commit();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new NotificationsFragment())
                                .commit();
                    }
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    // TODO: Handle profile action
                    if (savedInstanceState == null) {
                        // Khởi tạo FragmentManager và FragmentTransaction
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Thay thế FrameLayout
                        ProfileFragment profileFragment = new ProfileFragment();
                        fragmentTransaction.replace(R.id.fragment_container, profileFragment);

                        // Hoàn thành giao dịch
                        fragmentTransaction.commit();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

    }
}