package com.wz.appplay.presenter;

import com.wz.appplay.bean.IndexBean;
import com.wz.appplay.common.rx.RxHttpReponseCompat;
import com.wz.appplay.common.rx.subscriber.ProgressSubcriber;
import com.wz.appplay.data.AppinfoModel;
import com.wz.appplay.presenter.contract.AppinfoContract;

import javax.inject.Inject;

/**
 * Created by wz on 17-5-13.
 */

public class RecommendPresenter extends BasePresenter<AppinfoModel, AppinfoContract.View> {

    @Inject
    public RecommendPresenter(AppinfoModel appinfoModel, AppinfoContract.View view) {
        super(appinfoModel, view);

    }


    public void requestDatas() {

        mModel.index().compose(RxHttpReponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubcriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean value) {
                        mView.showResult(value);
                    }
                });
    }
}
