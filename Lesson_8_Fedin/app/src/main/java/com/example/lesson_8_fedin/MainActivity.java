package com.example.lesson_8_fedin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lesson_8_fedin.localDatabase.AppDatabase;
import com.example.lesson_8_fedin.localDatabase.LocalDatabase;
import com.example.lesson_8_fedin.localDatabase.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecyclerAdapterForNotes.OnClickRecyclerElement {

    public static final int MY_CODE = 48156;

    RecyclerView recyclerView;
    AppDatabase appDatabase;
    Disposable disposable;
    Disposable disposable1;
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
        disposable1 = Completable.create(e -> {
            appDatabase = LocalDatabase.getInstance().getAppDatabase();
            e.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    createRecyclerView();
                    createSubscriptionUpdateDatabase();
                });
    }


    private void createSubscriptionUpdateDatabase() {
        disposable = appDatabase.noteDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateRecyclerView);
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
        if(requestCode == MY_CODE && resultCode == RESULT_OK){
            if(data != null){
                Note note = data.getParcelableExtra(ActivityEditRecord.EXTRA_NOTE);
                Completable.create(e -> {
                    appDatabase.noteDao().insert(note);
                    e.onComplete();
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            }
        }
    }

    @Override
    public void click(Note note) {
        startActivityForResult(ActivityEditRecord.createStartIntent(MainActivity.this, note), MY_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("qwerty", "onDestroy");
        if (disposable.isDisposed()) {
            disposable.dispose();
            Log.i("qwerty", "отписался от обновлений базы"); // Этого нет в логах
        }

        if (disposable1.isDisposed()) {
            disposable1.dispose();
            Log.i("qwerty", "отписался от completable");
        }
    }
}
