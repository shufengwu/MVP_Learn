package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 实现M层接口
 */

public class WeatherModel implements WeatherContract.IWeatherModel {

    private final String AppKey = "12c5afd699940";

    //WeatherService对象的注入
    @Inject
    WeatherService weatherService;

    public WeatherModel() {
        DaggerWeatherComponent.builder().weatherModule(new WeatherModule()).build().inject(this);
    }

    @Override
    public Observable<WeatherBean> loadW() {
        Observable<WeatherBean> res = weatherService.getWeather(AppKey, "朝阳", "北京")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return res;
    }

    /**
     * 将返回的JavaBean类对象转化为List <String>
     * @param weatherBean
     * @return
     */
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
