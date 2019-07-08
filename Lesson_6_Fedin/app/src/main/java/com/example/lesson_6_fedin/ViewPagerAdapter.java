package com.example.lesson_6_fedin;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<DataDrawable> dataDrawables;
    public ViewPagerAdapter(FragmentManager fm, ArrayList<DataDrawable> dataDrawables ) {
        super(fm);
        this.dataDrawables = dataDrawables;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(dataDrawables.get(position));
    }

    @Override
    public int getCount() {
        return dataDrawables.size();
    }
}
