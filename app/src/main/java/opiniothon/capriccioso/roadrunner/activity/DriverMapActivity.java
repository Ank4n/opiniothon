package opiniothon.capriccioso.roadrunner.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import opiniothon.capriccioso.roadrunner.R;

public class DriverMapActivity extends AppCompatActivity implements RoutingListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String LOG_TAG = "DriverMapActivity";

    private static final int COLOR_GOOGLE_PATH = Color.BLUE;
    private static final int TRAVELLED_PATH = Color.GREEN;
    private static final int SUGGESTED_PATH = Color.RED;

    private static final int[] COLORS = new int[]{COLOR_GOOGLE_PATH, TRAVELLED_PATH, SUGGESTED_PATH};

    private GoogleMap map;

    private LatLng start;
    private LatLng end;

    protected GoogleApiClient mGoogleApiClient;

    private ProgressDialog progressDialog;

    private List<Polyline> polyLines;


    private static final LatLngBounds BOUNDS_BANGALURU = new LatLngBounds(
            new LatLng(77.40692138671875, 13.124954649619102),
            new LatLng(77.84912109375, 12.736800512460297));

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

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        MapsInitializer.initialize(this);
        mGoogleApiClient.connect();

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

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(12.94717095, 77.62842357));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        map.moveCamera(center);
        map.animateCamera(zoom);

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

                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                map.moveCamera(center);
                map.animateCamera(zoom);
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

        map.setMyLocationEnabled(true);

        map.getUiSettings().setZoomControlsEnabled(true);

        start = new LatLng(12.94717095, 77.62842357);
        end = new LatLng(12.90276004 , 77.60727167);

        sendRequest();
    }


    public void sendRequest() {

        if (Util.Operations.isOnline(this)) {

            showGoogleRecommendedRoute();

        } else {

            Toast.makeText(this, "No internet connectivity", Toast.LENGTH_SHORT).show();
        }
    }

    public void showGoogleRecommendedRoute() {

        if (start == null || end == null) {

            if (start == null) {

                Toast.makeText(this, "Starting point is not available.", Toast.LENGTH_SHORT).show();
            }

            if (end == null) {

                Toast.makeText(this, "Destination point is not available.", Toast.LENGTH_SHORT).show();
            }

        } else {

            LatLng via = new LatLng(12.92376915, 77.61471748);

            progressDialog = ProgressDialog.show(this, "Please wait.", "Fetching showGoogleRecommendedRoute information.", true);

            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(false)
                    .waypoints(start, via, end)
                    .build();

            routing.execute();
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {

        // The Routing request failed
        progressDialog.dismiss();

        if (e != null) {

            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {

        // The Routing Request starts
    }

    @Override
    public void onRoutingSuccess(List<Route> route, int shortestRouteIndex) {

        progressDialog.dismiss();

        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        map.moveCamera(center);

        if (polyLines.size() > 0) {

            for (Polyline poly : polyLines) {
                poly.remove();
            }
        }

        polyLines = new ArrayList<>();

        //add showGoogleRecommendedRoute(s) to the map.
        for (int i = 0; i < route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(COLORS[colorIndex]);
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = map.addPolyline(polyOptions);
            polyLines.add(polyline);

            Toast.makeText(getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.v(LOG_TAG, connectionResult.toString());
    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
