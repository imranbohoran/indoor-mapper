package com.tib.indoor_mapper.location;

import android.content.Context;

import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class LocationLogger {

    private IALocationManager locationManager;

    private IALocationListener locationListener;
    public static LocationLogger create(Context context, IALocationListenerFactory listenerFactory, FirebaseFactory firebaseFactory) {
        return new LocationLogger(context, listenerFactory, firebaseFactory);
    }

    private LocationLogger(final Context context, IALocationListenerFactory listenerFactory, FirebaseFactory firebaseFactory) {
        locationManager = IALocationManager.create(context);
        locationListener = listenerFactory.create(context, firebaseFactory);
    }

    public void startLogging() {
        IALocationRequest iaLocationRequest = IALocationRequest
                .create()
                .setFastestInterval(1000);
        locationManager.requestLocationUpdates(
                iaLocationRequest,
                locationListener);
    }

    public void stopLogging() {
        locationManager.removeLocationUpdates(locationListener);
    }
}
