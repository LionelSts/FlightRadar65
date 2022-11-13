package com.example.flightradar65.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightradar65.R;
import com.example.flightradar65.RetrofitAPI;
import com.example.flightradar65.RetrofitClient;
import com.example.flightradar65.data.Dataset;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    private SearchView searchView;
    FlightsInfoRetriever recyclerAdapter;
    String[] categories = {"No filter","ICAO24 Hex address", "Aircraft Registration number", "Airline ICAO code", "Airline Country ISO 2 code", "Flight ICAO code-number", "Flight number", "Departure Airport ICAO code", "Arrival Airport ICAO code"};
    RetrofitAPI service = RetrofitClient.createService(RetrofitAPI.class);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Context context = requireActivity().getApplicationContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Spinner spinnerCategories = view.findViewById(R.id.spinnerSearch);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategories.setAdapter(adapter);

        // When user select a List-Item.
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchView = view.findViewById(R.id.searchBar);

        // Add the following lines to create RecyclerView
        // Add RecyclerView member
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewSearch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerAdapter = new FlightsInfoRetriever();
        recyclerView.setAdapter(recyclerAdapter);

        Call<Dataset> callAsync = service.getApiResponse();
        callAsync.enqueue(new Callback<Dataset>() {
            @Override
            public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                Dataset dataset = response.body();
                recyclerAdapter.loadDataset(dataset);
                Toast.makeText(context, "Data acquired", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                System.out.println(throwable);
                Toast.makeText(context, "Data not acquired", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        String category = (String) adapter.getItem(position);
        Context context = requireActivity().getApplicationContext();
        CharSequence query = searchView.getQuery();
        Call<Dataset> callAsync;
        switch (category){
            case "ICAO24 Hex address":
                callAsync = service.getApiResponseHex(query.toString());
                break;
            case "Aircraft Registration number":
                callAsync = service.getApiResponseReg(query.toString());
                break;
            case "Airline ICAO code":
                callAsync = service.getApiResponseAirIcao(query.toString());
                break;
            case "Airline Country ISO 2 code":
                callAsync = service.getApiResponseAirFlag(query.toString());
                break;
            case "Flight ICAO code-number":
                callAsync = service.getApiResponseFltIcao(query.toString());
                break;
            case "Flight number":
                callAsync = service.getApiResponseFltNo(query.toString());
                break;
            case "Departure Airport ICAO code":
                callAsync = service.getApiResponseDepIcao(query.toString());
                break;
            case "Arrival Airport ICAO code":
                callAsync = service.getApiResponseArrIcao(query.toString());
                break;
            default:
                callAsync = service.getApiResponse();
                break;
        }
        callAsync.enqueue(new Callback<Dataset>() {
            @Override
            public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                Dataset dataset = response.body();
                recyclerAdapter.loadDataset(dataset);
                Toast.makeText(context, "Data acquired", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                System.out.println(throwable);
                Toast.makeText(context, "Data not acquired", Toast.LENGTH_LONG).show();
            }
        });

    }
}