package com.walid.sample.page.mine;

import com.walid.sample.mvp.MartianPersenter;

/**
 * Author   : walid
 * Data     : 2016-08-23  16:34
 * Describe : 个人中心业务
 */
class MinePresenter extends MartianPersenter<IMineView, MineModel> {

    MinePresenter(IMineView view) {
        super(view);
    }

    @Override
    protected MineModel createModel() {
        return new MineModel();
    }


}
