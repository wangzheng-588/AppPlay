package com.wz.appplay.di.module;

import com.wz.appplay.data.AppinfoModel;
import com.wz.appplay.data.http.ApiService;
import com.wz.appplay.presenter.contract.AppinfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-5-14.
 */

@Module
public class AppInfoModule {


    private AppinfoContract.AppInfoView mView;

    public AppInfoModule(AppinfoContract.AppInfoView view) {
        mView = view;
    }


    @Provides
    public AppinfoContract.AppInfoView provideView(){
        return mView;
    }


    @Provides
    public AppinfoModel provideModel(ApiService apiService){
        return new AppinfoModel(apiService);
    }


}
