package com.directions.manager;

import android.content.Context;

import com.directions.entities.CustomerDetails;
import com.directions.entities.DeliveryRequest;
import com.directions.entities.DriverDetails;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vaibhav on 15/5/16.
 */
public class TestManager {

    private RequestManager requestManager;

    public TestManager init(Context context) {

        requestManager = RequestManager.getInstance(context);

        DeliveryRequest deliveryRequest = new DeliveryRequest();

        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setId("1");
        driverDetails.setName("Rajan Damodaran");
        driverDetails.setAddress("The Professional Couriers, Koramangala, Bangalore, Karnataka, India");

        LatLng latLngDriver = new LatLng(12.933965, 77.619575);
        driverDetails.setCurrentLocation(latLngDriver);

        deliveryRequest.setDriverDetails(driverDetails);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setId("1");
        customerDetails.setName("Ankan Anurag");
        customerDetails.setAddress("49, Saroj Apartment, 4th cross road, 8th main road, BTM Layout 2, Bangluru(Karnataka)");

        LatLng latLngCustomer = new LatLng(12.90507123, 77.60361314);
        customerDetails.setCurrentLocation(latLngCustomer);



        return this;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }
}
