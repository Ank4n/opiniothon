package com.directions.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vaibhav on 15/5/16.
 */
public class LocationPoint {

    private String locationId;

    private String deliveryId;

    private double lat;

    private double lon;

    public LocationPoint() {

    }

    public LatLng getLatLong(){

       return new LatLng(lat, lon);
    }

    public void setLatLong(LatLng latLng){

        this.lat = latLng.latitude;
        this.lon = latLng.longitude;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
