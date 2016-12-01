package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import dagger.Component;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {
    void inject(MainActivity mainActivity);
}
