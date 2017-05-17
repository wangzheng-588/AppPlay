package com.wz.appplay.data.http;


import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.bean.BaseBean;
import com.wz.appplay.bean.IndexBean;
import com.wz.appplay.bean.PageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wz on 17-5-13.
 */

public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";


    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParams);
    //Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParams);

    @GET("index")
    Observable<BaseBean<IndexBean>> index();

    @GET("toplist")
    Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);
}
