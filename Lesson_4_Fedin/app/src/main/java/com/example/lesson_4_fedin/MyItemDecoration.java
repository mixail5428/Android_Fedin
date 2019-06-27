package com.example.lesson_4_fedin;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int spase;

    public MyItemDecoration(int spase){


        this.spase = spase;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = spase/2;
        outRect.top = spase/2;
        outRect.left = spase/2;
        outRect.right = spase/2;

    }
}
