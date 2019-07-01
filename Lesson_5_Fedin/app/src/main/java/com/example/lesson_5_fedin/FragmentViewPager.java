package com.example.lesson_5_fedin;

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

public class FragmentViewPager extends Fragment {

    public static String DRAWABLE = "DRAWABLE";
    private String drawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            drawable = arguments.getString(FragmentViewPager.DRAWABLE);

            ImageView imageView = view.findViewById(R.id.imageView);

            Glide.with(view.getContext())
                    .load(drawable)
                    .placeholder(R.drawable.img)
                    .centerCrop()
                    .into(imageView); //заполнил ImageView картинкой

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(view.findViewById(R.id.imageView), "Нажата какая то картинка"
                            , Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    FragmentViewPager() {

    }
}
