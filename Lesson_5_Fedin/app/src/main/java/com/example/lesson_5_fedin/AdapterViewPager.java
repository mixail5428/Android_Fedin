package com.example.lesson_5_fedin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterViewPager extends FragmentPagerAdapter {

    ArrayList<String> drawable;

    public AdapterViewPager(FragmentManager fm, ArrayList<String> drawable) {
        super(fm);
        this.drawable = drawable;
    }

    @Override
    public int getCount() {
        return drawable.size();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle arguments = new Bundle();
        arguments.putString(FragmentViewPager.DRAWABLE, drawable.get(position));
        FragmentViewPager fragmentViewPager = new FragmentViewPager();
        fragmentViewPager.setArguments(arguments);
        return fragmentViewPager;
    }
}
