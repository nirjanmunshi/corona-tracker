package com.androidmadeeasy.coronatracker.api;

import com.androidmadeeasy.coronatracker.model.WorldData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoronaApi {

    @GET("data")
    Call<WorldData> getPopularMovies(@Query("IG") String key);


}
