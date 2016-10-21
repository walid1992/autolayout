package com.walid.sample.page.campaign;

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
class CampaignListAdapter extends RecyclerArrayAdapter<Object> {

    CampaignListAdapter(Context context, RecyclerArrayAdapter.OnLoadMoreListener loadMoreListener) {
        super(context);
        setNoMore(R.layout.judy_item_nomore);
        setMore(R.layout.judy_item_loadmore, loadMoreListener);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MartianAdapterViewHolder<Object>(parent, R.layout.item_campaign) {
            @Override
            public void setData(Object data) {
                super.setData(data);
                setText(R.id.tv_name, "京东推广");
                setText(R.id.tv_ratio, "佣金 : 7%");
                setText(R.id.tv_desc, "按有效销售额度提成的CPS方式，有效销售指的是去掉退换货，以及运费，代金券费用以外的成交订单销售额。");
                setText(R.id.tv_effect_confirm, "效果认定期 : 7天");
                setText(R.id.tv_settlement_confirm, "结算周期 : 隔两个月确认");
            }
        };
    }

}
