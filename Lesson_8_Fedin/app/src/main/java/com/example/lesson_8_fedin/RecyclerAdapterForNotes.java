package com.example.lesson_8_fedin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_8_fedin.localDatabase.Note;

import java.util.List;

public class RecyclerAdapterForNotes extends RecyclerView.Adapter<RecyclerAdapterForNotes.NoteHolder> {
    private List<Note> notes;
    private OnClickRecyclerElement onClickRecyclerElement;

    public interface OnClickRecyclerElement {
        void onClick(Note note);

        void LongClick(Note note);
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
        CardView cardView;
        View layout;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDescription = itemView.findViewById(R.id.description);
            layout = itemView.findViewById(R.id.layout);
        }

        private int getColor(int color) {
            return cardView.getResources().getColor(color);
        }

        void bindNoteHolder(Note note, OnClickRecyclerElement onClickRecyclerElement) {

            if (note.getColor() == Note.DEFAULT_COLOR)
                note.setColor(getColor(R.color.white));

            cardView.setCardBackgroundColor(note.getColor());

            if (note.getColor() == getColor(R.color.white)) {
                textViewTitle.setTextColor(getColor(R.color.black_87));
                textViewDescription.setTextColor(getColor(R.color.black_87));
            } else {
                textViewTitle.setTextColor(getColor(R.color.white));
                textViewDescription.setTextColor(getColor(R.color.white));
            }

            if (note.getTitle().isEmpty()) {
                textViewTitle.setVisibility(View.GONE);
            } else {
                textViewTitle.setVisibility(View.VISIBLE);
                textViewTitle.setText(note.getTitle());
            }

            textViewDescription.setText(note.getDescription());

            layout.setOnClickListener(v -> {
                if (onClickRecyclerElement != null)
                    onClickRecyclerElement.onClick(note);
            });

            layout.setOnLongClickListener(v -> {
                if (onClickRecyclerElement != null) {
                    onClickRecyclerElement.LongClick(note);
                    return true;
                }
                return false;
            });
        }
    }
}
