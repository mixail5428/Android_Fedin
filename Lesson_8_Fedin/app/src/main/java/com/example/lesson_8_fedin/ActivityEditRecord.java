package com.example.lesson_8_fedin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lesson_8_fedin.localDatabase.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ActivityEditRecord extends AppCompatActivity {
    public static final String EXTRA_NOTE = "EXTRA_NOTE";
    Note note;
    TextInputEditText editTextTitle;
    TextInputEditText editTextDescription;
    AlertDialog alertDialog;

    private ArrayList<Integer> arrayListColor;


    public static Intent createStartIntent(Context context, Note note) {
        Intent intent = new Intent(context, ActivityEditRecord.class);
        intent.putExtra(ActivityEditRecord.EXTRA_NOTE, note);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        createArrayListColor();
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
                    openDialogColorSelected();
                    return true;
            }

            return false;
        });
        toolbar.setNavigationOnClickListener(v -> saveNoteAndCloseActivity());
    }

    private void openDialogColorSelected(){
        AlertDialogColorSelected myCustomBuilder = new AlertDialogColorSelected(this,
                note, arrayListColor, () -> alertDialog.dismiss());
        alertDialog = myCustomBuilder.create();
        alertDialog.setOnShowListener(dialog -> {
            Button negativButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            negativButton.setBackground(null);
            negativButton.setTextColor(getResources().getColor(R.color.teal_two));
        });
        alertDialog.show();
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

    private void createArrayListColor(){
        arrayListColor = new ArrayList<>();
        arrayListColor.add(getResources().getColor(R.color.lipstick));
        arrayListColor.add(getResources().getColor(R.color.lipstick_two));
        arrayListColor.add(getResources().getColor(R.color.barney));
        arrayListColor.add(getResources().getColor(R.color.bluey_purple));
        arrayListColor.add(getResources().getColor(R.color.lightish_blue));
        arrayListColor.add(getResources().getColor(R.color.azure));
        arrayListColor.add(getResources().getColor(R.color.turquoise_blue));
        arrayListColor.add(getResources().getColor(R.color.teal));
        arrayListColor.add(getResources().getColor(R.color.booger));
        arrayListColor.add(getResources().getColor(R.color.sickly_yellow));
        arrayListColor.add(getResources().getColor(R.color.sunshine_yellow));
        arrayListColor.add(getResources().getColor(R.color.marigold));
        arrayListColor.add(getResources().getColor(R.color.orange_red));
        arrayListColor.add(getResources().getColor(R.color.warm_grey_five));
        arrayListColor.add(getResources().getColor(R.color.blue_grey));
        arrayListColor.add(getResources().getColor(R.color.white));
    }
}
