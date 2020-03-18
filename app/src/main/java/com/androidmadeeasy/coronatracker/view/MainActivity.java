package com.androidmadeeasy.coronatracker.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.androidmadeeasy.coronatracker.R;
import com.androidmadeeasy.coronatracker.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    private TextView totalConfirmCases;
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

        getWorldData();
    }

    private void initialization() {
        totalConfirmCases = findViewById(R.id.total_confirmed_cases_number);
    }

    private void getWorldData() {
        mainActivityViewModel.getWorldData().observe(this, worldData -> {
            Gson gson = new Gson();
            Log.d(TAG, "getWorldData: "+gson.toJson(worldData));

            totalConfirmCases.setText(String.valueOf(worldData.getTotalConfirmed()));
        });
    }
}
