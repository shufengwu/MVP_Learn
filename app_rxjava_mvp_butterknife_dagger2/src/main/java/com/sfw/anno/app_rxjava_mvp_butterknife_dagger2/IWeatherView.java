package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import java.util.List;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */

public interface IWeatherView {
    public void initShow();

    public void showLoadStart();

    public void showWeather(List<String> list);

    public void showLoadSuccess();

    public void showLoadFailed();
}
