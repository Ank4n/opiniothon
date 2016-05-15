package com.directions.manager;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import com.directions.entities.DeliveryRequest;
import com.directions.entities.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaibhav on 15/5/16.
 */
public class TestManager {

    private RequestManager requestManager;

    private Handler handler = new Handler();

    public DeliveryRequest getDeliveryRequest() {
        return deliveryRequest;
    }

    public void setDeliveryRequest(DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    private DeliveryRequest deliveryRequest;

    public TestManager(Context context) {

        requestManager = RequestManager.getInstance(context);

        deliveryRequest = new DeliveryRequest();

        deliveryRequest.setId("1");

        User customerDetails = new User();
        customerDetails.setId("1");
        customerDetails.setName("Ankan Anurag");
        customerDetails.setAddress("49, Saroj Apartment, 4th cross road, 8th main road, BTM Layout 2, Bangluru(Karnataka)");

        deliveryRequest.setCustomerDetails(customerDetails);

        User driverDetails = new User();
        driverDetails.setId("1");
        driverDetails.setName("Rajan Damodaran");
        driverDetails.setAddress("The Professional Couriers, Koramangala, Bangalore, Karnataka, India");

        deliveryRequest.setDriverDetails(driverDetails);

        //Bangluru
        LatLng latLngCustomer = new LatLng(12.90276004, 77.60727167);
        customerDetails.setCurrentLatLng(latLngCustomer);

        LatLng latLngDriver = new LatLng(12.94717095, 77.62842357);
        driverDetails.setCurrentLatLng(latLngDriver);

        ArrayList<LatLng> recommendedPoints = new ArrayList<>();

        LatLng footPrintPoint1 = new LatLng(12.92376915, 77.61471748);
        recommendedPoints.add(footPrintPoint1);

        LatLng footPrintPoint2 = new LatLng(12.91117854, 77.60652065);
        recommendedPoints.add(footPrintPoint2);

        /*LatLng footPrintPoint3 = new LatLng(12.90442285, 77.60422468);
        recommendedPoints.add(footPrintPoint3);*/

        deliveryRequest.setStartPoint(latLngDriver);
        deliveryRequest.setEndPoint(latLngCustomer);

        deliveryRequest.setRecommendedPathPoints(recommendedPoints);

    }

    public void start() {

        RequestManager.OnRequestListener onRequestListener = requestManager.getOnRequestListener();
        if (onRequestListener != null) {

            onRequestListener.onRequestArrived(deliveryRequest);
        }
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    private static int i = 0;
    public static ArrayList<LatLng> getTestFootPrints(final OnTestFootPrintListener onTestFootPrintListener) {
        String json = "[{\"latitude\":12.94722,\"longitude\":77.6285,\"mVersionCode\":1},{\"latitude\":12.94664,\"longitude\":77.62886,\"mVersionCode\":1},{\"latitude\":12.94658,\"longitude\":77.62889,\"mVersionCode\":1},{\"latitude\":12.94643,\"longitude\":77.62897,\"mVersionCode\":1},{\"latitude\":12.9463,\"longitude\":77.62908,\"mVersionCode\":1},{\"latitude\":12.94556,\"longitude\":77.62952,\"mVersionCode\":1},{\"latitude\":12.9453,\"longitude\":77.62964,\"mVersionCode\":1},{\"latitude\":12.94455,\"longitude\":77.62998,\"mVersionCode\":1},{\"latitude\":12.94438,\"longitude\":77.63006,\"mVersionCode\":1},{\"latitude\":12.94391,\"longitude\":77.63028,\"mVersionCode\":1},{\"latitude\":12.94332,\"longitude\":77.63054,\"mVersionCode\":1},{\"latitude\":12.94249,\"longitude\":77.6309,\"mVersionCode\":1},{\"latitude\":12.94217,\"longitude\":77.63105,\"mVersionCode\":1},{\"latitude\":12.94183,\"longitude\":77.63119,\"mVersionCode\":1},{\"latitude\":12.94144,\"longitude\":77.63136,\"mVersionCode\":1},{\"latitude\":12.94142,\"longitude\":77.63136,\"mVersionCode\":1},{\"latitude\":12.94113,\"longitude\":77.63148,\"mVersionCode\":1},{\"latitude\":12.94091,\"longitude\":77.63156,\"mVersionCode\":1},{\"latitude\":12.9397,\"longitude\":77.63212,\"mVersionCode\":1},{\"latitude\":12.939,\"longitude\":77.63247,\"mVersionCode\":1},{\"latitude\":12.93897,\"longitude\":77.63249,\"mVersionCode\":1},{\"latitude\":12.93881,\"longitude\":77.63258,\"mVersionCode\":1},{\"latitude\":12.9388,\"longitude\":77.63259,\"mVersionCode\":1},{\"latitude\":12.93865,\"longitude\":77.63269,\"mVersionCode\":1},{\"latitude\":12.93865,\"longitude\":77.63269,\"mVersionCode\":1},{\"latitude\":12.93859,\"longitude\":77.63273,\"mVersionCode\":1},{\"latitude\":12.93849,\"longitude\":77.63277,\"mVersionCode\":1},{\"latitude\":12.93851,\"longitude\":77.63217,\"mVersionCode\":1},{\"latitude\":12.93851,\"longitude\":77.6321,\"mVersionCode\":1},{\"latitude\":12.93851,\"longitude\":77.63203,\"mVersionCode\":1},{\"latitude\":12.93851,\"longitude\":77.63198,\"mVersionCode\":1},{\"latitude\":12.9385,\"longitude\":77.63167,\"mVersionCode\":1},{\"latitude\":12.93848,\"longitude\":77.6312,\"mVersionCode\":1},{\"latitude\":12.93848,\"longitude\":77.63115,\"mVersionCode\":1},{\"latitude\":12.93848,\"longitude\":77.63105,\"mVersionCode\":1},{\"latitude\":12.93846,\"longitude\":77.63082,\"mVersionCode\":1},{\"latitude\":12.93847,\"longitude\":77.63079,\"mVersionCode\":1},{\"latitude\":12.93847,\"longitude\":77.6306,\"mVersionCode\":1},{\"latitude\":12.93848,\"longitude\":77.63013,\"mVersionCode\":1},{\"latitude\":12.93849,\"longitude\":77.62976,\"mVersionCode\":1},{\"latitude\":12.93849,\"longitude\":77.62961,\"mVersionCode\":1},{\"latitude\":12.93849,\"longitude\":77.62952,\"mVersionCode\":1},{\"latitude\":12.93848,\"longitude\":77.62947,\"mVersionCode\":1},{\"latitude\":12.93848,\"longitude\":77.62943,\"mVersionCode\":1},{\"latitude\":12.93847,\"longitude\":77.62939,\"mVersionCode\":1},{\"latitude\":12.93847,\"longitude\":77.62935,\"mVersionCode\":1},{\"latitude\":12.93846,\"longitude\":77.62932,\"mVersionCode\":1},{\"latitude\":12.93845,\"longitude\":77.62927,\"mVersionCode\":1},{\"latitude\":12.93844,\"longitude\":77.62923,\"mVersionCode\":1},{\"latitude\":12.93843,\"longitude\":77.6292,\"mVersionCode\":1},{\"latitude\":12.93842,\"longitude\":77.62916,\"mVersionCode\":1},{\"latitude\":12.93841,\"longitude\":77.62911,\"mVersionCode\":1},{\"latitude\":12.93839,\"longitude\":77.62906,\"mVersionCode\":1},{\"latitude\":12.93835,\"longitude\":77.62896,\"mVersionCode\":1},{\"latitude\":12.93828,\"longitude\":77.62876,\"mVersionCode\":1},{\"latitude\":12.93823,\"longitude\":77.62862,\"mVersionCode\":1},{\"latitude\":12.93822,\"longitude\":77.62857,\"mVersionCode\":1},{\"latitude\":12.93821,\"longitude\":77.62855,\"mVersionCode\":1},{\"latitude\":12.93818,\"longitude\":77.62851,\"mVersionCode\":1},{\"latitude\":12.93813,\"longitude\":77.62839,\"mVersionCode\":1},{\"latitude\":12.93806,\"longitude\":77.62829,\"mVersionCode\":1},{\"latitude\":12.93802,\"longitude\":77.6282,\"mVersionCode\":1},{\"latitude\":12.938,\"longitude\":77.62817,\"mVersionCode\":1},{\"latitude\":12.93791,\"longitude\":77.62803,\"mVersionCode\":1},{\"latitude\":12.93785,\"longitude\":77.62792,\"mVersionCode\":1},{\"latitude\":12.93779,\"longitude\":77.62783,\"mVersionCode\":1},{\"latitude\":12.93774,\"longitude\":77.62773,\"mVersionCode\":1},{\"latitude\":12.93774,\"longitude\":77.62772,\"mVersionCode\":1},{\"latitude\":12.93765,\"longitude\":77.62756,\"mVersionCode\":1},{\"latitude\":12.93759,\"longitude\":77.62745,\"mVersionCode\":1},{\"latitude\":12.93756,\"longitude\":77.62741,\"mVersionCode\":1},{\"latitude\":12.93754,\"longitude\":77.62738,\"mVersionCode\":1},{\"latitude\":12.93728,\"longitude\":77.62703,\"mVersionCode\":1},{\"latitude\":12.93722,\"longitude\":77.62696,\"mVersionCode\":1},{\"latitude\":12.93686,\"longitude\":77.62652,\"mVersionCode\":1},{\"latitude\":12.93602,\"longitude\":77.62548,\"mVersionCode\":1},{\"latitude\":12.93572,\"longitude\":77.62508,\"mVersionCode\":1},{\"latitude\":12.93548,\"longitude\":77.62478,\"mVersionCode\":1},{\"latitude\":12.93524,\"longitude\":77.62447,\"mVersionCode\":1},{\"latitude\":12.93517,\"longitude\":77.62439,\"mVersionCode\":1},{\"latitude\":12.93511,\"longitude\":77.62433,\"mVersionCode\":1},{\"latitude\":12.93508,\"longitude\":77.6243,\"mVersionCode\":1},{\"latitude\":12.93505,\"longitude\":77.62427,\"mVersionCode\":1},{\"latitude\":12.93502,\"longitude\":77.62424,\"mVersionCode\":1},{\"latitude\":12.935,\"longitude\":77.62422,\"mVersionCode\":1},{\"latitude\":12.93495,\"longitude\":77.62418,\"mVersionCode\":1},{\"latitude\":12.93491,\"longitude\":77.62415,\"mVersionCode\":1},{\"latitude\":12.93486,\"longitude\":77.62411,\"mVersionCode\":1},{\"latitude\":12.9348,\"longitude\":77.62407,\"mVersionCode\":1},{\"latitude\":12.93475,\"longitude\":77.62404,\"mVersionCode\":1},{\"latitude\":12.93468,\"longitude\":77.624,\"mVersionCode\":1},{\"latitude\":12.93464,\"longitude\":77.62397,\"mVersionCode\":1},{\"latitude\":12.9346,\"longitude\":77.62395,\"mVersionCode\":1},{\"latitude\":12.93456,\"longitude\":77.62392,\"mVersionCode\":1},{\"latitude\":12.93402,\"longitude\":77.62364,\"mVersionCode\":1},{\"latitude\":12.93339,\"longitude\":77.62335,\"mVersionCode\":1},{\"latitude\":12.93271,\"longitude\":77.62309,\"mVersionCode\":1},{\"latitude\":12.93151,\"longitude\":77.62261,\"mVersionCode\":1},{\"latitude\":12.93029,\"longitude\":77.62215,\"mVersionCode\":1},{\"latitude\":12.92949,\"longitude\":77.62184,\"mVersionCode\":1},{\"latitude\":12.92943,\"longitude\":77.62181,\"mVersionCode\":1},{\"latitude\":12.92936,\"longitude\":77.62178,\"mVersionCode\":1},{\"latitude\":12.92904,\"longitude\":77.62165,\"mVersionCode\":1},{\"latitude\":12.92857,\"longitude\":77.62146,\"mVersionCode\":1},{\"latitude\":12.92812,\"longitude\":77.62126,\"mVersionCode\":1},{\"latitude\":12.92763,\"longitude\":77.62107,\"mVersionCode\":1},{\"latitude\":12.92753,\"longitude\":77.62103,\"mVersionCode\":1},{\"latitude\":12.92745,\"longitude\":77.621,\"mVersionCode\":1},{\"latitude\":12.926,\"longitude\":77.62042,\"mVersionCode\":1},{\"latitude\":12.92571,\"longitude\":77.6203,\"mVersionCode\":1},{\"latitude\":12.92567,\"longitude\":77.62028,\"mVersionCode\":1},{\"latitude\":12.92562,\"longitude\":77.62026,\"mVersionCode\":1},{\"latitude\":12.92554,\"longitude\":77.62019,\"mVersionCode\":1},{\"latitude\":12.92552,\"longitude\":77.62016,\"mVersionCode\":1},{\"latitude\":12.92539,\"longitude\":77.61999,\"mVersionCode\":1},{\"latitude\":12.92532,\"longitude\":77.61989,\"mVersionCode\":1},{\"latitude\":12.92524,\"longitude\":77.61976,\"mVersionCode\":1},{\"latitude\":12.92518,\"longitude\":77.61963,\"mVersionCode\":1},{\"latitude\":12.92486,\"longitude\":77.61889,\"mVersionCode\":1},{\"latitude\":12.92477,\"longitude\":77.61863,\"mVersionCode\":1},{\"latitude\":12.92462,\"longitude\":77.61827,\"mVersionCode\":1},{\"latitude\":12.92462,\"longitude\":77.61827,\"mVersionCode\":1},{\"latitude\":12.92449,\"longitude\":77.61806,\"mVersionCode\":1},{\"latitude\":12.92457,\"longitude\":77.61801,\"mVersionCode\":1},{\"latitude\":12.9251,\"longitude\":77.6177,\"mVersionCode\":1},{\"latitude\":12.92559,\"longitude\":77.61739,\"mVersionCode\":1},{\"latitude\":12.92576,\"longitude\":77.6173,\"mVersionCode\":1},{\"latitude\":12.92586,\"longitude\":77.61731,\"mVersionCode\":1},{\"latitude\":12.92598,\"longitude\":77.61722,\"mVersionCode\":1},{\"latitude\":12.92638,\"longitude\":77.61701,\"mVersionCode\":1},{\"latitude\":12.92653,\"longitude\":77.61692,\"mVersionCode\":1},{\"latitude\":12.927,\"longitude\":77.61661,\"mVersionCode\":1},{\"latitude\":12.927,\"longitude\":77.61661,\"mVersionCode\":1},{\"latitude\":12.92675,\"longitude\":77.61625,\"mVersionCode\":1},{\"latitude\":12.92655,\"longitude\":77.61594,\"mVersionCode\":1},{\"latitude\":12.92646,\"longitude\":77.61572,\"mVersionCode\":1},{\"latitude\":12.92646,\"longitude\":77.61572,\"mVersionCode\":1},{\"latitude\":12.92647,\"longitude\":77.61561,\"mVersionCode\":1},{\"latitude\":12.92647,\"longitude\":77.61561,\"mVersionCode\":1},{\"latitude\":12.92627,\"longitude\":77.61556,\"mVersionCode\":1},{\"latitude\":12.92598,\"longitude\":77.61549,\"mVersionCode\":1},{\"latitude\":12.92567,\"longitude\":77.61539,\"mVersionCode\":1},{\"latitude\":12.92527,\"longitude\":77.61526,\"mVersionCode\":1},{\"latitude\":12.92518,\"longitude\":77.61522,\"mVersionCode\":1},{\"latitude\":12.9249,\"longitude\":77.61512,\"mVersionCode\":1},{\"latitude\":12.92488,\"longitude\":77.61511,\"mVersionCode\":1},{\"latitude\":12.92467,\"longitude\":77.61504,\"mVersionCode\":1},{\"latitude\":12.92444,\"longitude\":77.61497,\"mVersionCode\":1},{\"latitude\":12.9243,\"longitude\":77.61493,\"mVersionCode\":1},{\"latitude\":12.92412,\"longitude\":77.61485,\"mVersionCode\":1},{\"latitude\":12.92386,\"longitude\":77.61476,\"mVersionCode\":1},{\"latitude\":12.92375,\"longitude\":77.61472,\"mVersionCode\":1},{\"latitude\":12.92359,\"longitude\":77.61467,\"mVersionCode\":1},{\"latitude\":12.92341,\"longitude\":77.61462,\"mVersionCode\":1},{\"latitude\":12.92324,\"longitude\":77.61457,\"mVersionCode\":1},{\"latitude\":12.92282,\"longitude\":77.61442,\"mVersionCode\":1},{\"latitude\":12.92246,\"longitude\":77.61423,\"mVersionCode\":1},{\"latitude\":12.92221,\"longitude\":77.61408,\"mVersionCode\":1},{\"latitude\":12.92205,\"longitude\":77.61398,\"mVersionCode\":1},{\"latitude\":12.92187,\"longitude\":77.61388,\"mVersionCode\":1},{\"latitude\":12.92157,\"longitude\":77.61371,\"mVersionCode\":1},{\"latitude\":12.92126,\"longitude\":77.61356,\"mVersionCode\":1},{\"latitude\":12.92113,\"longitude\":77.61348,\"mVersionCode\":1},{\"latitude\":12.92097,\"longitude\":77.61342,\"mVersionCode\":1},{\"latitude\":12.92082,\"longitude\":77.61336,\"mVersionCode\":1},{\"latitude\":12.92074,\"longitude\":77.61333,\"mVersionCode\":1},{\"latitude\":12.92061,\"longitude\":77.61326,\"mVersionCode\":1},{\"latitude\":12.92047,\"longitude\":77.61322,\"mVersionCode\":1},{\"latitude\":12.92035,\"longitude\":77.61316,\"mVersionCode\":1},{\"latitude\":12.92007,\"longitude\":77.61307,\"mVersionCode\":1},{\"latitude\":12.91977,\"longitude\":77.613,\"mVersionCode\":1},{\"latitude\":12.91963,\"longitude\":77.61297,\"mVersionCode\":1},{\"latitude\":12.91963,\"longitude\":77.61297,\"mVersionCode\":1},{\"latitude\":12.91964,\"longitude\":77.61292,\"mVersionCode\":1},{\"latitude\":12.91964,\"longitude\":77.61286,\"mVersionCode\":1},{\"latitude\":12.91965,\"longitude\":77.61276,\"mVersionCode\":1},{\"latitude\":12.91967,\"longitude\":77.61252,\"mVersionCode\":1},{\"latitude\":12.91969,\"longitude\":77.61224,\"mVersionCode\":1},{\"latitude\":12.91971,\"longitude\":77.6118,\"mVersionCode\":1},{\"latitude\":12.91973,\"longitude\":77.61148,\"mVersionCode\":1},{\"latitude\":12.91975,\"longitude\":77.61124,\"mVersionCode\":1},{\"latitude\":12.91976,\"longitude\":77.61113,\"mVersionCode\":1},{\"latitude\":12.91978,\"longitude\":77.61079,\"mVersionCode\":1},{\"latitude\":12.91982,\"longitude\":77.61038,\"mVersionCode\":1},{\"latitude\":12.91982,\"longitude\":77.61038,\"mVersionCode\":1},{\"latitude\":12.91933,\"longitude\":77.61035,\"mVersionCode\":1},{\"latitude\":12.91848,\"longitude\":77.61031,\"mVersionCode\":1},{\"latitude\":12.91781,\"longitude\":77.61025,\"mVersionCode\":1},{\"latitude\":12.91708,\"longitude\":77.6102,\"mVersionCode\":1},{\"latitude\":12.9166,\"longitude\":77.61015,\"mVersionCode\":1},{\"latitude\":12.9166,\"longitude\":77.61015,\"mVersionCode\":1},{\"latitude\":12.91651,\"longitude\":77.61015,\"mVersionCode\":1},{\"latitude\":12.91651,\"longitude\":77.61006,\"mVersionCode\":1},{\"latitude\":12.91655,\"longitude\":77.60846,\"mVersionCode\":1},{\"latitude\":12.91657,\"longitude\":77.60786,\"mVersionCode\":1},{\"latitude\":12.91656,\"longitude\":77.60742,\"mVersionCode\":1},{\"latitude\":12.91658,\"longitude\":77.60665,\"mVersionCode\":1},{\"latitude\":12.91658,\"longitude\":77.60659,\"mVersionCode\":1},{\"latitude\":12.91659,\"longitude\":77.60611,\"mVersionCode\":1},{\"latitude\":12.91659,\"longitude\":77.60609,\"mVersionCode\":1},{\"latitude\":12.9166,\"longitude\":77.60598,\"mVersionCode\":1},{\"latitude\":12.9166,\"longitude\":77.60598,\"mVersionCode\":1},{\"latitude\":12.91631,\"longitude\":77.60597,\"mVersionCode\":1},{\"latitude\":12.91581,\"longitude\":77.60607,\"mVersionCode\":1},{\"latitude\":12.91512,\"longitude\":77.60612,\"mVersionCode\":1},{\"latitude\":12.91369,\"longitude\":77.60622,\"mVersionCode\":1},{\"latitude\":12.91369,\"longitude\":77.60622,\"mVersionCode\":1},{\"latitude\":12.91363,\"longitude\":77.60623,\"mVersionCode\":1},{\"latitude\":12.9124,\"longitude\":77.60635,\"mVersionCode\":1},{\"latitude\":12.9121,\"longitude\":77.60638,\"mVersionCode\":1},{\"latitude\":12.91173,\"longitude\":77.6064,\"mVersionCode\":1},{\"latitude\":12.91171,\"longitude\":77.6064,\"mVersionCode\":1},{\"latitude\":12.91055,\"longitude\":77.60649,\"mVersionCode\":1},{\"latitude\":12.91039,\"longitude\":77.60649,\"mVersionCode\":1},{\"latitude\":12.91035,\"longitude\":77.60649,\"mVersionCode\":1},{\"latitude\":12.91009,\"longitude\":77.6065,\"mVersionCode\":1},{\"latitude\":12.90981,\"longitude\":77.60651,\"mVersionCode\":1},{\"latitude\":12.90977,\"longitude\":77.60651,\"mVersionCode\":1},{\"latitude\":12.90945,\"longitude\":77.60651,\"mVersionCode\":1},{\"latitude\":12.90898,\"longitude\":77.60647,\"mVersionCode\":1},{\"latitude\":12.90837,\"longitude\":77.60638,\"mVersionCode\":1},{\"latitude\":12.90734,\"longitude\":77.60622,\"mVersionCode\":1},{\"latitude\":12.90688,\"longitude\":77.60613,\"mVersionCode\":1},{\"latitude\":12.90674,\"longitude\":77.60612,\"mVersionCode\":1},{\"latitude\":12.90651,\"longitude\":77.6061,\"mVersionCode\":1},{\"latitude\":12.90643,\"longitude\":77.60608,\"mVersionCode\":1},{\"latitude\":12.90637,\"longitude\":77.60606,\"mVersionCode\":1},{\"latitude\":12.90633,\"longitude\":77.60604,\"mVersionCode\":1},{\"latitude\":12.90626,\"longitude\":77.60601,\"mVersionCode\":1},{\"latitude\":12.90619,\"longitude\":77.60599,\"mVersionCode\":1},{\"latitude\":12.90613,\"longitude\":77.60598,\"mVersionCode\":1},{\"latitude\":12.90567,\"longitude\":77.60592,\"mVersionCode\":1},{\"latitude\":12.90567,\"longitude\":77.60592,\"mVersionCode\":1},{\"latitude\":12.90562,\"longitude\":77.60591,\"mVersionCode\":1},{\"latitude\":12.90571,\"longitude\":77.60542,\"mVersionCode\":1},{\"latitude\":12.90573,\"longitude\":77.60523,\"mVersionCode\":1},{\"latitude\":12.90577,\"longitude\":77.60468,\"mVersionCode\":1},{\"latitude\":12.90579,\"longitude\":77.60434,\"mVersionCode\":1},{\"latitude\":12.90583,\"longitude\":77.60388,\"mVersionCode\":1},{\"latitude\":12.90583,\"longitude\":77.60388,\"mVersionCode\":1},{\"latitude\":12.90548,\"longitude\":77.60386,\"mVersionCode\":1},{\"latitude\":12.90548,\"longitude\":77.60386,\"mVersionCode\":1},{\"latitude\":12.90546,\"longitude\":77.60209,\"mVersionCode\":1},{\"latitude\":12.90546,\"longitude\":77.60197,\"mVersionCode\":1},{\"latitude\":12.90546,\"longitude\":77.60197,\"mVersionCode\":1},{\"latitude\":12.90544,\"longitude\":77.60197,\"mVersionCode\":1},{\"latitude\":12.90541,\"longitude\":77.60197,\"mVersionCode\":1},{\"latitude\":12.90539,\"longitude\":77.60197,\"mVersionCode\":1},{\"latitude\":12.90528,\"longitude\":77.60197,\"mVersionCode\":1},{\"latitude\":12.90507,\"longitude\":77.60195,\"mVersionCode\":1},{\"latitude\":12.905,\"longitude\":77.60193,\"mVersionCode\":1},{\"latitude\":12.90491,\"longitude\":77.60191,\"mVersionCode\":1},{\"latitude\":12.9045,\"longitude\":77.60182,\"mVersionCode\":1},{\"latitude\":12.90345,\"longitude\":77.60156,\"mVersionCode\":1},{\"latitude\":12.90345,\"longitude\":77.60156,\"mVersionCode\":1},{\"latitude\":12.90342,\"longitude\":77.60184,\"mVersionCode\":1},{\"latitude\":12.90337,\"longitude\":77.60285,\"mVersionCode\":1},{\"latitude\":12.90337,\"longitude\":77.60286,\"mVersionCode\":1},{\"latitude\":12.90331,\"longitude\":77.6036,\"mVersionCode\":1},{\"latitude\":12.90329,\"longitude\":77.60376,\"mVersionCode\":1},{\"latitude\":12.90327,\"longitude\":77.60408,\"mVersionCode\":1},{\"latitude\":12.90325,\"longitude\":77.6043,\"mVersionCode\":1},{\"latitude\":12.90323,\"longitude\":77.60456,\"mVersionCode\":1},{\"latitude\":12.90318,\"longitude\":77.60497,\"mVersionCode\":1},{\"latitude\":12.90316,\"longitude\":77.60522,\"mVersionCode\":1},{\"latitude\":12.90315,\"longitude\":77.60549,\"mVersionCode\":1},{\"latitude\":12.90316,\"longitude\":77.60571,\"mVersionCode\":1},{\"latitude\":12.90319,\"longitude\":77.60639,\"mVersionCode\":1},{\"latitude\":12.90321,\"longitude\":77.60726,\"mVersionCode\":1}]";
        Gson gson = new Gson();

        Type listType = new TypeToken<List<LatLng>>() {
        }.getType();
        final ArrayList<LatLng> latLngArrayList = gson.fromJson(json, listType);
        Log.d("ROUTEDATA", latLngArrayList.toString());

        i = 0;

        if (onTestFootPrintListener != null) {

            /*for (int i = 0; i < latLngArrayList.size(); i= i + 10) {

                onTestFootPrintListener.onNextFootPrint(latLngArrayList.get(i));

                Log.d("STACK SIZE : ", "SIZE : " + i);
            }*/

            new CountDownTimer(70000, 130) {

                @Override
                public void onTick(long millisUntilFinished) {

                    if(latLngArrayList.size() > i) {

                        onTestFootPrintListener.onNextFootPrint(latLngArrayList.get(i));
                    } else {

                        cancel();
                    }

                    i = i + 1;
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }

        return latLngArrayList;
    }

    public interface OnTestFootPrintListener {

        void onNextFootPrint(LatLng pop);
    }
}
