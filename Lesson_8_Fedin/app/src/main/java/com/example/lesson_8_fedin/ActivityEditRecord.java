package com.example.lesson_8_fedin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.lesson_8_fedin.localDatabase.Note;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityEditRecord extends AppCompatActivity {
    public static final String EXTRA_NOTE = "EXTRA_NOTE";
    Note note;
    TextInputEditText editTextTitle;
    TextInputEditText editTextDescription;


    public static Intent createStartIntent(Context context, Note note) {
        Intent intent = new Intent(context, ActivityEditRecord.class);
        intent.putExtra(ActivityEditRecord.EXTRA_NOTE, note);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        createToolbar();
        createEditor();
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_edit_record);
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.menu_item_change_color:
                    return true;
            }

            return false;
        });
        toolbar.setNavigationOnClickListener(v -> saveNoteAndCloseActivity());
    }

    private void createEditor() {
        note = getIntent().getParcelableExtra(EXTRA_NOTE);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextTitle.setText(note.getTitle());
        editTextDescription.setText(note.getDescription());
    }

    private void saveNoteAndCloseActivity() {
        note.setTitle(editTextTitle.getText().toString());
        note.setDescription(editTextDescription.getText().toString());
        Intent intent = new Intent();
        intent.putExtra(ActivityEditRecord.EXTRA_NOTE, note);
        setResult(RESULT_OK, intent);
        finish();
    }

}
