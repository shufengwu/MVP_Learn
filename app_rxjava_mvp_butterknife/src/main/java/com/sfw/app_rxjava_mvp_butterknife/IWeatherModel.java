package com.sfw.app_rxjava_mvp_butterknife;

import java.util.List;

import rx.Observable;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public interface IWeatherModel {
    public Observable<WeatherBean> loadW();
    public List<String> weatherBean2List(WeatherBean weatherBean);
}
