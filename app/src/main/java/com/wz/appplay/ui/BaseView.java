package com.wz.appplay.ui;

/**
 * Created by wz on 17-5-13.
 */

public interface BaseView {

    void showLoading();
    void dismissLoading();
    void showError(String msg);
    void onRequestPermissonSuccess();
    void onRequestPermissonError();
}
