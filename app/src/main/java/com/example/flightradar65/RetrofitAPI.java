package com.example.flightradar65;

import com.example.flightradar65.data.Dataset;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponse();
}
