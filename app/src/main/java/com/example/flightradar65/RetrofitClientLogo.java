package com.example.flightradar65;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitClientLogo {
    private static final String BASE_URL = "https://images.kiwi.com/";

    private static final Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL);

    private static final Retrofit retrofit = builder.build();

    private static final OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
