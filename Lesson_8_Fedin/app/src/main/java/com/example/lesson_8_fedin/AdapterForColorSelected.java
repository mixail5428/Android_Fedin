package com.example.lesson_8_fedin;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_8_fedin.localDatabase.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdapterForColorSelected extends RecyclerView.Adapter<AdapterForColorSelected.HolderColor> {

    ArrayList<Integer> colors;
    Note note;
    OnclickColorSelected onclickColorSelected;

    public interface OnclickColorSelected {
        void closeAlertDialog();
    }

    public AdapterForColorSelected(Note note, ArrayList<Integer> colors,
                                   OnclickColorSelected onclickColorSelected) {
        super();
        this.note = note;
        this.colors = colors;
        this.onclickColorSelected = onclickColorSelected;
    }

    @NonNull
    @Override
    public HolderColor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderColor(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.holder_selected_color, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderColor holder, int position) {
        holder.bind(note, colors.get(position), onclickColorSelected);
    }

    @Override
    public int getItemCount() {
        if (colors == null)
            return 0;
        return colors.size();
    }

    public static class HolderColor extends RecyclerView.ViewHolder {
        View layout;
        FloatingActionButton fab;

        public HolderColor(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            fab = itemView.findViewById(R.id.floating_action_button);
        }

        public void bind(Note note, int color, OnclickColorSelected onclickColorSelected) {

            fab.setOnClickListener(v -> {
                note.setColor(color);
                onclickColorSelected.closeAlertDialog();
            });

            fab.setBackgroundTintList(ColorStateList.valueOf(color));

            if (color == note.getColor()) {
                fab.setImageDrawable(layout.getContext().getResources()
                        .getDrawable(R.drawable.ic_check_white_24dp));
            }

            if (note.getColor() == Note.DEFAULT_COLOR && color == layout.getResources()
                    .getColor(R.color.white))
                fab.setImageDrawable(layout.getContext().getResources()
                .getDrawable(R.drawable.ic_check_black_24dp));
        }


    }

}

