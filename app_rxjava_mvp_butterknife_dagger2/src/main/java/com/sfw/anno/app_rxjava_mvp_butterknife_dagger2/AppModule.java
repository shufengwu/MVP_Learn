package com.sfw.anno.app_rxjava_mvp_butterknife_dagger2;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shufeng.Wu on 2016/12/1.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {

        this.application = application;
    }

    @Provides
    public Application provideApplication() {

        return application;
    }
}
