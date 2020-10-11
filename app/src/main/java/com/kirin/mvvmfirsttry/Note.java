package com.kirin.mvvmfirsttry;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Create date: 2020/10/11 13:09
 * Create by: chixilun
 * Profile:
 */

//写了这个后，会自动生成一个SQLite table
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
