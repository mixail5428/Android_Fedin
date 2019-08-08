package com.example.lesson_8_fedin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson_8_fedin.localDatabase.AppDatabase;
import com.example.lesson_8_fedin.localDatabase.LocalDatabase;
import com.example.lesson_8_fedin.localDatabase.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecyclerAdapterForNotes.OnClickRecyclerElement {

    public static final int MY_CODE = 48156;

    RecyclerView recyclerView;
    AppDatabase appDatabase;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    SearchView searchView;
    ProgressBar progressBar;
    TextView textViewNoRecords;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();
        createFloatingActionButton();
        createDatabase();
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main_activity_toolbar);
        toolbar.inflateMenu(R.menu.nemu_main_activity);
        MenuItem myActionMenuItem = toolbar.getMenu().findItem(R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Submit " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "Change " + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void createFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(v -> newNote());
    }

    @SuppressLint("CheckResult")
    private void newNote() {
        Note newNote = new Note();
        startActivityForResult(ActivityEditRecord.createStartIntent(MainActivity.this, newNote), MY_CODE);
    }

    @SuppressLint("CheckResult")
    private void createDatabase() {
        Disposable disposable = Single.fromCallable(() -> LocalDatabase.getInstance().getAppDatabase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    appDatabase = object;
                    createRecyclerView();
                    createSubscriptionUpdateDatabase();
                });

        compositeDisposable.add(disposable);
    }

    private void createSubscriptionUpdateDatabase() {
        Disposable disposable = appDatabase.noteDao().getAll(Note.STATUS_NOTE_NOT_ARCHIVED)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateRecyclerView);
        compositeDisposable.add(disposable);
    }

    private void updateRecyclerView(List<Note> notes) {
        recyclerView.setAdapter(new RecyclerAdapterForNotes(notes, this));
        progressBar.setVisibility(View.GONE);
        if (notes.size() == 0) {
            textViewNoRecords.setVisibility(View.VISIBLE);
        } else {
            textViewNoRecords.setVisibility(View.GONE);
        }
    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progressbar);
        textViewNoRecords = findViewById(R.id.text_view_no_record_found);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Note note = data.getParcelableExtra(ActivityEditRecord.EXTRA_NOTE);
                updateOrInsertNote(note);
            }
        }
    }

    private void updateOrInsertNote(Note note) {
        Disposable disposable = Completable.fromAction(() -> appDatabase.noteDao().insert(note))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        compositeDisposable.add(disposable);
    }

    @Override
    public void onClick(Note note) {
        startActivityForResult(ActivityEditRecord.createStartIntent(MainActivity.this, note), MY_CODE);
    }

    @Override
    public void LongClick(Note note) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNegativeButton(R.string.delete_note, (dialog1, which) -> {
            Disposable disposable = Completable.fromAction(() -> appDatabase.noteDao().delete(note))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            compositeDisposable.add(disposable);
        });
        dialog.setPositiveButton(R.string.archived_note, (dialog12, which) -> {
            note.archivedNote();
            updateOrInsertNote(note);
        });

        dialog.setNeutralButton(R.string.close, (dialog13, which) -> dialog13.dismiss());

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        appDatabase.close(); // здесь не уверен. Это можно делать уже в UI потоке?
        super.onDestroy();
    }
}
