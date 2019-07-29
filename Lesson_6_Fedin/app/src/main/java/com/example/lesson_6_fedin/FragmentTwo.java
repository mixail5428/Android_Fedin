package com.example.lesson_6_fedin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FragmentTwo extends Fragment implements AdapterRecyclerViewCounter.AdapterClickListr {

    private static String DATA_COUNTER = "data_counter";

    ArrayList<DataCounter> dataCounters;
    RecyclerView recyclerView;

    public FragmentTwo() {

    }

    public static FragmentTwo newInstance(ArrayList<DataCounter> dataCounters) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle argument = new Bundle();
        argument.putParcelableArrayList(DATA_COUNTER, dataCounters);
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            dataCounters = getArguments().getParcelableArrayList(DATA_COUNTER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createToolbar(view);
        createRecyclerView(view);
    }

    private void createRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AdapterRecyclerViewCounter(dataCounters, this));

    }

    private void createToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Item two");
        toolbar.inflateMenu(R.menu.menu_fragment2);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.lamp:
                        Snackbar.make(getView(), "Фонарик", Snackbar.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void infoClick(int position) {
        Snackbar.make(getView(), "кнопка информации " + position + "элемента", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void optionClick(int position) {
        Snackbar.make(getView(), "кнопка настроек " + position + "элемента", Snackbar.LENGTH_LONG).show();
    }

}
