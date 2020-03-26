package com.androidmadeeasy.coronatracker.api;

import com.androidmadeeasy.coronatracker.model.WorldData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoronaApi {

    @GET("data")
    Call<WorldData> getPopularMovies(@Query("IG") String key);

    @GET("graphdata")
    Call<ResponseBody> graphData();

}
