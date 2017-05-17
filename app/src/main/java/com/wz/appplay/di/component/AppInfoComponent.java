package com.wz.appplay.di.component;

import com.wz.appplay.di.FragmentScope;
import com.wz.appplay.di.module.AppInfoModule;
import com.wz.appplay.ui.fragment.GameFragment;
import com.wz.appplay.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by wz on 17-5-14.
 */

@FragmentScope
@Component(modules = AppInfoModule.class,dependencies = AppComponent.class)
public interface AppInfoComponent {

    void injectTopListFragment(TopListFragment fragment);
    void injectGameFragment(GameFragment fragment);

}
