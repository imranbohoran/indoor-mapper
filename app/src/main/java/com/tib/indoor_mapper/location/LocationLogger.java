package com.tib.indoor_mapper.location;

import android.content.Context;
import android.util.Log;

import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class LocationLogger {

    private static final String TAG = "LocationLogger";
    private static final int UPDATES_RETRIEVAL_INTERVAL_MILLIS = 1000;
    private IALocationManager locationManager;

    private IALocationListener locationListener;

    public static LocationLogger create(Context context, LocationManagerFactory locationManagerFactory,
                                        LocationListenerFactory listenerFactory, LocationStoreFactory locationStoreFactory) {
        return new LocationLogger(context, locationManagerFactory, listenerFactory, locationStoreFactory);
    }

    private LocationLogger(final Context context, LocationManagerFactory locationManagerFactory,
                           LocationListenerFactory listenerFactory, LocationStoreFactory locationStoreFactory) {
        locationManager = locationManagerFactory.createIALocationManager(context);
        locationListener = listenerFactory.createIALocationListener(context, locationStoreFactory);
    }

    public void startLogging() {
        IALocationRequest iaLocationRequest = IALocationRequest
                .create()
                .setFastestInterval(UPDATES_RETRIEVAL_INTERVAL_MILLIS);
        Log.i(TAG, "Location updates requested with intents");
        locationManager.requestLocationUpdates(
                iaLocationRequest,
                locationListener);
    }

    public void stopLogging() {
        locationManager.removeLocationUpdates(locationListener);
    }

    public void destroy() {
        Log.i(TAG, "Destroying location logger");
        locationManager.destroy();
    }

}
