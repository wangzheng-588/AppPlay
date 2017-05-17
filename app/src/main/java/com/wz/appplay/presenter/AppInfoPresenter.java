package com.wz.appplay.presenter;

import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.bean.BaseBean;
import com.wz.appplay.bean.PageBean;
import com.wz.appplay.common.rx.RxHttpReponseCompat;
import com.wz.appplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.wz.appplay.common.rx.subscriber.ProgressSubcriber;
import com.wz.appplay.data.AppinfoModel;
import com.wz.appplay.presenter.contract.AppinfoContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by wz on 17-5-16.
 */

public class AppInfoPresenter extends BasePresenter<AppinfoModel, AppinfoContract.AppInfoView> {


    public static final int TOP_LIST = 1;
    public static final int GAME = 2;

    @Inject
    public AppInfoPresenter(AppinfoModel appinfoModel, AppinfoContract.AppInfoView appInfoView) {
        super(appinfoModel, appInfoView);
    }

    public void requestData(int type, int page) {
        DefaultObserver<PageBean<AppInfo>> subcriber = null;
        if (page == 0) {
            subcriber = new ProgressSubcriber<PageBean<AppInfo>>(mContext, mView) {

                @Override
                public void onNext(PageBean<AppInfo> value) {
                    mView.showResult(value);
                }
            };
        } else {
            subcriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onNext(PageBean<AppInfo> value) {
                    mView.showResult(value);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }


        Observable observable = getObservable(type, page);

        observable
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subcriber);
    }


    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page) {

        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);

            case GAME:
                return mModel.games(page);

            default:
                return Observable.empty();
        }

    }
}
