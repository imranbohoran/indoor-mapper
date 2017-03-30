package com.tib.indoor_mapper.location;

import android.content.Context;

import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.tib.indoor_mapper.LocationListenerFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationLoggerTest {

    LocationLogger locationLogger;

    @Mock
    Context context;

    @Mock
    LocationListenerFactory locationListenerFactory;

    @Mock
    LocationStoreFactory locationStoreFactory;

    @Mock
    LocationManagerFactory locationManagerFactory;

    @Mock
    IALocationListener locationListener;

    @Mock
    IALocationManager locationManager;

    @Before
    public void setup() {
        when(locationListenerFactory.createIALocationListener(context, locationStoreFactory)).thenReturn(locationListener);
        when(locationManagerFactory.createIALocationManager(context)).thenReturn(locationManager);
        locationLogger = LocationLogger.create(context, locationManagerFactory, locationListenerFactory, locationStoreFactory);
    }

    @Test
    public void startLogging_shouldRequestForUpdatesEveryOneSecond() {
        locationLogger.startLogging();

        ArgumentCaptor<IALocationRequest> captor = ArgumentCaptor.forClass(IALocationRequest.class);

        verify(locationManager).requestLocationUpdates(captor.capture(), eq(locationListener));

        assertThat(captor.getValue().getFastestInterval(), is(1000L));
    }

    @Test
    public void stopLogging_shouldRemoveLocationUpdates() {
        locationLogger.stopLogging();

        verify(locationManager).removeLocationUpdates(locationListener);
    }

    @Test
    public void destroy_shouldDestroyLoggingManager() {
        locationLogger.destroy();

        verify(locationManager).destroy();
    }

}