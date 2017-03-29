package com.tib.indoor_mapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GeoFenceActivity extends AppCompatActivity {

    private static final String TAG = "GEO-FENCING";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geofence);
    }

    public void geofence(View view) {
        Log.i(TAG, "Starting geo fencing");
        TextView longitude = (TextView) findViewById(R.id.text_lng);
        TextView latitude = (TextView) findViewById(R.id.text_lat);

        TextView currentLocation = (TextView) findViewById(R.id.txt_info_current_location);
        currentLocation.setText("Current set location - Lng: "+ longitude.getText() + ", Lat: "+ latitude.getText());
    }
}
