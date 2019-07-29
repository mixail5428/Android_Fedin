package com.example.lesson_7_fedin;

import com.example.lesson_7_fedin.bridge.Bridges;
import com.example.lesson_7_fedin.bridgeAPI.BridgeAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controler {

    public static final String BASE_URL = "https://gdemost.handh.ru/";
    private static Controler controler;
    private BridgeAPI bridgeAPI;

    public static BridgeAPI getBridgeAPI() {
        if (controler == null)
            controler = new Controler();
        return controler.bridgeAPI;
    }


    private Controler() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        bridgeAPI = retrofit.create(BridgeAPI.class);
    }
}
