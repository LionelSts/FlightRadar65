package com.example.flightradar65.ui.map;

import android.content.Context;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment {
    private Dataset searchDataset;
    LatLng planePos = new LatLng(0, 0);
    float planeHead = 0;
    String planeName = "Default Name";
    String planeDesc = "Default Desc";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize view
        View view=inflater.inflate(R.layout.fragment_map, container, false);
        Context context = requireActivity().getApplicationContext();
        DashboardViewModel viewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
        viewModel.getDataset().observe(getViewLifecycleOwner(), dataset -> searchDataset = dataset);

        // Initialize map fragment
        SupportMapFragment mapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapPlanes);
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
                        googleMap.addMarker(
                                new MarkerOptions()
                                        .position(planePos)
                                        .title(planeName)
                                        .snippet(planeDesc)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion_little))
                                        .rotation(planeHead)
                                        .anchor(0.5F,0.5F));
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
                        googleMap.addMarker(
                                new MarkerOptions()
                                        .position(planePos)
                                        .title(planeName)
                                        .snippet(planeDesc)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion_little))
                                        .rotation(planeHead)
                                        .anchor(0.5F,0.5F));

                    }
                    Toast.makeText(context, "Data acquired", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Data not acquired", Toast.LENGTH_LONG).show();
                }
            });
        });
        return view;
    }
}