package com.example.flightradar65.ui.map;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightradar65.R;
import com.example.flightradar65.RetrofitAPI;
import com.example.flightradar65.RetrofitClient;
import com.example.flightradar65.data.ApiResponse;
import com.example.flightradar65.data.Dataset;
import com.example.flightradar65.ui.dashboard.DashboardViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StyleSpan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener {
    private Dataset searchDataset;
    LatLng planePos = new LatLng(0, 0);
    float planeHead = 0;
    String planeName = "Default Name";
    String planeDesc = "Default Desc";

    Marker[] pathMarker = new Marker[2];
    Polyline[] pathPolyline = new Polyline[2];

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    SupportMapFragment mapFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");

        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);
        Context context = requireActivity().getApplicationContext();
        DashboardViewModel viewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
        viewModel.getDataset().observe(getViewLifecycleOwner(), dataset -> searchDataset = dataset);

        // Initialize map fragment
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapPlanes);
        // Async map
        assert mapFragment != null;
        mapFragment.getMapAsync(googleMap -> {
            // When map is loaded
            RetrofitAPI service = RetrofitClient.createService(RetrofitAPI.class);
            Call<Dataset> callAsync = service.getApiResponse();
            callAsync.enqueue(new Callback<Dataset>() {
                @Override
                public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                    Dataset dataset = response.body();
                    assert dataset != null;
                    for (ApiResponse apiresponse:dataset.getResponse()) {
                        planeName = apiresponse.getFlightIcao();
                        double latitude = apiresponse.getLat();
                        double longitude = apiresponse.getLng();
                        planePos = new LatLng(latitude, longitude);
                        planeHead = apiresponse.getDir()+180;
                        planeDesc = apiresponse.getDepIcao() + "-" + apiresponse.getArrIcao();
                        googleMap.setOnMarkerClickListener(MapFragment.this);
                        Objects.requireNonNull(googleMap.addMarker(
                                new MarkerOptions()
                                        .position(planePos)
                                        .flat(true)
                                        .title(planeName)
                                        .snippet(planeDesc)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion_little))
                                        .rotation(planeHead)
                                        .anchor(0.5F, 0.5F)
                        )).setTag(new String[]{apiresponse.getDepIcao(), apiresponse.getArrIcao(), String.valueOf(apiresponse.getLat()), String.valueOf(apiresponse.getLng())});
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                    System.out.println(throwable);
                }
            });

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(planePos, 1));

            Button loadButton= view.findViewById(R.id.buttonReload);
            loadButton.setOnClickListener(view1 -> {
                if(searchDataset != null){
                    googleMap.clear();
                    for (ApiResponse apiresponse:searchDataset.getResponse()) {
                        planeName = apiresponse.getFlightIcao();
                        double latitude = apiresponse.getLat();
                        double longitude = apiresponse.getLng();
                        planePos = new LatLng(latitude, longitude);
                        planeHead = apiresponse.getDir()+180;
                        planeDesc = apiresponse.getDepIcao() + "-" + apiresponse.getArrIcao();
                        googleMap.setOnMarkerClickListener(MapFragment.this);
                        Objects.requireNonNull(googleMap.addMarker(
                                new MarkerOptions()
                                        .position(planePos)
                                        .title(planeName)
                                        .flat(true)
                                        .snippet(planeDesc)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion_little))
                                        .rotation(planeHead)
                                        .anchor(0.5F, 0.5F))).setTag(new String[]{apiresponse.getDepIcao(), apiresponse.getArrIcao()});

                    }
                }
            });
        });
        return view;
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        final String[][] depInfos = new String[2][2];
        String[][] arrInfos = new String[2][2];
        String[] infos = new String[4];
        if(Objects.requireNonNull(marker.getTag()).getClass().equals(String.class)){
            infos[0]= (String) marker.getTag();
        }else if(Objects.requireNonNull(marker.getTag()).getClass().equals(String[].class)){
            infos = (String[]) marker.getTag();
            String[] finalInfos = infos;
            mapFragment.getMapAsync(googleMap -> {
                if(pathPolyline[0] != null){
                    for (Polyline polyline:pathPolyline) {
                        polyline.remove();
                    }
                    pathPolyline = new Polyline[2];
                }
                if(pathMarker[0] != null){
                    for (Marker marker1:pathMarker) {
                        marker1.remove();
                    }
                    pathMarker = new Marker[2];
                }
                assert finalInfos != null;
                databaseReference.child("Airportsv2").child("0").child(finalInfos[0]).child("coordinates").get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getContext(), "Error getting data", Toast.LENGTH_LONG).show();
                    }
                    else {
                        depInfos[0] = Objects.requireNonNull(task.getResult().getValue()).toString().split(", ");
                    }
                });
                databaseReference.child("Airportsv2").child("0").child(finalInfos[1]).child("coordinates").get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getContext(), "Error getting data", Toast.LENGTH_LONG).show();
                    }
                    else {
                        arrInfos[0] = Objects.requireNonNull(task.getResult().getValue()).toString().split(", ");
                    }
                });
                databaseReference.child("Airportsv2").child("0").child(finalInfos[0]).child("name").get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getContext(), "Error getting data", Toast.LENGTH_LONG).show();
                    }
                    else {
                        depInfos[1][0] = Objects.requireNonNull(task.getResult().getValue()).toString();
                    }
                });
                databaseReference.child("Airportsv2").child("0").child(finalInfos[1]).child("name").get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getContext(), "Error getting data", Toast.LENGTH_LONG).show();
                    }
                    else {
                        arrInfos[1][0] = Objects.requireNonNull(task.getResult().getValue()).toString();
                        Objects.requireNonNull( pathMarker[0] = googleMap.addMarker(
                                new MarkerOptions()
                                        .position(new LatLng(parseDouble(depInfos[0][1]), parseDouble(depInfos[0][0])))
                                        .title(finalInfos[0])
                                        .snippet(depInfos[1][0]))).setTag("path");

                        Objects.requireNonNull(pathMarker[1] = googleMap.addMarker(
                                new MarkerOptions()
                                        .position(new LatLng(parseDouble(arrInfos[0][1]), parseDouble(arrInfos[0][0])))
                                        .title(finalInfos[1])
                                        .snippet(arrInfos[1][0]))).setTag("path");

                        List<PatternItem> pattern = Arrays.asList(
                                new Dot(), new Gap(20), new Dash(30), new Gap(20));

                        PolylineOptions polylineOptions1 = new PolylineOptions()
                                .add(new LatLng(parseDouble(depInfos[0][1]), parseDouble(depInfos[0][0])))
                                .addSpan(new StyleSpan(Color.MAGENTA))
                                .add(new LatLng(parseDouble(finalInfos[2]), parseDouble(finalInfos[3])))
                                .geodesic(true);

                        PolylineOptions polylineOptions2 = new PolylineOptions()
                                .add(new LatLng(parseDouble(finalInfos[2]), parseDouble(finalInfos[3])))
                                .addSpan(new StyleSpan(Color.BLUE))
                                .add(new LatLng(parseDouble(arrInfos[0][1]), parseDouble(arrInfos[0][0])))
                                .geodesic(true);

                        pathPolyline[0] = googleMap.addPolyline(polylineOptions2);
                        pathPolyline[0].setPattern(pattern);
                        pathPolyline[0].setTag("path");

                        pathPolyline[1] = googleMap.addPolyline(polylineOptions1);
                        pathPolyline[1].setTag("path");
                    }
                });
            });
        }
        return false;
    }
}