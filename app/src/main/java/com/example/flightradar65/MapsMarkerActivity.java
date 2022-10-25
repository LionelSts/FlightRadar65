package com.example.flightradar65;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapPlanes);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng sydney = new LatLng(-33.867, 151.206);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        googleMap.addMarker(
                new MarkerOptions()
                        .position(sydney)
                        .title("Marker in Sydney")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion_little))
                        .rotation(-45.0f));
    }
}
