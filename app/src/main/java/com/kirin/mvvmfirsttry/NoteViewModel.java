package com.kirin.mvvmfirsttry;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Create date: 2020/10/11 14:25
 * Create by: chixilun
 * Profile:
 */
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository mRepository;

    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public void insert(Note note){
        mRepository.insert(note);
    }

    public void update(Note note){
        mRepository.update(note);
    }

    public void delete(Note note){
        mRepository.delete(note);
    }

    public void deleteAllNotes(){
        mRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

}
