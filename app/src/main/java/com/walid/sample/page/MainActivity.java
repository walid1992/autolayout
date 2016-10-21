package com.walid.sample.page;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.roughike.bottombar.BottomBar;
import com.walid.sample.mvp.MartianActivity;
import com.walid.sample.mvp.MartianPersenter;
import com.walid.sample.utils.FragmentPagerAdapter;
import com.walid.sample.R;
import com.walid.sample.page.campaign.CampaignFragment;
import com.walid.sample.page.mine.MineFragment;
import com.walid.sample.page.task.TaskFragment;

import butterknife.BindView;

public class MainActivity extends MartianActivity {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected MartianPersenter createPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomBar.selectTabAtPosition(position);
            }
        });
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void bindEvent() {
        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_baby:
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.tab_task:
                    viewPager.setCurrentItem(1, false);
                    break;
                case R.id.tab_mine:
                    viewPager.setCurrentItem(2, false);
                    break;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState();
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CampaignFragment(), "baby");
        adapter.addFragment(new TaskFragment(), "task");
        adapter.addFragment(new MineFragment(), "mine");
        viewPager.setAdapter(adapter);
    }

}
