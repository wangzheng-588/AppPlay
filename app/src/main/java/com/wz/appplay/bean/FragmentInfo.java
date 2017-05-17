package com.wz.appplay.bean;

/**
 * Created by wz on 17-5-11.
 */

public class FragmentInfo {
    private String Title;
    private Class fragment;

    public FragmentInfo() {
    }

    public FragmentInfo(String title, Class fragment) {
        Title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
