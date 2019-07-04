package com.example.lesson_6_fedin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    private static final String PARAM_1 = "param_1";

    private String textForView;

    public FragmentOne() {
        super();
    }

    public static FragmentOne newInstance(String str){
        FragmentOne fragment = new FragmentOne();
        Bundle argument = new Bundle();
        argument.putString(PARAM_1, str);

        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            textForView = getArguments().getString(PARAM_1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.textVIewFragmentOne);
        textView.setText(textForView);
    }
}
