package com.wz.appplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wz.appplay.bean.FragmentInfo;
import com.wz.appplay.ui.fragment.TopListFragment;
import com.wz.appplay.ui.fragment.CategoryFragment;
import com.wz.appplay.ui.fragment.GameFragment;
import com.wz.appplay.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wz on 17-5-11.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<>();



    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment(){
        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行", TopListFragment.class));
        mFragments.add(new FragmentInfo("游戏", GameFragment.class));
        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments==null?0:mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
