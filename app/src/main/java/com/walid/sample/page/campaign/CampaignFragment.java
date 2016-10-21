package com.walid.sample.page.campaign;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.walid.sample.R;
import com.walid.sample.mvp.MartianFragment;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * Author   : walid
 * Data     : 2016-08-25  17:10
 * Describe : 好宝贝模块fragment
 */
public class CampaignFragment extends MartianFragment<CampaignPresenter> implements ICampaignView {

    @BindView(R.id.rvTask)
    EasyRecyclerView rvTask;

    @BindColor(R.color.col_bbbbbb)
    int colbbbbbb;

    private CampaignListAdapter mTaskListAdapter;

    @Override
    protected int getRootLayoutRes() {
        return R.layout.fragment_campaign;
    }

    @Override
    protected CampaignPresenter createPresenter() {
        return new CampaignPresenter(this);
    }

    @Override
    protected void initViewsAndEvents() {
    }

    @Override
    protected void initData() {
        rvTask.setLayoutManager(new LinearLayoutManager(activity));
        // 间隔线
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, 1, 0, 0);
        itemDecoration.setDrawLastItem(false);
        rvTask.addItemDecoration(itemDecoration);
        rvTask.setRefreshListener(() -> presenter.loading());
        mTaskListAdapter = new CampaignListAdapter(activity, () -> {
            presenter.loadMore();
        });
        rvTask.setAdapterWithProgress(mTaskListAdapter);
        presenter.loading();
    }

    @Override
    public void loadingBabyList(List<Object> objects) {
        rvTask.setRefreshing(false);
        mTaskListAdapter.clear();
        mTaskListAdapter.addAll(objects);
    }

    @Override
    public void addMoreBabyList(List<Object> objects) {
        rvTask.setRefreshing(false);
        mTaskListAdapter.addAll(objects);
    }

    @Override
    public void setRefreshing(boolean b) {
        rvTask.setRefreshing(b);
    }

}
