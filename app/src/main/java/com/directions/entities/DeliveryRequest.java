package com.directions.entities;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by vaibhav on 15/5/16.
 */
public class DeliveryRequest {

    public static final String STATUS_WAITING = "Waiting";
    public static final String STATUS_PICKED_UP= "Picked up";
    public static final String STATUS_READY_ON_WAY = "On the way";
    public static final String STATUS_DELIVERED = "Delivered";
    public static final String STATUS_CANCELLED = "Cancelled";

    private String id;

    private double startLat;

    private double startLon;

    private double destinationLat;

    private double destinationLon;

    private ArrayList<LocationPoint> recommendedPathPoints;

    private ArrayList<LocationPoint> driverFootPrints;

    private User driverDetails;

    private User customerDetails;

    private String status = STATUS_WAITING;

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public double getDestinationLon() {
        return destinationLon;
    }

    public void setDestinationLon(double destinationLon) {
        this.destinationLon = destinationLon;
    }

    public User getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(User driverDetails) {
        this.driverDetails = driverDetails;
    }

    public User getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(User customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<LatLng> getDriverFootPrints() {

        if(driverFootPrints == null) {

            driverFootPrints = new ArrayList<>();
        }

        ArrayList<LatLng> latLngs = new ArrayList<>();

        for (LocationPoint locationPoint : driverFootPrints) {

            latLngs.add(locationPoint.getLatLong());
        }

        return latLngs;
    }

    public void setDriverFootPrints(ArrayList<LatLng> latLngs) {

        ArrayList<LocationPoint> locationPoints = new ArrayList<>();

        for (LatLng latLng : latLngs) {

            LocationPoint locationPoint = new LocationPoint();
            locationPoint.setDeliveryId(id);
            locationPoint.setLatLong(latLng);
            locationPoints.add(locationPoint);
        }

        this.driverFootPrints = locationPoints;
    }

    public ArrayList<LatLng> getRecommendedPathPoints() {

        if(recommendedPathPoints == null) {

            recommendedPathPoints = new ArrayList<>();
        }

        ArrayList<LatLng> latLngs = new ArrayList<>();

        for (LocationPoint locationPoint : recommendedPathPoints) {

            latLngs.add(locationPoint.getLatLong());
        }

        return latLngs;
    }

    public void setRecommendedPathPoints(ArrayList<LatLng> latLngs) {

        ArrayList<LocationPoint> locationPoints = new ArrayList<>();

        for (LatLng latLng : latLngs) {

            LocationPoint locationPoint = new LocationPoint();
            locationPoint.setDeliveryId(id);
            locationPoint.setLatLong(latLng);
            locationPoints.add(locationPoint);
        }

        this.recommendedPathPoints = locationPoints;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartPoint(LatLng latLng) {

        startLat = latLng.latitude;
        startLon = latLng.longitude;
    }

    public void setEndPoint(LatLng latLng) {

        destinationLat = latLng.latitude;
        destinationLon = latLng.longitude;
    }
}
