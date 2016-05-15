package opiniothon.capriccioso.roadrunner.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.entities.DeliveryRequest;
import com.directions.manager.RequestManager;
import com.directions.manager.TestManager;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import opiniothon.capriccioso.roadrunner.R;

public class DriverMapActivity extends AppCompatActivity {

    //TODO For demonstration only
    public static boolean launchCollectionMode = true;

    private static final String LOG_TAG = "DriverMapActivity";

    //private static final int[] COLORS = new int[]{COLOR_GOOGLE_PATH, TRAVELLED_PATH, SUGGESTED_PATH};

    private GoogleMap map;

    //private LatLng start;
    //private LatLng end;

    private List<Polyline> polyLines;

    private RequestManager requestManager;

    private View next;

    private static final LatLngBounds BOUNDS_BANGALURU = new LatLngBounds(
            new LatLng(77.40692138671875, 13.124954649619102),
            new LatLng(77.84912109375, 12.736800512460297));

    private TextView tvAppCalculated;

    private TextView tvGoogleSuggested;

    /**
     * This activity loads a map and then displays the showGoogleRecommendedRoute and pushpins on it.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_map_activity);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true);
        }

        polyLines = new ArrayList<>();

        MapsInitializer.initialize(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment == null) {

            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        }

        map = mapFragment.getMap();

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition position) {

                LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;
                //mAdapter.setBounds(bounds);
            }
        });

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                //CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                //CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                //map.moveCamera(center);
                //map.animateCamera(zoom);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 10, locationListener);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, locationListener);

        //map.setMyLocationEnabled(true);

        map.getUiSettings().setZoomControlsEnabled(true);

        //start = new LatLng(12.94717095, 77.62842357);
        //end = new LatLng(12.90276004 , 77.60727167);

        tvGoogleSuggested = (TextView) findViewById(R.id.tvGoogleSuggested);
        tvAppCalculated = (TextView) findViewById(R.id.tvAppCalculated);

        if (launchCollectionMode) {

            findViewById(R.id.llBottom).setVisibility(View.GONE);
        }

        startLookingForRequest();

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        runTestCase();
    }

    private void startLookingForRequest() {

        requestManager = RequestManager.getInstance(this);
        requestManager.setOnRequestListener(new RequestManager.OnRequestListener() {

            @Override
            public void onRequestArrived(final DeliveryRequest deliveryRequest) {

                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(deliveryRequest.getStartLat(), deliveryRequest.getDestinationLon()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

                map.moveCamera(center);
                map.animateCamera(zoom);

                /*CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(deliveryRequest.getStartLat(), deliveryRequest.getDestinationLon()))
                        .zoom(12)
                        .build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

                showGoogleRecommendedRoute(deliveryRequest);

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        next(deliveryRequest);
                    }
                });
            }
        });
    }

    private void next(final DeliveryRequest deliveryRequest) {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (!launchCollectionMode) {

                    showAppRecommendedRoute(deliveryRequest);

                } else {

                    footPrintCollectionSimulation();
                }
            }

        }, 0);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (!launchCollectionMode) {

                    TestManager.optimizeLast = true;

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            TestManager.optimizeLast = false;
                            launchCollectionMode = true;

                            startActivity(new Intent(DriverMapActivity.this, DriverMapActivity.class));
                            finish();
                        }
                    });
                }*/

                //launchCollectionMode = false;
                launchCollectionMode = !launchCollectionMode;

