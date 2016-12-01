package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import android.app.Application;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public class AppApplication extends Application{
    private static AppApplication sInstance;
    private WeatherComponent weatherComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        setupComponent();
    }

    private void setupComponent() {
        //生成AppComponent对象
        weatherComponent = DaggerWeatherComponent.builder().weatherModule(new WeatherModule()).build();
    }

    public static AppApplication getsInstance() {
        return sInstance;
    }

    public WeatherComponent getAppComponent() {
        return weatherComponent;
    }

}
