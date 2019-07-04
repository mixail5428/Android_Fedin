package com.example.lesson_6_fedin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentTwo extends Fragment {

    public FragmentTwo() {

    }

    public static FragmentTwo newInstance() {
        FragmentTwo fragment = new FragmentTwo();
        Bundle argument = new Bundle();

        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setFocusable(true);
        recyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new AdapterRecyclerViewCounter());

    }
}
