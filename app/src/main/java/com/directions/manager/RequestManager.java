package com.directions.manager;

import android.content.Context;

import com.directions.entities.DeliveryRequest;

/**
 * Created by vaibhav on 15/5/16.
 */
public class RequestManager {

    private static RequestManager requestManager;

    private Context context;

    private OnRequestListener onRequestListener;

    private RequestManager(){}

    public static RequestManager getInstance(Context context) {

        if(requestManager == null) {

            requestManager = new RequestManager();
        }

        requestManager.context = context;

        return requestManager;
    }

    public OnRequestListener getOnRequestListener() {
        return onRequestListener;
    }

    public void setOnRequestListener(OnRequestListener onRequestListener) {
        this.onRequestListener = onRequestListener;
    }

    public interface OnRequestListener {

        void onRequestArrived(DeliveryRequest deliveryRequest);
    }
}
