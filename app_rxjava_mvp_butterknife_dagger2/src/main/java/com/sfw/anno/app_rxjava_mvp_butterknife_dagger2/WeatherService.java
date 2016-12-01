package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wushufeng on 2016/11/30.
 */

public interface WeatherService {
    @GET("v1/weather/query")
    Observable<WeatherBean> getWeather(
            @Query("key") String key,
            @Query("city") String city,
            @Query("province") String province
    );
}
