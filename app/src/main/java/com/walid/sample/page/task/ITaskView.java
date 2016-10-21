package com.walid.sample.page.task;

import com.walid.sample.mvp.IView;

import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-08-28  17:14
 * Describe :
 */
interface ITaskView extends IView {

    void loadingTaskList(List<Object> objects);

    void addMoreTaskList(List<Object> objects);

    void setRefreshing(boolean b);

}
