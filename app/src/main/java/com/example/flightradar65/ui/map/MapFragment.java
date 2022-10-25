package com.example.flightradar65.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.flightradar65.R;
import com.example.flightradar65.databinding.FragmentMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize map fragment
        SupportMapFragment mapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapPlanes);

        // Async map
        assert mapFragment != null;
        mapFragment.getMapAsync(googleMap -> {
            // When map is loaded
            LatLng planePos = new LatLng(0, 0);
            int planeHead = 0;
            String planeName = "test";
            /*
            URL url = null;
            try {
                url = new URL("http://api.aviationstack.com/v1/flights?access_key=d686215dacae227bc179fd3bea0ba7ea&flight_icao=FDB540");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                con.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                int status = con.getResponseCode();
                Reader streamReader = null;

                if (status > 299) {
                    streamReader = new InputStreamReader(con.getErrorStream());
                } else {
                    streamReader = new InputStreamReader(con.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(planePos, 1));

            googleMap.addMarker(
                new MarkerOptions()
                    .position(planePos)
                    .title(planeName)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion_little))
                    .rotation(planeHead));
        });
        return view;
    }
}