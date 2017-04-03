package com.tib.indoor_mapper.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Provides an API to broadcast a message to the LocalBroadcaster of
 * the runtime. This could then be consumed by any BroadcastReceiver.
 */
public class GeoFenceBroadcaster {

    static final String GEOFENCE_BROADCAST_INTENT_FILTER = "location-alert";
    private Context context;

    void initialise(Context context, BroadcastReceiver broadcastReceiver) {
        this.context = context;
        LocalBroadcastManager
                .getInstance(context)
                .registerReceiver(broadcastReceiver,
                    new IntentFilter(GEOFENCE_BROADCAST_INTENT_FILTER));
    }

    void broadcast(Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
