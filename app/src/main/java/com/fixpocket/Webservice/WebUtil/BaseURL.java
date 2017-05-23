package com.fixpocket.Webservice.WebUtil;

import com.fixpocket.Webservice.FixpocketAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseURL {

    public static final String BASE_URL = "http://fixpocket.com/api/";

    private static Retrofit getBaseUrl() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static FixpocketAPI getFixpocketAPI() {
        return BaseURL.getBaseUrl().create(FixpocketAPI.class);
    }
}
