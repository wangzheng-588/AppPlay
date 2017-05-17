package com.wz.appplay.common.rx.subscriber;

import android.content.Context;

import com.wz.appplay.common.exception.BaseException;
import com.wz.appplay.common.util.ProgressDialogHandler;
import com.wz.appplay.ui.BaseView;

/**
 * Created by wz on 17-5-15.
 */

public abstract class ProgressSubcriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener{

    private BaseView mView;

    public ProgressSubcriber(Context context,BaseView BaseView) {
        super(context);
        mView = BaseView;
    }




    public boolean isShowProgress(){
        return true;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mRxErrorHandler.handlerError(e);
        mView.showError(exception.getDisplayMessage());
    }

    @Override
    public void onComplete() {

        dismissProgressDialog();


    }


    private void showProgressDialog(){

    }

    private void dismissProgressDialog(){
//            if (mDialog!=null&&mDialog.isShowing()){
//                mDialog.dismiss();
//            }
        //mView.dismissLoading();
    }

    @Override
    public void onCancelProgress() {


    }
}
