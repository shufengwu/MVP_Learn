package com.sfw.app_rxjava_mvp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public class WeatherModel implements IWeatherModel {

    private final String AppKey = "12c5afd699940";


    @Override
    public Observable<WeatherBean> loadW() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        Observable<WeatherBean> res = weatherService.getWeather(AppKey, "朝阳", "北京")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return res;
    }

}
