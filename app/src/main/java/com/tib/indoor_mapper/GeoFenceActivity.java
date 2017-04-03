package com.tib.indoor_mapper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.tib.indoor_mapper.geofence.GeoFenceBroadcaster;
import com.tib.indoor_mapper.geofence.GeoFencer;
import com.tib.indoor_mapper.location.LocationManagerFactory;

import static com.tib.indoor_mapper.geofence.GeoFenceLocationListener.GEOFENCE_BROADCAST_LOCATION;
import static com.tib.indoor_mapper.geofence.GeoFenceLocationListener.GEOFENCE_BROADCAST_YTA;

/**
 * GeoFence activity that setsup the coordinates for geofencing
 * and initiates the process.
 *
 * The implementation for geofencing is delegated to <code>GeoFencer.java</code>
 *
 * There is also a debug feature to check the current location.
 *
 */
public class GeoFenceActivity extends AppCompatActivity {

    private static final String TAG = "GEO-FENCING";

    private IALocationManager debugIALocationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geofence);
    }

    public void geofence(View view) {
        Log.i(TAG, "Starting geo fencing");
        TextView longitude = (TextView) findViewById(R.id.text_lng);
        TextView latitude = (TextView) findViewById(R.id.text_lat);

        TextView currentLocation = (TextView) findViewById(R.id.txt_info_current_location);
        currentLocation.setText("Current set location - Lng: " + longitude.getText() + ", Lat: " + latitude.getText());
        GeoFencer geoFencer = new GeoFencer(
                this,
                new GeoFenceBroadcaster(),
                getBroadcastReceiver(),
                new LocationManagerFactory(),
                new LocationListenerFactory());
        Location location = new Location("geofence-location");
        location.setLongitude(Double.valueOf(longitude.getText().toString()));
        location.setLatitude(Double.valueOf(latitude.getText().toString()));
        geoFencer.setupGeoFence(location);
    }

    private BroadcastReceiver getBroadcastReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.hasExtra(GEOFENCE_BROADCAST_LOCATION)) {
                    ((TextView) findViewById(R.id.lbl_current_status)).setText(R.string.geo_fence_in_range_message);
                } else if(intent.hasExtra(GEOFENCE_BROADCAST_YTA)) {
                    ((TextView) findViewById(R.id.lbl_current_status)).setText(R.string.geo_fence_to_be_reached_message);
                }
            }
        };
    }

    public void getCurrentLocation(View view) {
        IALocationRequest locationRequest = IALocationRequest.create().setFastestInterval(2000);
        debugIALocationManager = IALocationManager.create(this);
        debugIALocationManager.requestLocationUpdates(locationRequest, debugLocationListener);
    }

    private IALocationListener debugLocationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            ((TextView) findViewById(R.id.lbl_current_location)).setText(
                    "Lng: "+ iaLocation.getLongitude() + ", Lat: "+ iaLocation.getLatitude()
            );
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };

    public void clearCurrentLocation(View view) {
        debugIALocationManager.removeLocationUpdates(debugLocationListener);
        ((TextView) findViewById(R.id.lbl_current_location)).setText("");
    }

    @Override
    protected void onDestroy() {
        if(debugIALocationManager != null) {
            debugIALocationManager.removeLocationUpdates(debugLocationListener);
            debugIALocationManager.destroy();
        }
        super.onDestroy();
    }
}
