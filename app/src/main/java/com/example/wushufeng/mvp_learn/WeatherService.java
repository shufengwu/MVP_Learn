package com.example.wushufeng.mvp_learn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wushufeng on 2016/11/30.
 */

public interface WeatherService {
    @GET("v1/weather/query")
    Call<WeatherBean> getWeather(
            @Query("key") String key,
            @Query("city") String city,
            @Query("province") String province
    );
}
