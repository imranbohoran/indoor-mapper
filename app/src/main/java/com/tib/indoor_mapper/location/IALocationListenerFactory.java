package com.tib.indoor_mapper.location;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;

public class IALocationListenerFactory {

    public IALocationListener create(final Context context, final FirebaseFactory firebaseFatory) {
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
}
