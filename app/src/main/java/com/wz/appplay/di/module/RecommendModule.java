package com.wz.appplay.di.module;

import android.app.ProgressDialog;

import com.wz.appplay.data.AppinfoModel;
import com.wz.appplay.data.http.ApiService;
import com.wz.appplay.presenter.contract.AppinfoContract;
import com.wz.appplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wz on 17-5-14.
 */

@Module
public class RecommendModule {


    private AppinfoContract.View mView;

    public RecommendModule(AppinfoContract.View view) {
        mView = view;
    }


    @Provides
    public AppinfoContract.View  provideView(){
        return mView;
    }


    @Provides
    public ProgressDialog provideProgressDialog(AppinfoContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

    @Provides
    public AppinfoModel provideModel(ApiService apiService){
        return new AppinfoModel(apiService);
    }

}
