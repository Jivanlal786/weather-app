package com.example.weatherforecast.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Call<CallbackResponse> sendData(

            @Query("q") String cityName,
            @Query("units") String units,
            @Query("appid") String appId
    );

}
