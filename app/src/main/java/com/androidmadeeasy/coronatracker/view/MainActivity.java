package com.androidmadeeasy.coronatracker.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidmadeeasy.coronatracker.R;
import com.androidmadeeasy.coronatracker.adapter.RegionAdapter;
import com.androidmadeeasy.coronatracker.model.CountryData;
import com.androidmadeeasy.coronatracker.model.WorldData;
import com.androidmadeeasy.coronatracker.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int RequestCode = 1234;
    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    private TextView totalConfirmCases, totalDeathCases, totalRecoveredCases, totalActiveCases,region;
    private AutoCompleteTextView etRegion;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        initialization();
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_purple,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark
        );
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        swipeRefreshLayout.setOnRefreshListener(this::getWorldData);

        getWorldData();
    }

    private void initialization() {
        totalConfirmCases = findViewById(R.id.total_confirmed_cases_number);
        totalDeathCases = findViewById(R.id.total_confirmed_death_number);
        totalRecoveredCases = findViewById(R.id.heading_total_recovered_cases_number);
        totalActiveCases = findViewById(R.id.total_active_cases_number);
        region = findViewById(R.id.region);
        etRegion = findViewById(R.id.etRegion);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
    }

    private void getWorldData() {
        mainActivityViewModel.getWorldData(swipeRefreshLayout).observe(this, worldData -> {
            Gson gson = new Gson();
            Log.d(TAG, "getWorldData: " + gson.toJson(worldData));
            updateMainUI(worldData);
            etRegion.setOnClickListener(view -> startActivityForResult(new Intent(this,CountryListActivity.class),RequestCode));
        });
    }

    private void updateMainUI(WorldData worldData){
        totalConfirmCases.setText(String.valueOf(worldData.getTotalConfirmed()));
        totalDeathCases.setText(String.valueOf(worldData.getTotalDeaths()));
        totalRecoveredCases.setText(String.valueOf(worldData.getTotalRecovered()));
        totalActiveCases.setText(
                String.valueOf(worldData.getTotalConfirmed() - (worldData.getTotalRecovered() + worldData.getTotalDeaths()))
        );
        region.setText(worldData.getDisplayName());
        etRegion.setText(worldData.getDisplayName().toUpperCase());
    }

    private void updateMainUI(CountryData data){
        totalConfirmCases.setText(String.valueOf(data.getTotalConfirmed()));
        totalDeathCases.setText(String.valueOf(data.getTotalDeaths()));
        totalRecoveredCases.setText(String.valueOf(data.getTotalRecovered()));
        totalActiveCases.setText(
                String.valueOf(data.getTotalConfirmed() - (data.getTotalRecovered() + data.getTotalDeaths()))
        );
        region.setText(data.getDisplayName());
        etRegion.setText(data.getDisplayName().toUpperCase());
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode && resultCode == RESULT_OK){
            if(data != null){
                String s = data.getStringExtra("countryData");
                CountryData countryData = new Gson().fromJson(s,CountryData.class);
                updateMainUI(countryData);
                Log.d(TAG, "onActivityResult: "+new Gson().toJson(countryData));
            }
        }
    }
}
