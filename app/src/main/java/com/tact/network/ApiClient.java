package com.tact.network;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    static Retrofit retrofit;
    private static final String API_BASE_URL = "http://ca-infosky.azurewebsites.net/";


    private ApiClient()
    {

    }

    public static Retrofit getApiClient()
    {
        createInstance();
        return retrofit;
    }


    private static void createInstance()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);

        httpClient.addInterceptor(logging);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();

    }
}
