package com.sfw.app_rxjava_mvp;

import java.util.ArrayList;
import java.util.List;

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
                List<String> list = new ArrayList<String>();
                WeatherBean wB = weatherBean;
                List<WeatherBean.ResultBean.FutureBean> futureList = wB.getResult().get(0).getFuture();
                for (int i = 0; i < futureList.size(); i++) {
                    list.add("\n" + futureList.get(i).getDate() + "\n"
                            + "白天：" + futureList.get(i).getDayTime() + "\n"
                            + "夜间：" + futureList.get(i).getNight() + "\n"
                            + futureList.get(i).getTemperature() + "\n"
                            + futureList.get(i).getWeek() + "\n"
                            + futureList.get(i).getWind() + "\n");
                }
                view.showWeather(list);
                view.showLoadSuccess();
            }
        });
    }
}
