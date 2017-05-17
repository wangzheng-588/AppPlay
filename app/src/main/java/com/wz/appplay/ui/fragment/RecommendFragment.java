package com.wz.appplay.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wz.appplay.R;
import com.wz.appplay.bean.IndexBean;
import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.di.component.DaggerRecommendComponent;
import com.wz.appplay.di.module.RecommendModule;
import com.wz.appplay.presenter.RecommendPresenter;
import com.wz.appplay.presenter.contract.AppinfoContract;
import com.wz.appplay.ui.adapter.IndexAdapter;
import com.youth.banner.Banner;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by wz on 17-5-11.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppinfoContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    ProgressDialog mProgressDialog;
    private IndexAdapter mAdapte;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        inirRecycleView();
        mPresenter.requestDatas();
    }


    @Override
    public void showResult(IndexBean indexBean) {
        mAdapte = new IndexAdapter(getActivity());
        mAdapte.setData(indexBean);
        mRecyclerView.setAdapter(mAdapte);

        mAdapte.setOnBannerChangeListener(new IndexAdapter.OnBannerChangeListener() {
            @Override
            public void changeListener(Banner banner) {
                banner.start();
            }
        });
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器开小差了：" + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPerMissonSuccess() {

    }

    @Override
    public void onRequestPerMissonError() {

    }

    @Override
    public void onRequestPermissonSuccess() {
       // mPresenter.requestDatas();
    }

    @Override
    public void onRequestPermissonError() {
        Toast.makeText(getActivity(), "您已拒绝授权", Toast.LENGTH_SHORT).show();
    }


    private void inirRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapte!=null){
            mAdapte.setOnBannerChangeListener(new IndexAdapter.OnBannerChangeListener() {
                @Override
                public void changeListener(Banner banner) {
                    banner.startAutoPlay();
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mAdapte!=null){

            mAdapte.setOnBannerChangeListener(new IndexAdapter.OnBannerChangeListener() {
                @Override
                public void changeListener(Banner banner) {
                    banner.stopAutoPlay();
                }
            });
        }
    }
}
