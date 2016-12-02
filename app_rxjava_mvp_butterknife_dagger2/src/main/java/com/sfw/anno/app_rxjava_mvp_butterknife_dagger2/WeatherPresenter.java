package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import rx.Observer;

/**
 * 实现P层接口
 */

public class WeatherPresenter implements WeatherContract.IWeatherPresenter {
    private WeatherContract.IWeatherView view;
    private WeatherContract.IWeatherModel mode;

    public WeatherPresenter(WeatherContract.IWeatherModel mode, WeatherContract.IWeatherView view) {
        this.mode = mode;
        this.view = view;
    }

    @Override
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
