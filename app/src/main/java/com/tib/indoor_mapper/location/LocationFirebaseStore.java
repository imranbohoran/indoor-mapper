package com.tib.indoor_mapper.location;

import android.content.Context;
import android.util.Log;

import com.firebase.client.Firebase;
import com.indooratlas.android.sdk.IALocation;

import java.util.Date;

import java.text.DateFormat;

/**
 * Provides the functionality of persisting data into Firebase.
 * The data persisted is a Location update and hence the structure of the
 * data is local to this class based on the location provided by the Indoor
 * Atlast apis.
 */
public class LocationFirebaseStore {

    static final String TAG = "LOCATION-STORE";
//    static final String FIREBASE_URL = "https://indoor-mapper-home.firebaseio.com";
    static final String FIREBASE_URL = "https://indoor-mapper.firebaseio.com";

    private Firebase firebase;

    public static LocationFirebaseStore create(Context context, LocationStoreFactory locationStoreFactory) {
        return new LocationFirebaseStore(context, locationStoreFactory);
    }

    private LocationFirebaseStore(Context context, LocationStoreFactory locationStoreFactory) {
        Firebase.setAndroidContext(context);
        firebase = locationStoreFactory.createFirebase(FIREBASE_URL);
    }

    public void store(IALocation location) {
        Log.i(TAG, "storing location: Lng - "+ location.getLongitude() + ", Lat - "+ location.getLatitude());
        String timestamp = DateFormat.getDateTimeInstance().format(new Date());
        LocationUpdate locationUpdate = new LocationUpdate(
                String.valueOf(location.getLongitude()),
                String.valueOf(location.getLatitude()));

        firebase.child("Location-Updates").child(timestamp).setValue(locationUpdate);

        Log.i(TAG, "Logged in firebase");
    }

    public static class LocationUpdate {

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LocationUpdate that = (LocationUpdate) o;

            if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null)
                return false;
            return latitude != null ? latitude.equals(that.latitude) : that.latitude == null;

        }

        @Override
        public int hashCode() {
            int result = longitude != null ? longitude.hashCode() : 0;
            result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "LocationUpdate{" +
                    "longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    '}';
        }
    }
}
