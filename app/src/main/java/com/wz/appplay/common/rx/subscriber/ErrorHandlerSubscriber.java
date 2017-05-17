package com.wz.appplay.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.wz.appplay.common.exception.BaseException;
import com.wz.appplay.common.rx.RxErrorHandler;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by wz on 17-5-15.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultObserver<T> {

    protected   RxErrorHandler mRxErrorHandler;
    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        mContext = context;
        mRxErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onError(Throwable t) {


        BaseException exception = mRxErrorHandler.handlerError(t);
        if (exception==null){
            Log.e("TAG", "ErrorHandlerSubscriber: "+t.getMessage() );
        } else {
            mRxErrorHandler.showErrorMessage(exception);
        }


    }


}
