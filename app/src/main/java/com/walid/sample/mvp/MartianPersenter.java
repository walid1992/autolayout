package com.walid.sample.mvp;

/**
 * Author   : walid
 * Data     : 2016-09-05  10:56
 * Describe :
 */

public abstract class MartianPersenter<V extends IView, M extends IModel> implements IPresenter {

    protected V iView;
    protected M model;

    public MartianPersenter(V view) {
        this.iView = view;
        model = createModel();
    }

    protected abstract M createModel();

    @Override
    public void detachView() {
        this.iView = null;
    }

}
