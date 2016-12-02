package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import java.util.List;

import rx.Observable;

/**
 *
 */

public class WeatherContract {
    /*
     *
     * M层接口
     *
     */
     public interface IWeatherModel {
        public Observable<WeatherBean> loadW();
        public List<String> weatherBean2List(WeatherBean weatherBean);
     }

     /*
     *
     * P层接口
     *
     */
     public interface IWeatherPresenter {
        public void load();
     }

     /*
     *
     * V层接口
     *
     */
    public interface IWeatherView {
        public void initShow();

        public void showLoadStart();

        public void showWeather(List<String> list);

        public void showLoadSuccess();

        public void showLoadFailed();
    }
}
