package com.h5170043.muhammed_cagatay_mercan_final.network;

import com.h5170043.muhammed_cagatay_mercan_final.model.Movies;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieService {

    @GET("filmler.json")
    Observable<Movies> getMovies();
}
