package com.example.diorous.quanlykhachsan.Application;

import android.app.Application;

import com.example.diorous.quanlykhachsan.Api.ApiInterface;
import com.example.diorous.quanlykhachsan.Api.UrlParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelApplication extends Application {

    public static ApiInterface apiInterface;

    public HotelApplication() {

        apiInterface = getClient().create(ApiInterface.class);
    }

    public Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(UrlParams.urlLocal)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
