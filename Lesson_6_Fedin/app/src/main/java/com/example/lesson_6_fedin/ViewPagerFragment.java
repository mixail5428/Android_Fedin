package com.example.lesson_6_fedin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class ViewPagerFragment extends Fragment {
    private static String DRAWABLE = "drawable";
    private static String TEXT = "TEXT";

    private String drawable;
    private String text;
    ImageView imageView;
    TextView textView;
    View layout;



    public static ViewPagerFragment newInstance(DataDrawable item){
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle argument = new Bundle();
        argument.putString(DRAWABLE, item.getDrawable());
        argument.putString(TEXT, item.getText());
        fragment.setArguments(argument);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            drawable = getArguments().getString(DRAWABLE);
            text = getArguments().getString(TEXT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_for_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);
        layout = view;
        billing(drawable, text);
    }

    private void billing(String _drawable, final String _text){
        Glide
                .with(imageView)
                .load(_drawable)
                .placeholder(R.drawable.ic_alert)
                .centerCrop()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, _text, Snackbar.LENGTH_SHORT ).show();
            }
        });

        textView.setText(text);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
