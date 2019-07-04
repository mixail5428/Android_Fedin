package com.example.lesson_6_fedin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecyclerViewCounter
        extends RecyclerView.Adapter<AdapterRecyclerViewCounter.HolderCounter> {

    static public class HolderCounter extends RecyclerView.ViewHolder{
        public HolderCounter(@NonNull View itemView) {
            super(itemView);
        }
    }

    public AdapterRecyclerViewCounter(){

    }

    @NonNull
    @Override
    public HolderCounter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderCounter(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_for_counter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCounter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
