package com.wz.appplay.common.rx;

import android.support.annotation.NonNull;
import android.util.Log;

import com.wz.appplay.bean.BaseBean;
import com.wz.appplay.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wz on 17-5-15.
 */

public class RxHttpReponseCompat {


    public static <T> ObservableTransformer<BaseBean<T>,T> compatResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> baseBeanObservable) {

                return baseBeanObservable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {

                        if (tBaseBean.success()) {
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> subscriber) throws Exception {

                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onComplete();
                                    } catch (Exception e) {
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        } else {
                            Log.e("TAG", "apply: "+tBaseBean.getMessage() );
                            return Observable.error(new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }

                    }
                }) .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }


/*
    public static <T> ObservableTransformer<PageBean<T>,T> compatResult2() {

        return new ObservableTransformer<PageBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<PageBean<T>> upstream) {
                return upstream.flatMap(new Function<PageBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final PageBean<T> tPageBean) throws Exception {

                        return Observable.create(new ObservableOnSubscribe<T>() {
                            @Override
                            public void subscribe(ObservableEmitter<T> e) throws Exception {
                                e.onNext((T) tPageBean.getDatas());
                                e.onComplete();
                            }
                        });
                    }
                });

            }
        };


    }*/
}
