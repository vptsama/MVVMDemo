package com.kirin.mvvmfirsttry;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Create date: 2020/10/11 13:35
 * Create by: chixilun
 * Profile:
 */
@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase mInstance;

    public abstract NoteDao noteDao();

    //synchronized : only 1 thread can get access to this method at the same moment
    public static synchronized NoteDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()  //和版本相关，version
                    .addCallback(roomCallback) //关联到下面写的roomcallback，效果应该是，创建database之后马上就添加三个item
                    .build();
        }
        return mInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        //onCreate只在第一次create这个database的时候被call，onOpen每次打开都会被回调
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(mInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","Description 1",1));
            noteDao.insert(new Note("Title 2","Description 2",2));
            noteDao.insert(new Note("Title 3","Description 3",3));
            return null;
        }
    }

}
