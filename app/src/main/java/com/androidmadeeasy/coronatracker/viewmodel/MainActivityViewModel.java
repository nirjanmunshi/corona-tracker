package com.androidmadeeasy.coronatracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.androidmadeeasy.coronatracker.model.WorldData;
import com.androidmadeeasy.coronatracker.repo.CoronaRepo;

public class MainActivityViewModel extends AndroidViewModel {

    private CoronaRepo repo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repo = new CoronaRepo(application);
    }

    // return json data from repo
    public LiveData<WorldData> getWorldData(){
        return repo.getWorldData();
    }
}
