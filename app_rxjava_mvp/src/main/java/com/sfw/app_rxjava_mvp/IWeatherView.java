package com.sfw.app_rxjava_mvp;

import android.view.View;

import java.util.List;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public interface IWeatherView {
    public void initShow();

    public void showLoadStart();

    public void showWeather(List<String>list);

    public void showLoadSuccess();

    public void showLoadFailed();
}
