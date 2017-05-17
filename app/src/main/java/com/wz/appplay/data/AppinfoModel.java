package com.wz.appplay.data;

import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.bean.BaseBean;
import com.wz.appplay.bean.IndexBean;
import com.wz.appplay.bean.PageBean;
import com.wz.appplay.data.http.ApiService;

import io.reactivex.Observable;


/**
 * Created by wz on 17-5-13.
 */

public class AppinfoModel {

    private ApiService mApiService;

    public AppinfoModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
        return mApiService.getApps("{'page':0}");
    }

    public Observable<BaseBean<IndexBean>> index(){
        return mApiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return mApiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> games(int page){
        return mApiService.games(page);
    }

}
