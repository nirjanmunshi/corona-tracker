package com.androidmadeeasy.coronatracker.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.List;

public class WorldData {

    @SerializedName("totalConfirmed")
    private int totalConfirmed;

    @SerializedName("totalDeaths")
    private int totalDeaths;

    @SerializedName("totalRecovered")
    private int totalRecovered;

    @SerializedName("areas")
    private List<CountryData> countryData;

    @SerializedName("id")
    private String id;

    @Nullable
    @SerializedName("parentId")
    private String parentId;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("lastUpdated")
    private Timestamp lastUpdated;


    public WorldData() {
    }

    public WorldData(int totalConfirmed, int totalDeaths, int totalRecovered, List<CountryData> countryData, String id, @Nullable String parentId, String displayName, Timestamp lastUpdated) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.countryData = countryData;
        this.id = id;
        this.parentId = parentId;
        this.displayName = displayName;
        this.lastUpdated = lastUpdated;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public List<CountryData> getCountryData() {
        return countryData;
    }

    public void setCountryData(List<CountryData> countryData) {
        this.countryData = countryData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    public String getParentId() {
        return parentId;
    }

    public void setParentId(@Nullable String parentId) {
        this.parentId = parentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
