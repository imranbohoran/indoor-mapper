package com.tib.indoor_mapper.location;

import android.content.Context;
import android.util.Log;

import com.firebase.client.Firebase;
import com.indooratlas.android.sdk.IALocation;

import java.util.Date;

import java.text.DateFormat;

public class LocationFirebaseStore {

    static final String TAG = "LOCATION-STORE";
    static final String FIREBASE_URL = "https://indoor-mapper-home.firebaseio.com";

    private Firebase firebase;

    public static LocationFirebaseStore create(Context context, FirebaseFactory firebaseFactory) {
        return new LocationFirebaseStore(context, firebaseFactory);
    }

    private LocationFirebaseStore(Context context, FirebaseFactory firebaseFactory) {
        Firebase.setAndroidContext(context);
        firebase = firebaseFactory.create(FIREBASE_URL);
    }

    public void store(IALocation location) {
        Log.i(TAG, "storing location: Lng - "+ location.getLongitude() + ", Lat - "+ location.getLatitude());
        String timestamp = DateFormat.getDateTimeInstance().format(new Date());
        LocationUpdate locationUpdate = new LocationUpdate(
                String.valueOf(location.getLongitude()),
                String.valueOf(location.getLatitude()));

        firebase.child("Home-Indoor").child(timestamp).setValue(locationUpdate);

        Log.i(TAG, "Logged in firebase");
    }

    private static class LocationUpdate {

        final String longitude;
        final String latitude;

        LocationUpdate(String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

    }
}
