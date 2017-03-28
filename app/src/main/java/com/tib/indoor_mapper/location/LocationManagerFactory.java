package com.tib.indoor_mapper.location;

import android.content.Context;

import com.indooratlas.android.sdk.IALocationManager;

public class LocationManagerFactory {

    public IALocationManager createIALocationManager(Context context) {
        return IALocationManager.create(context);
    }
}