                startActivity(new Intent(DriverMapActivity.this, DriverMapActivity.class));
                finish();
            }
        });
    }

    private void footPrintCollectionSimulation() {

        final ArrayList<LatLng> points = new ArrayList<LatLng>();

        final ArrayList<Polyline> polylinesTemp = new ArrayList<>();

        final ArrayList<Marker> markers = new ArrayList<>();


        TestManager.getTestFootPrints(new TestManager.OnTestFootPrintListener() {

            @Override
            public void onNextFootPrint(final LatLng latLng) {

                points.add(latLng);

                final PolylineOptions polyOptions = new PolylineOptions();
                int color = Color.argb(255, 0, 255, 0);
                polyOptions.color(color);
                polyOptions.width(18);
                polyOptions.addAll(points);

                for (Polyline poly : polylinesTemp) {
                    poly.remove();
                }

                for (Marker marker : markers) {
                    marker.remove();
                }

                Polyline polyline = map.addPolyline(polyOptions);
                polylinesTemp.add(polyline);

                CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);

                map.moveCamera(center);
                map.animateCamera(zoom);

                MarkerOptions options = new MarkerOptions();
                options.position(latLng);

                Marker marker = map.addMarker(options);
                markers.add(marker);
            }
        });
    }

    private void showAppRecommendedRoute(DeliveryRequest deliveryRequest) {

        showRoute(deliveryRequest, true);

    }

    private void runTestCase() {

        final TestManager testManager = new TestManager(this);
        testManager.setRequestManager(requestManager);
        testManager.start();
    }

    public void showGoogleRecommendedRoute(DeliveryRequest deliveryRequest) {

        showRoute(deliveryRequest, false);
    }

    public void showRoute(DeliveryRequest deliveryRequest, final boolean addRecommendation) {

        if (!Util.Operations.isOnline(this)) {

            Toast.makeText(this, "No internet connectivity", Toast.LENGTH_SHORT).show();
            return;
        }

        double startLat = deliveryRequest.getStartLat();
        double startLon = deliveryRequest.getStartLon();

        final LatLng start = new LatLng(startLat, startLon);

        final double endLat = deliveryRequest.getDestinationLat();
        final double endLon = deliveryRequest.getDestinationLon();

        final LatLng end = new LatLng(endLat, endLon);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Please wait.", "Fetching route information.", true);

        RoutingListener routeListener = new RoutingListener() {

            @Override
            public void onRoutingFailure(RouteException e) {

                // The Routing request failed
                progressDialog.dismiss();

                if (e != null) {

                    Toast.makeText(DriverMapActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(DriverMapActivity.this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoutingStart() {

                // The Routing Request starts
            }

            @Override
            public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

                progressDialog.dismiss();

                CameraUpdate center = CameraUpdateFactory.newLatLng(start);
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                map.moveCamera(center);

                /*if (polyLines.size() > 0) {

                    for (Polyline poly : polyLines) {
                        poly.remove();
                    }
                }*/

                polyLines = new ArrayList<>();

                //add showGoogleRecommendedRoute(s) to the map.
                for (int i = 0; i < route.size(); i++) {

                    //In case of more than 5 alternative routes
                    int color = Color.argb(255, 0, 0, 255 / (i + 1));

                    if (addRecommendation) {

                        color = Color.argb(255, 255 / (i + 1), 0, 0);
                    }

                    PolylineOptions polyOptions = new PolylineOptions();
                    polyOptions.color(color);
                    polyOptions.width(10 + i * 3);
                    polyOptions.addAll(route.get(i).getPoints());
                    Polyline polyline = map.addPolyline(polyOptions);
                    polyLines.add(polyline);

                    //Toast.makeText(getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
                }


                if (route.size() > 0) {

                    Route route1 = route.get(shortestRouteIndex);
                    String distance = route1.getDistanceText();
                    String time = route1.getDurationText();

                    if (addRecommendation) {

                        tvAppCalculated.setText("Distance : " + distance + ", Duration : " + time);

                    } else {

                        tvGoogleSuggested.setText("Distance : " + distance + ", Duration : " + time);
                    }
                }


                // Start marker
                MarkerOptions options = new MarkerOptions();
                options.position(start);
                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));
                map.addMarker(options);

                // End marker
                options = new MarkerOptions();
                options.position(end);
                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));
                map.addMarker(options);

            }

            @Override
            public void onRoutingCancelled() {
                Log.i(LOG_TAG, "Routing was cancelled.");

                // The Routing request failed
                progressDialog.dismiss();
            }
        };

        ArrayList<LatLng> recommended = null;

        if (addRecommendation) {

            recommended = deliveryRequest.getRecommendedPathPoints();

            if (recommended == null || recommended.size() == 0) {

                // The Routing request failed
                progressDialog.dismiss();

                return;
            }
        }

        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(routeListener)
                .alternativeRoutes(false)
                .waypoints(start, end, recommended)
                .build();

        routing.execute();
    }

}
