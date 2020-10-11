package com.kirin.mvvmfirsttry;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Create date: 2020/10/11 13:57
 * Create by: chixilun
 * Profile:
 */
public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        //上面的getinstance方法，room直接就自动生成了noteDao里面的方法
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    /**
     * APIs that is open to other class
     * Start
     */
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    /**
     * End
     */

    //对数据库的操作不允许在主线程中完成，创建一个AsyncTask去操作数据库
    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;
        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
