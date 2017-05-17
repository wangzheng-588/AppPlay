package com.wz.appplay.di.component;

import com.wz.appplay.di.FragmentScope;
import com.wz.appplay.di.module.RecommendModule;
import com.wz.appplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by wz on 17-5-14.
 */

@FragmentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
