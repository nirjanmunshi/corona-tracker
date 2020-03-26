package com.androidmadeeasy.coronatracker.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidmadeeasy.coronatracker.R;
import com.androidmadeeasy.coronatracker.adapter.RegionAdapter;
import com.androidmadeeasy.coronatracker.model.CountryData;
import com.androidmadeeasy.coronatracker.viewmodel.CountryListActivityViewModel;
import com.google.gson.Gson;

import java.util.List;


public class CountryListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, RegionAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "CountryListActivity";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CountryListActivityViewModel countryListViewModel;
    private List<CountryData> countryDataList;
    private RegionAdapter regionAdapter;
    private SearchView searchView;
    private CountryData dataForWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        init();
        countryListViewModel = new ViewModelProvider(this).get(CountryListActivityViewModel.class);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_purple,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark
        );
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        swipeRefreshLayout.setOnRefreshListener(this :: getWorldData);
        searchView.setOnQueryTextListener(this);
        getWorldData();


    }

    private void getWorldData() {
        countryListViewModel.getWorldData(swipeRefreshLayout).observe(this, worldData -> {
            Gson gson = new Gson();
            Log.d(TAG, "getWorldData: " + gson.toJson(worldData));
            dataForWorld = new CountryData(worldData.getTotalConfirmed(),worldData.getTotalDeaths(),worldData.getTotalRecovered(),worldData.getId(),worldData.getLastUpdated(),null,worldData.getDisplayName(),0.0,0.0,"","");
            bindRegion(worldData.getCountryData());
        });
    }

    private void bindRegion(List<CountryData> countries) {
        countryDataList = countries;
        if(!countryDataList.isEmpty()) {
            countries.add(0,dataForWorld);
            showRecyclerView();
        } else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "empty list", Toast.LENGTH_SHORT).show();
        }
    }

    private void showRecyclerView() {
        regionAdapter = new RegionAdapter(this, countryDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(regionAdapter);
        regionAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
        regionAdapter.setOnItemClickListener(this);
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        searchView = findViewById(R.id.searchView);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(regionAdapter != null) {
            regionAdapter.filter(s);
        }
        return false;
    }

    @Override
    public void onItemClick(CountryData countryData) {
        Log.d(TAG, "onItemClick: " + new Gson().toJson(countryData));
        if(countryData != null) {
            Intent intent = new Intent();
            intent.putExtra("countryData", new Gson().toJson(countryData));
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "Null Country Data", Toast.LENGTH_SHORT).show();
        }
    }
}
