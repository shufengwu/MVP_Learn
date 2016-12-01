package com.sfw.app_rxjava_mvp_butterknife;

import rx.Observer;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public class WeatherPresenter {
    private IWeatherView view;
    private IWeatherModel mode;

    public WeatherPresenter(IWeatherModel mode, IWeatherView view) {
        this.mode = mode;
        this.view = view;
    }

    public void load(){
        view.showLoadStart();
        mode.loadW().subscribe(new Observer<WeatherBean>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showLoadFailed();
            }

            @Override
            public void onNext(WeatherBean weatherBean) {
                view.showWeather(mode.weatherBean2List(weatherBean));
                view.showLoadSuccess();
            }
        });
    }
}
