package com.wz.appplay.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-5-14.
 */
@Module
public class AppModule {

    public Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }
}
