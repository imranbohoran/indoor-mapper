package com.tib.indoor_mapper.geofence;

import android.content.Intent;
import android.location.Location;

import com.indooratlas.android.sdk.IALocation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Running with the RoboelectricTestRunner so a mock android system
 * is available for when the tests run. Without this we can't get a Location object or
 * any Intents created.
 */
@RunWith(RobolectricTestRunner.class)
public class GeoFenceLocationListenerTest {

    GeoFenceLocationListener locationListener;

    GeoFenceBroadcaster geoFenceBroadcaster;

    @Before
    public void setup() {
        geoFenceBroadcaster = mock(GeoFenceBroadcaster.class);
    }

    @Test
    public void onLocationChanged_shouldSendBroadcastIfLocationWithinRangeOfGeoFenceLocation() {
        Location location = new Location("geofence-location");
        location.setLongitude(1.32222);
        location.setLatitude(2.3);

        locationListener = new GeoFenceLocationListener(location, geoFenceBroadcaster);

        Location newLocation = new Location("geofence-location");
        newLocation.setLongitude(1.32221);
        newLocation.setLatitude(2.3);

        IALocation iaLocation = IALocation.from(newLocation);
        locationListener.onLocationChanged(iaLocation);

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);

        verify(geoFenceBroadcaster).broadcast(captor.capture());

        assertTrue(captor.getValue().hasExtra("within-range"));
    }

    @Test
    public void onLocationChanged_shouldNotBroadcastIfLocationNotInRange() {
        Location location = new Location("geofence-location");
        location.setLongitude(1.32);
        location.setLatitude(2.3);

        locationListener = new GeoFenceLocationListener(location, geoFenceBroadcaster);

        Location newLocation = new Location("geofence-location");
        newLocation.setLongitude(1.31);
        newLocation.setLatitude(2.3);

        IALocation iaLocation = IALocation.from(newLocation);
        locationListener.onLocationChanged(iaLocation);

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);

        verify(geoFenceBroadcaster).broadcast(captor.capture());

        assertTrue(captor.getValue().hasExtra("yet-to-arrive"));
    }
}