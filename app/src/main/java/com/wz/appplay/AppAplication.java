package com.wz.appplay;

import android.app.Application;
import android.content.Context;

import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.di.component.DaggerAppComponent;
import com.wz.appplay.di.module.AppModule;
import com.wz.appplay.di.module.HttpModule;

/**
 * Created by wz on 17-5-14.
 */

public class AppAplication extends Application {


    private AppComponent mAppComponent;

    public static AppAplication get(Context context){
        return (AppAplication)context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent =  DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
    }
}
