package com.androidmadeeasy.coronatracker.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class RegionData {

    @SerializedName("totalConfirmed")
    private int totalConfirmed;

    @SerializedName("totalDeaths")
    private int totalDeaths;

    @SerializedName("totalRecovered")
    private int totalRecovered;

    @SerializedName("lastUpdated")
    private Timestamp lastUpdated;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("long")
    private double longitude;

    @SerializedName("parentId")
    private String parentId;

    public RegionData() {
    }

    public RegionData(int totalConfirmed, int totalDeaths, int totalRecovered, Timestamp lastUpdated, String displayName, double latitude, double longitude, String parentId) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.lastUpdated = lastUpdated;
        this.displayName = displayName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parentId = parentId;
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

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
