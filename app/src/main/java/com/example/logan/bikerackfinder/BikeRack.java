package com.example.logan.bikerackfinder;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Logan on 9/04/2016.
 */
public class BikeRack {
    private String type;
    private String title;
    private String location;
    private double latitude;
    private double longitude;
    private int numberOfRacks;
    private boolean isSheltered;

    public BikeRack(String type, String title, String location,double latitude, double longitude, int numberOfRacks, boolean isSheltered){
        this.type = type;
        this.title = title;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberOfRacks = numberOfRacks;
        this.isSheltered = isSheltered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getNumberOfRacks() {
        return numberOfRacks;
    }

    public void setNumberOfRacks(int numberOfRacks) {
        this.numberOfRacks = numberOfRacks;
    }

    public boolean getIsSheltered() {
        return isSheltered;
    }

    public void setIsSheltered(boolean sheltered) {
        isSheltered = sheltered;
    }
}