package com.tib.indoor_mapper.location;

import com.firebase.client.Firebase;

public class FirebaseFactory {

    public Firebase create(String firebaseUrl) {
        return new Firebase(firebaseUrl);
    }
}
