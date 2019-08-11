package com.example.lesson_8_fedin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lesson_8_fedin.localDatabase.AppDatabase;
import com.example.lesson_8_fedin.localDatabase.LocalDatabase;
import com.example.lesson_8_fedin.localDatabase.Note;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityEditRecord extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "EXTRA_NOTE_ID";

    public static final long DEFAULT_ID = -1;
    long idNote;
    Note note;
    NestedScrollView nestedScrollView;
    EditText editTextTitle;
    EditText editTextDescription;
    AlertDialog alertDialog;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AppDatabase appDatabase;
    ContentLoadingProgressBar progressBar;
    MenuItem menuItemColorSelected;

    private ArrayList<Integer> arrayListColor;


    public static Intent createStartIntent(Context context, long idNote) {
        Intent intent = new Intent(context, ActivityEditRecord.class);
        intent.putExtra(ActivityEditRecord.EXTRA_NOTE_ID, idNote);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        idNote = getIntent().getLongExtra(EXTRA_NOTE_ID, DEFAULT_ID);
        createArrayListColor();
        createToolbar();
        createEditor();
        createDatabase();
    }

    @Override
    public void onBackPressed() {
        updateNoteAndCloseActivity();
    }

    private void createDatabase() {
        Disposable disposable = Single.fromCallable(() -> LocalDatabase.getInstance().getAppDatabase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    appDatabase = object;
                    createSubscriptionUpdateDatabase();
                });

        compositeDisposable.add(disposable);
    }

    private void createSubscriptionUpdateDatabase() {
        Disposable disposable = appDatabase.noteDao().getNoteById(idNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshActivity);

        compositeDisposable.add(disposable);
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
        menuItemColorSelected = toolbar.getMenu().findItem(R.id.menu_item_change_color);
        menuItemColorSelected.setEnabled(false);
        toolbar.setNavigationOnClickListener(v -> updateNoteAndCloseActivity());
    }

    private void openDialogColorSelected() {
        AlertDialogColorSelected myCustomBuilder = new AlertDialogColorSelected(this,
                note, arrayListColor, () -> {
            alertDialog.dismiss();
            updateNote();
        });
        alertDialog = myCustomBuilder.create();
        alertDialog.setOnShowListener(dialog -> {
            Button negativButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            negativButton.setBackground(null);
            negativButton.setTextColor(getResources().getColor(R.color.teal_two));
        });
        alertDialog.show();
    }

    private void createEditor() {
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        progressBar = findViewById(R.id.progressbar);
        nestedScrollView = findViewById(R.id.nestedScrollView);
    }

    private void lockEditor(){
        editTextTitle.setVisibility(View.INVISIBLE);
        editTextDescription.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        menuItemColorSelected.setEnabled(false);
    }

    private void unlockEditor(){
        editTextTitle.setVisibility(View.VISIBLE);
        editTextDescription.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        menuItemColorSelected.setEnabled(true);
    }



    private void refreshActivity(Note note) {
        this.note = note;
        unlockEditor();



        if(note.getColor() == Note.DEFAULT_COLOR ||
                note.getColor() == getResources().getColor(R.color.white)){
            editTextDescription.setTextColor(getResources().getColor(R.color.black_54));
            editTextDescription.setHintTextColor(getResources().getColor(R.color.black_54));
            editTextTitle.setTextColor(getResources().getColor(R.color.warm_grey_four));
            editTextTitle.setHintTextColor(getResources().getColor(R.color.warm_grey_four));
        }
        else {
            editTextDescription.setTextColor(getResources().getColor(R.color.white));
            editTextDescription.setHintTextColor(getResources().getColor(R.color.white));
            editTextTitle.setTextColor(getResources().getColor(R.color.white));
            editTextTitle.setHintTextColor(getResources().getColor(R.color.white));
        }

        editTextTitle.setText(note.getTitle());
        editTextDescription.setText(note.getDescription());
        nestedScrollView.setBackgroundColor((note.getColor() == Note.DEFAULT_COLOR) ?
                getResources().getColor(R.color.white) : note.getColor());
    }

    private void updateNote() {

        lockEditor();

        note.setTitle(editTextTitle.getText().toString());
        note.setDescription(editTextDescription.getText().toString());

        Disposable disposable = Completable.fromAction(() -> appDatabase.noteDao().insert(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    private void updateNoteAndCloseActivity() {
        updateNote();
        finish();
    }

    private void createArrayListColor() {
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

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
