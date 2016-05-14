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

    private LatLng startPoint;

    private LatLng endPoint;

    private ArrayList<LatLng> recommendedPathPoints;

    private ArrayList<LatLng> driverFootPrints;

    private DriverDetails driverDetails;

    private CustomerDetails customerDetails;

    private String status = STATUS_WAITING;

    public LatLng getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(LatLng startPoint) {
        this.startPoint = startPoint;
    }

    public LatLng getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(LatLng endPoint) {
        this.endPoint = endPoint;
    }

    public ArrayList<LatLng> getRecommendedPathPoints() {

        if(recommendedPathPoints != null) {

            recommendedPathPoints = new ArrayList<>();
        }

        return recommendedPathPoints;
    }

    public void setRecommendedPathPoints(ArrayList<LatLng> recommendedPathPoints) {

        this.recommendedPathPoints = recommendedPathPoints;
    }

    public DriverDetails getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<LatLng> getDriverFootPrints() {
        return driverFootPrints;
    }

    public void setDriverFootPrints(ArrayList<LatLng> driverFootPrints) {
        this.driverFootPrints = driverFootPrints;
    }
}
