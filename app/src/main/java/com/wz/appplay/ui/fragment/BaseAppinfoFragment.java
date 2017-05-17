package com.wz.appplay.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wz.appplay.R;
import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.bean.PageBean;
import com.wz.appplay.presenter.AppInfoPresenter;
import com.wz.appplay.presenter.contract.AppinfoContract;
import com.wz.appplay.ui.adapter.AppInfoAdapter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wz on 17-5-16.
 */

public abstract class BaseAppinfoFragment extends ProgressFragment<AppInfoPresenter>
        implements AppinfoContract.AppInfoView{
    int page = 0;

    AppInfoAdapter mAdapter;


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;


    abstract AppInfoAdapter buildAdapter();


    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }



    abstract int type();

    @Override
    public void init() {

        mPresenter.requestData(type(),page);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = buildAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.requestData(type(),page);
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissonSuccess() {

    }

    @Override
    public void onRequestPermissonError() {

    }

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());

        if (pageBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

}
