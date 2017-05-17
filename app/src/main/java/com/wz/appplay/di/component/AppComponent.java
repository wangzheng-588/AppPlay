package com.wz.appplay.di.component;

import android.app.Application;

import com.wz.appplay.common.rx.RxErrorHandler;
import com.wz.appplay.data.http.ApiService;
import com.wz.appplay.di.module.AppModule;
import com.wz.appplay.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wz on 17-5-14.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {


    ApiService getApiService();

    Application getApplication();

    RxErrorHandler getRxErrorHandler();

}
