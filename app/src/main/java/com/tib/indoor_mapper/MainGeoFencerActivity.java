package com.tib.indoor_mapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tib.indoor_mapper.location.LocationManagerFactory;
import com.tib.indoor_mapper.location.LocationStoreFactory;
import com.tib.indoor_mapper.location.LocationListenerFactory;
import com.tib.indoor_mapper.location.LocationLogger;

public class MainGeoFencerActivity extends AppCompatActivity {

    private static final String TAG = "MAIN-ACTIVITY";

    LocationLogger locationLogger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationLogger = LocationLogger.create(
                this,
                new LocationManagerFactory(),
                new LocationListenerFactory(),
                new LocationStoreFactory());

        setContentView(R.layout.acitivity_main);
    }

    public void startLogging(View view) {
        Log.i(TAG, "Start logging location");
        locationLogger.startLogging();
    }

    public void stopLogging(View view) {
        Log.i(TAG, "Stop logging location");
        locationLogger.stopLogging();
    }

    @Override
    protected void onDestroy() {
        locationLogger.destroy();
        super.onDestroy();
    }

    public void startFencing(View view) {
        Log.i(TAG, "Start Geofencing");
    }

}
