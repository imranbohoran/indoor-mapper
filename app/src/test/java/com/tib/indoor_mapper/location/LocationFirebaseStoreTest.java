package com.tib.indoor_mapper.location;

import android.content.Context;

import com.firebase.client.Firebase;
import com.indooratlas.android.sdk.IALocation;
import com.tib.indoor_mapper.location.LocationFirebaseStore.LocationUpdate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tib.indoor_mapper.location.LocationFirebaseStore.FIREBASE_URL;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationFirebaseStoreTest {

    LocationFirebaseStore firebaseStore;

    @Mock
    Context context;

    @Mock
    IALocation location;

    @Mock
    LocationStoreFactory storeFactory;

    @Mock
    Firebase firebase;

    @Before
    public void setup() {
        when(storeFactory.createFirebase(FIREBASE_URL)).thenReturn(firebase);
        when(firebase.child(anyString())).thenReturn(firebase);

        firebaseStore = LocationFirebaseStore.create(context, storeFactory);

    }

    @Test
    public void shouldStoreLocationWithTimestamp() {
        IALocation location = mock(IALocation.class);

        when(location.getLongitude()).thenReturn(5.1D);
        when(location.getLatitude()).thenReturn(1.3D);
        LocationUpdate locationUpdate = new LocationUpdate("5.1", "1.3");

        firebaseStore.store(location);

        InOrder order = inOrder(firebase);
        order.verify(firebase).child("Location-Updates");
        order.verify(firebase).child(anyString());
        order.verify(firebase).setValue(locationUpdate);
    }
}