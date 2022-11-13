package com.example.flightradar65;

import com.example.flightradar65.data.Dataset;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponse();
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseHex(@Query("hex") String hexAdress);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseReg(@Query("reg_number") String regNumber);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseAirIcao(@Query("airline_icao") String airlineIcao);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseAirFlag(@Query("flag") String airlineFlag);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseFltIcao(@Query("flight_icao") String flightIcao);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseFltNo(@Query("flight_number") String flightNumber);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseDepIcao(@Query("dep_icao") String depIcao);
    @GET("api/v9/flights?api_key=e3c9c5c2-e0e4-40e7-b270-16866714b9dc")
    Call<Dataset> getApiResponseArrIcao(@Query("arr_icao") String arrIcao);
}
