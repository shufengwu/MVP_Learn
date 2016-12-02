package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import com.sfw.anno.app_rxjava_mvp_butterknife_dagger2.WeatherModel;
import com.sfw.anno.app_rxjava_mvp_butterknife_dagger2.WeatherModule;

import dagger.Component;

/**
 *
 */
@Component(modules = {WeatherModule.class})
public interface WeatherComponent {
    void inject(WeatherModel weatherModel);
}
