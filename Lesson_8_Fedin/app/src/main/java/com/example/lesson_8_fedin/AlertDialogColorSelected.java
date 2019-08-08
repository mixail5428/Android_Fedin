package com.example.lesson_8_fedin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_8_fedin.localDatabase.Note;

import java.util.ArrayList;

public class AlertDialogColorSelected extends AlertDialog.Builder {


    private RecyclerView recyclerView;

    public AlertDialogColorSelected(Context context, Note note, ArrayList<Integer> colors,
                                    AdapterForColorSelected.OnclickColorSelected onclickColorSelected) {
        super(context);
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_for_selection_color, null);

        this.setView(view).setNegativeButton(context.getString(R.string.cansel), (dialog, which) ->
                dialog.cancel());
        recyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        AdapterForColorSelected adapter = new AdapterForColorSelected(note, colors, onclickColorSelected);
        recyclerView.setAdapter(adapter);
    }
}
