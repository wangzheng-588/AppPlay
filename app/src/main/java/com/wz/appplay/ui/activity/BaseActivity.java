package com.wz.appplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.wz.appplay.AppAplication;
import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wz on 17-5-14.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;

    private AppAplication mApplication;

    @Inject
    T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //字体图标
        LayoutInflaterCompat.setFactory(getLayoutInflater(),new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(setLayoutResID());
        mUnbinder = ButterKnife.bind(this);
        mApplication = (AppAplication) getApplication();

        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    public abstract int setLayoutResID() ;

    public abstract void setupActivityComponent(AppComponent appComponent);

    protected void startActivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }

    public abstract void init();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }
}
