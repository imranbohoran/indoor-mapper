package com.tib.indoor_mapper.geofence;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;

import static com.tib.indoor_mapper.geofence.GeoFenceBroadcaster.GEOFENCE_BROADCAST_INTENT_FILTER;

public class GeoFenceLocationListener implements IALocationListener {

    public static final int GEO_FENCED_RANGE_IN_METERS = 3;
    public static final String GEOFENCE_BROADCAST_LOCATION = "within-range";
    public static final String GEOFENCE_BROADCAST_YTA = "yet-to-arrive";
    private Location geoFenceLocation;
    private GeoFenceBroadcaster geoFenceBroadcaster;

    /**
     * Making this private an instance of this listener can never be created
     * without providing the necessary components.
     */
    private GeoFenceLocationListener(){}

    public GeoFenceLocationListener(Location geoFenceLocation, GeoFenceBroadcaster geoFenceBroadcaster) {
        this.geoFenceLocation = geoFenceLocation;
        this.geoFenceBroadcaster = geoFenceBroadcaster;
    }

    /**
     * Gets triggered when the location changes.
     * If the location recived as part of the location change is within range to the
     * geofence location configured, then a message is broadcasted with the current location.
     *
     * @param iaLocation The new location
     */
    @Override
    public void onLocationChanged(IALocation iaLocation) {
        Location currentLocation = iaLocation.toLocation();
        if(geoFenceLocation.distanceTo(currentLocation) < GEO_FENCED_RANGE_IN_METERS) {
            Intent geoFenceIntent = new Intent(GEOFENCE_BROADCAST_INTENT_FILTER);
            geoFenceIntent.putExtra(GEOFENCE_BROADCAST_LOCATION, currentLocation);
            geoFenceBroadcaster.broadcast(geoFenceIntent);
        } else {
            Intent geoFenceIntent = new Intent(GEOFENCE_BROADCAST_INTENT_FILTER);
            geoFenceIntent.putExtra(GEOFENCE_BROADCAST_YTA, currentLocation);
            geoFenceBroadcaster.broadcast(geoFenceIntent);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}
}
