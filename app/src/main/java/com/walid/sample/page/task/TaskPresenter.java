package com.walid.sample.page.task;

import com.walid.sample.mvp.MartianPersenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Author   : walid
 * Data     : 2016-08-28  17:03
 * Describe :
 */
class TaskPresenter extends MartianPersenter<ITaskView, TaskModel> {

    TaskPresenter(ITaskView view) {
        super(view);
    }

    @Override
    protected TaskModel createModel() {
        return new TaskModel();
    }

    void loading() {
        iView.setRefreshing(false);
        Observable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    iView.loadingTaskList(model.list(0));
                });
    }

    void loadMore() {
        iView.setRefreshing(false);
        Observable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    int start = model.getObjects().size();
                    iView.addMoreTaskList(model.list(start));
                });
    }
}
