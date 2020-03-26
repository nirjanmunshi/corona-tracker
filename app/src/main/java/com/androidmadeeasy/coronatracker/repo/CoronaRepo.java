package com.androidmadeeasy.coronatracker.repo;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidmadeeasy.coronatracker.R;
import com.androidmadeeasy.coronatracker.api.CoronaApi;
import com.androidmadeeasy.coronatracker.api.RetrofitInstance;
import com.androidmadeeasy.coronatracker.model.WorldData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaRepo {

    private WorldData worldData;
    private MutableLiveData<WorldData> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public CoronaRepo(Application application) {
        this.application = application;
    }

    // get world data from api
    public LiveData<WorldData> getWorldData(SwipeRefreshLayout swipeRefreshLayout){
        CoronaApi coronaApi = RetrofitInstance.getApi();
        Call<WorldData> call = coronaApi.getPopularMovies(application.getApplicationContext().getString(R.string.ig_key));

        call.enqueue(new Callback<WorldData>() {
            @Override
            public void onResponse(Call<WorldData> call, Response<WorldData> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()){
                    worldData = response.body();
                    mutableLiveData.setValue(worldData);
                }

            }

            @Override
            public void onFailure(Call<WorldData> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(application.getApplicationContext(),"Error in api", Toast.LENGTH_SHORT).show();
            }
        });

        return mutableLiveData;
    }

}
