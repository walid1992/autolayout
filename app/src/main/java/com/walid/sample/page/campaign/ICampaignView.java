package com.walid.sample.page.campaign;

import com.walid.sample.mvp.IView;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-08-28  17:14
 * Describe :
 */
interface ICampaignView extends IView {

    void loadingBabyList(List<Object> objects);

    void addMoreBabyList(List<Object> objects);

    void setRefreshing(boolean b);
}
