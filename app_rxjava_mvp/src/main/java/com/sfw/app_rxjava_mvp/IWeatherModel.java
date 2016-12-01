package com.sfw.app_rxjava_mvp;

import java.util.List;

import rx.Observable;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public interface IWeatherModel {
    public Observable<WeatherBean> loadW();
}
