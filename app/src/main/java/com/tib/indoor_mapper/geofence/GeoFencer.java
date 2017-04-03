package com.tib.indoor_mapper.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;

import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.tib.indoor_mapper.LocationListenerFactory;
import com.tib.indoor_mapper.location.LocationManagerFactory;

public class GeoFencer {

    private static final int UPDATES_RETRIEVAL_INTERVAL = 5000;
    private IALocationManager locationManager;
    private LocationListenerFactory locationListenerFactory;
    private GeoFenceBroadcaster geoFenceBroadcaster;
    private Context context;
    private BroadcastReceiver broadcastReceiver;

    public GeoFencer(Context context, GeoFenceBroadcaster geoFenceBroadcaster,
                     BroadcastReceiver broadcastReceiver, LocationManagerFactory locationManagerFactory,
                     LocationListenerFactory locationListenerFactory) {
        this.geoFenceBroadcaster = geoFenceBroadcaster;
        this.context = context;
        this.broadcastReceiver = broadcastReceiver;
        this.locationManager = locationManagerFactory.createIALocationManager(context);
        this.locationListenerFactory = locationListenerFactory;
    }

    /**
     * Setups geofencing by initialising a GeoFenceBroadcaster and requesting
     * a Indoor Atlast location manager for location updates.
     * The location request specifies the updates retrieval interval.
     * And an Indoor Atlas location listener instance implemented as a GeoFenceLocationListener
     * is created with access to the broadcaster.
     */
    public void setupGeoFence(Location location) {
        IALocationListener iaListener = locationListenerFactory.createGeoFencingIAListener(location, geoFenceBroadcaster);
        IALocationRequest locationRequest = IALocationRequest
                .create()
                .setFastestInterval(UPDATES_RETRIEVAL_INTERVAL);

        geoFenceBroadcaster.initialise(context, broadcastReceiver);
        locationManager.requestLocationUpdates(locationRequest, iaListener);
    }
}
