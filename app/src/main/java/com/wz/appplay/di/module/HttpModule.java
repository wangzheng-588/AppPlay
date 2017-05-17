package com.wz.appplay.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.wz.appplay.common.http.CommonParamsInterceptor;
import com.wz.appplay.common.rx.RxErrorHandler;
import com.wz.appplay.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wz on 17-5-14.
 */

@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application, Gson gson){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        CommonParamsInterceptor commonParamsInterceptor = new CommonParamsInterceptor(application,gson);
        return new OkHttpClient.Builder()
                .addInterceptor(commonParamsInterceptor)
                .addInterceptor(loggingInterceptor)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public RxErrorHandler provideRxErrorHandler(Application appAplication){
        return new RxErrorHandler(appAplication);
    }
}
