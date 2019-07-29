package com.example.lesson_6_fedin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class FragmentThree extends Fragment {

    public static final String DATA_DRAWABLES = "data drawables";

    static final int VISIBLE = 1;
    static final int INVISIBLE = 0;


    private ArrayList<DataDrawable> dataDrawables;
    private int visibleViewPager = 1;
    ViewPager viewPager;
    CirclePageIndicator indicator;
    AppCompatButton button;

    public static FragmentThree newInstance(ArrayList<DataDrawable> dataDrawables) {
        FragmentThree fragment = new FragmentThree();
        Bundle argument = new Bundle();
        argument.putParcelableArrayList(DATA_DRAWABLES, dataDrawables);
        fragment.setArguments(argument);
        return fragment;
    }

    public FragmentThree() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            dataDrawables = getArguments().getParcelableArrayList(DATA_DRAWABLES);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), dataDrawables);
        viewPager.setAdapter(adapter);
        indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (visibleViewPager) {
                    case VISIBLE:
                        visibleViewPager(View.GONE, INVISIBLE, getResources().getString(R.string.show_banner));
                        break;
                    case INVISIBLE:
                        visibleViewPager(View.VISIBLE, VISIBLE, getResources().getString(R.string.hide_banner));
                }
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Item three");
    }

    private void visibleViewPager(int viewVisible, int visibleViewPager, String textButton) {
        viewPager.setVisibility(viewVisible);
        indicator.setVisibility(viewVisible);
        this.visibleViewPager = visibleViewPager;
        button.setText(textButton);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentItem", viewPager.getCurrentItem());
    }


}
