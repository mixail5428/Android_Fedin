package com.example.lesson_8_fedin.localDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lesson_8_fedin.localDatabase.Note;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    Flowable<List<Note>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);


}
