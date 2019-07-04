package com.example.lesson_6_fedin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static String SELECTED_BOTTOM_NAVIGATION_ITEM = "selectedBottomNavigationItem";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createBottomNavigation();


    }

    private void createBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.item_one:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, FragmentOne.newInstance("Фрагмент 1"))
                                .commit();
                        return true;

                    case R.id.item_two:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, FragmentTwo.newInstance())
                                .commit();

                        return true;

                    case R.id.item_three:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, FragmentOne.newInstance("Фрагмент 3"))
                                .commit();

                        return true;
                }
                return false;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.item_one);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_BOTTOM_NAVIGATION_ITEM, bottomNavigationView.getSelectedItemId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECTED_BOTTOM_NAVIGATION_ITEM));
    }
}
