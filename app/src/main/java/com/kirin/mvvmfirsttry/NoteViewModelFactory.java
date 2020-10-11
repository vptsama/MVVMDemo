package com.kirin.mvvmfirsttry;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Create date: 2020/10/11 14:47
 * Create by: chixilun
 * Profile:
 */
public class NoteViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;


    public NoteViewModelFactory(Application application){
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NoteViewModel(mApplication);
    }
}
