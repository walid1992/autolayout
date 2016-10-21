package com.walid.sample.page.campaign;


import com.walid.sample.mvp.MartianPersenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author   : walid
 * Data     : 2016-08-28  17:03
 * Describe :
 */
class CampaignPresenter extends MartianPersenter<ICampaignView, CampaignModel> {

    CampaignPresenter(ICampaignView view) {
        super(view);
    }

    @Override
    protected CampaignModel createModel() {
        return new CampaignModel();
    }

    void loading() {
        iView.setRefreshing(false);
        Observable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    iView.loadingBabyList(model.list(0));
                });
    }

    void loadMore() {
        iView.setRefreshing(false);
        Observable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    int start = model.getObjects().size();
                    iView.addMoreBabyList(model.list(start));
                });
    }

}
