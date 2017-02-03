package com.nobrain.rx_study;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DaumImageSearchApi {

    private static final String API_KEY = "6476af2c2f3439b9952662f733c4d4ed";

    public Observable<SearchResult> getImages(String query) {
        return new Retrofit.Builder()
                .baseUrl("https://apis.daum.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class)
                .getImages(query);
    }


    interface Api {

        @GET("search/image?output=json&apikey=" + API_KEY)
        Observable<SearchResult> getImages(@Query("q") String query);
    }
}
