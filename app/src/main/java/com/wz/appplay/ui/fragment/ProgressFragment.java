package com.wz.appplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wz.appplay.AppAplication;
import com.wz.appplay.R;
import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.presenter.BasePresenter;
import com.wz.appplay.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wz on 17-5-15.
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;
    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private Unbinder mUnbinder;
    private TextView mTextError;
    private AppAplication mAppAplication;


    @Inject
    T mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);

        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);

        mTextError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });
        return mRootView;
    }

    public void onEmptyViewClick(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mAppAplication = (AppAplication) getActivity().getApplication();
        setupActivityComponent(mAppAplication.getAppComponent());

        setRealContentView();

        init();

    }


    public abstract void setupActivityComponent(AppComponent appComponent);


    protected  void setRealContentView(){
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);

    }


    public void showProgress(){
        showView(R.id.view_progress);
    }

    public void showContentView(){
        showView(R.id.view_content);
    }

    public void showEmptyView(){
        showView(R.id.view_empty);
    }

    public void showEmptyView(int resID){
        showEmptyView();
        mTextError.setText(resID);
    }

    public void showEmptyView(String message){
        showEmptyView();
        mTextError.setText(message);
    }

    public void showView(int viewID){
        for (int i = 0; i < mRootView.getChildCount(); i++) {
           if ( mRootView.getChildAt(i).getId()==viewID){
               mRootView.getChildAt(i).setVisibility(View.VISIBLE);
           } else {
               mRootView.getChildAt(i).setVisibility(View.GONE);
           }
        }
    }

    public abstract int setLayout();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    public abstract void init();

    @Override
    public void showLoading() {
        showProgress();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }
}
