package com.example.lesson_7_fedin.bridgeAPI;

import com.example.lesson_7_fedin.bridge.Bridges;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BridgeAPI {
    @GET("api/v1/bridges/")
    Observable<Bridges> getData();
}
