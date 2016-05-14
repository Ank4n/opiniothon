package com.directions.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vaibhav on 15/5/16.
 */
public class User {

    public static final int TYPE_DRIVER = 1;

    public static final int TYPE_BUYER = 2;

    protected String id;
    
    protected String name;

    protected String address;

    protected String phoneNumber;

    private double lat;

    private double lon;

    private boolean isAvailable = true;

    protected int type = TYPE_DRIVER;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAvailable() {

        return isAvailable;
    }

    public void setAvailable(boolean available) {

        isAvailable = available;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentLatLng(LatLng latLngDriver) {

        this.lat = latLngDriver.latitude;
        this.lon = latLngDriver.longitude;
    }
}
