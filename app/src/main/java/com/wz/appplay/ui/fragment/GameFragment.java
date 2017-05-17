package com.wz.appplay.ui.fragment;

import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.di.component.DaggerAppInfoComponent;
import com.wz.appplay.di.module.AppInfoModule;
import com.wz.appplay.presenter.AppInfoPresenter;
import com.wz.appplay.ui.adapter.AppInfoAdapter;

/**
 * Created by wz on 17-5-11.
 */

public class GameFragment extends BaseAppinfoFragment {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().injectGameFragment(this);
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showShowBrief(false)
                .showCategoryName(true).build();
    }

    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }
}
