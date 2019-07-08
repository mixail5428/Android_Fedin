package com.example.lesson_6_fedin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataCounter> dataCounters;
    ArrayList<DataDrawable> dataDrawables;

    private static String SELECTED_BOTTOM_NAVIGATION_ITEM = "selectedBottomNavigationItem";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            createDataCounters();
        } else {
            dataCounters = savedInstanceState.getParcelableArrayList("data");
        }
        createDataDrawables();
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
                                .replace(R.id.frame_layout, FragmentTwo.newInstance(dataCounters))
                                .commit();

                        return true;

                    case R.id.item_three:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout, FragmentThree.newInstance(dataDrawables))
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
        outState.putParcelableArrayList("data", dataCounters);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECTED_BOTTOM_NAVIGATION_ITEM));
        dataCounters = savedInstanceState.getParcelableArrayList("data");
    }

    private void createDataCounters() {

        SimpleDateFormat last = new SimpleDateFormat("dd.MM.yyyy");

        Date lastDate;
        Date nextDate;
        Date oldDate;
        // надеюсь что здесь проблем не будет
        try {
            lastDate = last.parse("05.07.2019");
            nextDate = last.parse("25.07.2019");
            oldDate = last.parse("18.06.2019");
            dataCounters = new ArrayList<>();
            dataCounters.add(new DataCounter("Холодная вода", R.drawable.ic_water_cold,
                    54656553, 8547, oldDate, nextDate));
            dataCounters.add(new DataCounter("Горячая вода", R.drawable.ic_water_hot,
                    54656553, 4751, 5465, oldDate, nextDate));
            dataCounters.add(new DataCounter("Электроэнергия", R.drawable.ic_electro_copy,
                    54656553, 7854, 4756, 4125,
                    lastDate, nextDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDataDrawables() {
        dataDrawables = new ArrayList<>();
        dataDrawables.add(new DataDrawable(
                "https://i1.rozetka.ua/goods/2458012/nike_888411851646_images_2458012513.jpg",
                "красные кроссовки"));
        dataDrawables.add(new DataDrawable(
                "https://i1.rozetka.ua/goods/2682131/nike_887225058890_images_2682131407._S.jpg",
                "синие кроссовки"));
        dataDrawables.add(new DataDrawable(
                "https://avatars.mds.yandex.net/get-marketpic/937819/market_ggLuW9g5PaWRIC-rDtGR6Q/orig",
                "черные кросовки"));
    }
}
