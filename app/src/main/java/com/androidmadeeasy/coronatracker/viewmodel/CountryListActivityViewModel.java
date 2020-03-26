package com.androidmadeeasy.coronatracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidmadeeasy.coronatracker.model.WorldData;
import com.androidmadeeasy.coronatracker.repo.CoronaRepo;

public class CountryListActivityViewModel extends AndroidViewModel {

    private CoronaRepo repo;
    public CountryListActivityViewModel(@NonNull Application application) {
        super(application);
        repo = new CoronaRepo(application);
    }

    // return json data from repo
    public LiveData<WorldData> getWorldData(SwipeRefreshLayout swipeRefreshLayout){
        return repo.getWorldData(swipeRefreshLayout);
    }
}
