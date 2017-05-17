package com.wz.appplay.common.rx.subscriber;

import android.content.Context;

import com.wz.appplay.common.util.ProgressDialogHandler;

/**
 * Created by wz on 17-5-15.
 */

public abstract class ProgressDialogSubcriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener{



    public ProgressDialogSubcriber(Context context) {
        super(context);
        new ProgressDialogHandler(context,true,this);
    }

    protected  boolean isShowDialog(){
        return true;
    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

/*
    private void initProgressDialog(){
        if (mDialog==null){
            mDialog = new ProgressDialog(mContext);
            mDialog.setMessage("loading....");
        }

    }*/
    private void showProgressDialog(){
//        initProgressDialog();
//        mDialog.show();
       // mView.showLoading();
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
