package com.wz.appplay.ui.fragment;

import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.di.component.DaggerAppInfoComponent;
import com.wz.appplay.di.module.AppInfoModule;
import com.wz.appplay.presenter.AppInfoPresenter;
import com.wz.appplay.ui.adapter.AppInfoAdapter;

/**
 * Created by wz on 17-5-11.
 */

public class TopListFragment extends BaseAppinfoFragment {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().injectTopListFragment(this);
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showShowBrief(false)
                .showCategoryName(true).build();
    }

    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }
}
