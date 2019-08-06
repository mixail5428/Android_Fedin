package com.example.lesson_8_fedin;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_8_fedin.localDatabase.Note;

import java.util.List;
import java.util.Random;

import static com.example.lesson_8_fedin.MainActivity.MY_CODE;

public class RecyclerAdapterForNotes extends RecyclerView.Adapter<RecyclerAdapterForNotes.NoteHolder> {
    private List<Note> notes;
    private OnClickRecyclerElement onClickRecyclerElement;

    public interface OnClickRecyclerElement {
        void click(Note note);
    }


    public RecyclerAdapterForNotes(List<Note> notes, OnClickRecyclerElement onClickRecyclerElement) {
        super();
        this.notes = notes;
        this.onClickRecyclerElement = onClickRecyclerElement;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_for_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.bindNoteHolder(notes.get(position), onClickRecyclerElement);
    }

    @Override
    public int getItemCount() {
        if (notes == null)
            return 0;
        else return notes.size();
    }

    public static class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        View layout;
        View background;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDescription = itemView.findViewById(R.id.description);
            background = itemView.findViewById(R.id.background);
        }

        public void bindNoteHolder(Note note, OnClickRecyclerElement onClickRecyclerElement) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            background.setBackgroundColor(color);
            textViewTitle.setText(note.getTitle());
            textViewDescription.setText(note.getDescription());
            background.setOnClickListener(v -> {
                if (onClickRecyclerElement != null)
                    onClickRecyclerElement.click(note);
            });
        }
    }
}
