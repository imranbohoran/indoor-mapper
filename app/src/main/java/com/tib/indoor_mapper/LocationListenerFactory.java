package com.tib.indoor_mapper;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.tib.indoor_mapper.geofence.GeoFenceBroadcaster;
import com.tib.indoor_mapper.geofence.GeoFenceLocationListener;
import com.tib.indoor_mapper.location.LocationFirebaseStore;
import com.tib.indoor_mapper.location.LocationStoreFactory;

public class LocationListenerFactory {

    public IALocationListener createIALocationListener(final Context context, final LocationStoreFactory firebaseFatory) {
        return new IALocationListener() {
            @Override
            public void onLocationChanged(IALocation iaLocation) {
                Log.i("LocationListener", "Location changed...");
                LocationFirebaseStore.create(context, firebaseFatory).store(iaLocation);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}
        };
    }

    public IALocationListener createGeoFencingIAListener(Location geoFenceLocation, GeoFenceBroadcaster geoFenceBroadcaster) {
        return new GeoFenceLocationListener(geoFenceLocation, geoFenceBroadcaster);
    }
}
