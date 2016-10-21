package com.walid.sample.page.task;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.walid.sample.R;
import com.walid.sample.vh.MartianAdapterViewHolder;

/**
 * Author   : walid
 * Data     : 2016-08-24  11:01
 * Describe :
 */
class TaskListAdapter extends RecyclerArrayAdapter<Object> {

    TaskListAdapter(Context context, OnLoadMoreListener loadMoreListener) {
        super(context);
        setNoMore(R.layout.judy_item_nomore);
        setMore(R.layout.judy_item_loadmore, loadMoreListener);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MartianAdapterViewHolder<Object>(parent, R.layout.item_task) {
            @Override
            public void setData(Object data) {
                super.setData(data);
                setText(R.id.tv_residue_degree, "剩余次数10次");
                setText(R.id.tv_task_name, "招商银行注册");
                setText(R.id.tv_money, "100");
                setText(R.id.tv_end_time, "截止日期 ： 2016-12-20");
            }
        };
    }

}
