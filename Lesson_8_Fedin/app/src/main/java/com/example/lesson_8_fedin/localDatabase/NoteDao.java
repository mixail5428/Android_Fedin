package com.example.lesson_8_fedin.localDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lesson_8_fedin.localDatabase.Note;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note WHERE archived =:criterionArchivedStatusNote")
    Flowable<List<Note>> getAll(int criterionArchivedStatusNote);

    @Query("SELECT * FROM note WHERE id =:idNote")
    Flowable<Note> getNoteById(long idNote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAndReturnId(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);


}
