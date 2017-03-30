package com.tib.indoor_mapper.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class GeoFenceBroadcaster {

    public static final String GEOFENCE_BROADCAST_INTENT_FILTER = "location-alert";
    private Context context;

    public void initialise(Context context, BroadcastReceiver broadcastReceiver) {
        this.context = context;
        LocalBroadcastManager
                .getInstance(context)
                .registerReceiver(broadcastReceiver,
                    new IntentFilter(GEOFENCE_BROADCAST_INTENT_FILTER));
    }

    public void broadcast(Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
