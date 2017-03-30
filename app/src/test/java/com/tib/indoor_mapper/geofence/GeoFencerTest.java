package com.tib.indoor_mapper.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;

import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.tib.indoor_mapper.LocationListenerFactory;
import com.tib.indoor_mapper.location.LocationManagerFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeoFencerTest {

    GeoFencer geoFencer;

    @Mock
    LocationManagerFactory locationManagerFactory;

    @Mock
    LocationListenerFactory locationListenerFactory;

    @Mock
    private IALocationListener geoFenceListener;

    @Mock
    private Context context;

    @Mock
    private IALocationManager locationManager;

    @Mock
    private GeoFenceBroadcaster geoFenceBroadcaster;

    @Mock
    private BroadcastReceiver broadcastReceiver;

    @Before
    public void setup() {
        when(locationListenerFactory.createGeoFencingIAListener(any(Location.class), eq(geoFenceBroadcaster))).thenReturn(geoFenceListener);
        when(locationManagerFactory.createIALocationManager(context)).thenReturn(locationManager);

        geoFencer = new GeoFencer(context, geoFenceBroadcaster,
                broadcastReceiver, locationManagerFactory, locationListenerFactory);
    }

    @Test
    public void shouldRequestForUpdatesEveryFiveSeconds() {
        Location geoFenceLocation = new Location("geoFence");
        geoFenceLocation.setLatitude(1.32D);
        geoFenceLocation.setLongitude(5.21D);

        geoFencer.setupGeoFence(geoFenceLocation);

        ArgumentCaptor<IALocationRequest> captor = ArgumentCaptor.forClass(IALocationRequest.class);
        verify(locationListenerFactory).createGeoFencingIAListener(geoFenceLocation, geoFenceBroadcaster);
        verify(locationManager).requestLocationUpdates(captor.capture(), eq(geoFenceListener));

        assertThat(captor.getValue().getFastestInterval(), is(5000L));
    }

    @Test
    public void shouldSetupLocalBroadcastManager() {
        Location geoFenceLocation = new Location("geoFence");
        geoFenceLocation.setLatitude(1.32D);
        geoFenceLocation.setLongitude(5.21D);

        geoFencer.setupGeoFence(geoFenceLocation);

        verify(geoFenceBroadcaster).initialise(context, broadcastReceiver);
    }

}