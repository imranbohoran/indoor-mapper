package com.tib.indoor_mapper.location;

import com.firebase.client.Firebase;

public class LocationStoreFactory {

    public Firebase createFirebase(String firebaseUrl) {
        return new Firebase(firebaseUrl);
    }
}
