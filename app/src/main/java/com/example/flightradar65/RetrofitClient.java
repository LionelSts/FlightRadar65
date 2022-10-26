package com.example.flightradar65;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://airlabs.co/";

    private static final Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create());

    private static final Retrofit retrofit = builder.build();

    private static final OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
