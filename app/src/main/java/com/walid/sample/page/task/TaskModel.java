package com.walid.sample.page.task;


import com.walid.sample.mvp.IModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-09-18  17:12
 * Describe :
 */

class TaskModel implements IModel {

    private List<Object> objects = new LinkedList<>();

    List<Object> getObjects() {
        return objects;
    }

    List<Object> list(int start) {
        if (start == 0) {
            objects.clear();
        }

        // 模拟超过10调数据没有更多数据
        List<Object> datas = new LinkedList<>();
        if (objects.size() >= 10) {
            return datas;
        }
        // 不满足数据可以加载更多
        datas.add(new Object());
        datas.add(new Object());
        datas.add(new Object());
        datas.add(new Object());
        datas.add(new Object());
        objects.addAll(datas);
        return datas;
    }

}
