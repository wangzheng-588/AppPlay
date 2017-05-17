package com.wz.appplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wz.appplay.AppAplication;
import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.presenter.BasePresenter;
import com.wz.appplay.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wz on 17-5-14.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private Unbinder mUnbinder;
    private AppAplication mAppAplication;
    private View mRootView;


    @Inject
    T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResID(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);



        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mAppAplication = (AppAplication) getActivity().getApplication();
        setupActivityComponent(mAppAplication.getAppComponent());
        init();
    }

    public abstract int setLayoutResID() ;

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}
