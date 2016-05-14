package com.directions.manager;

import android.content.Context;

import com.directions.entities.DeliveryRequest;
import com.directions.entities.User;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vaibhav on 15/5/16.
 */
public class TestManager {

    private RequestManager requestManager;

    public DeliveryRequest getDeliveryRequest() {
        return deliveryRequest;
    }

    public void setDeliveryRequest(DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    private DeliveryRequest deliveryRequest;

    public TestManager init(Context context) {

        requestManager = RequestManager.getInstance(context);

        deliveryRequest = new DeliveryRequest();

        deliveryRequest.setId("1");

        User driverDetails = new User();
        driverDetails.setId("1");
        driverDetails.setName("Rajan Damodaran");
        driverDetails.setAddress("The Professional Couriers, Koramangala, Bangalore, Karnataka, India");

        LatLng latLngDriver = new LatLng(12.933965, 77.619575);
        driverDetails.setCurrentLatLng(latLngDriver);

        deliveryRequest.setDriverDetails(driverDetails);

        User customerDetails = new User();
        customerDetails.setId("1");
        customerDetails.setName("Ankan Anurag");
        customerDetails.setAddress("49, Saroj Apartment, 4th cross road, 8th main road, BTM Layout 2, Bangluru(Karnataka)");

        LatLng latLngCustomer = new LatLng(12.90507123, 77.60361314);
        customerDetails.setCurrentLatLng(latLngCustomer);

        deliveryRequest.setCustomerDetails(customerDetails);

        deliveryRequest.setStartPoint(latLngDriver);
        deliveryRequest.setEndPoint(latLngCustomer);

        return this;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }
}
