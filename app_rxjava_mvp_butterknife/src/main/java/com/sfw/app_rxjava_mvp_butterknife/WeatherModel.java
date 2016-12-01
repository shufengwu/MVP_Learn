package com.sfw.app_rxjava_mvp_butterknife;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> weatherBean2List(WeatherBean weatherBean) {
        List<String> list = new ArrayList<String>();
        List<WeatherBean.ResultBean.FutureBean> futureList = weatherBean.getResult().get(0).getFuture();
        for (int i = 0; i < futureList.size(); i++) {
            list.add("\n" + futureList.get(i).getDate() + "\n"
                    + "白天：" + futureList.get(i).getDayTime() + "\n"
                    + "夜间：" + futureList.get(i).getNight() + "\n"
                    + futureList.get(i).getTemperature() + "\n"
                    + futureList.get(i).getWeek() + "\n"
                    + futureList.get(i).getWind() + "\n");
        }
        return list;
    }

}
