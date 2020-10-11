package com.kirin.mvvmfirsttry;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Create date: 2020/10/11 13:27
 * Create by: chixilun
 * Profile:
 */

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //定义一个非预设的操作，可以用@Query
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    //返回livedata的话，如果有变化可以自动告知activity
    LiveData<List<Note>> getAllNotes();
}
