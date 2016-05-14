package com.directions.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vaibhav on 15/5/16.
 */
public abstract class AbstractPerson {
    
    protected String id;
    
    protected String name;

    protected String address;

    protected String phoneNumber;

    protected LatLng currentLocation;

    public LatLng getCurrentLocation() {

        return currentLocation;
    }

    public void setCurrentLocation(LatLng currentLocation) {

        this.currentLocation = currentLocation;
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
}
