package com.androidmadeeasy.coronatracker.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.List;

public class CountryData {

    @SerializedName("totalConfirmed")
    private int totalConfirmed;

    @SerializedName("totalDeaths")
    private int totalDeaths;

    @SerializedName("totalRecovered")
    private int totalRecovered;

    @SerializedName("id")
    private String id;

    @SerializedName("lastUpdated")
    private Timestamp lastUpdated;

    @SerializedName("areas")
    private List<RegionData> regionData;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("long")
    private double longitude;

    @SerializedName("country")
    private String country;

    @SerializedName("parentId")
    private String parentId;

    public CountryData() {
    }

    public CountryData(int totalConfirmed, int totalDeaths, int totalRecovered, String id, Timestamp lastUpdated, List<RegionData> regionData, String displayName, double latitude, double longitude, String country, String parentId) {
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.regionData = regionData;
        this.displayName = displayName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<RegionData> getRegionData() {
        return regionData;
    }

    public void setRegionData(List<RegionData> regionData) {
        this.regionData = regionData;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


}
