package com.h5170043.muhammed_cagatay_mercan_final.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieServiceApi {
    private static Retrofit instance;
    private static final String baseUrl =
            "https://raw.githubusercontent.com/mercan6464/h5170043muhammedcagataymercan/main/webservice/";
    private static OkHttpClient okHttpClient;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient().newBuilder().build();
        }
        return okHttpClient;
    }

    public static MovieService getMovieService() {
        return getInstance().create(MovieService.class);
    }

    private MovieServiceApi(){}
}
