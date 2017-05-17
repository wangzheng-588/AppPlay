package com.wz.appplay.presenter.contract;

import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.bean.IndexBean;
import com.wz.appplay.bean.PageBean;
import com.wz.appplay.ui.BaseView;

/**
 * Created by wz on 17-5-13.
 */

public interface AppinfoContract {

    interface View extends BaseView {

        void  showResult(IndexBean indexBean);
    }


    interface AppInfoView extends BaseView {
        void showResult(PageBean<AppInfo> pageBean);

        void onLoadMoreComplete();
    }


}
