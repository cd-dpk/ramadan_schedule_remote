package com.example.user.ramadan_schedule.activities.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ramadan_schedule.R;
import com.example.user.ramadan_schedule.activities.template.TemplateActivity;
import com.example.user.ramadan_schedule.adapters.CustomPagerAdapter;
import com.example.user.ramadan_schedule.datamodels.RamadanDay;
import com.example.user.ramadan_schedule.datamodels.interfaces.OnPagerViewItemListener;

public class PagerScheduleActivity extends TemplateActivity implements OnPagerViewItemListener {

    ViewPager pagerScheduleViewPager;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pager_schedule);
        pagerScheduleViewPager = (ViewPager) findViewById(R.id.view_pager_activity_pager_schedule);
    }

    @Override
    public void loadData() {
        ramadanDayList = RamadanDay.toRamadanDays(localDataBaseHelper.selectRows(new RamadanDay()));
    }
    @Override
    public void initializeViewByData() {
        pagerScheduleViewPager.setAdapter(new CustomPagerAdapter(this,ramadanDayList.size()));
    }
    @Override
    public void listenView() {

    }

    @Override
    public View objectInitiateView(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.card_pager_item,container,false);
        ((ViewPager)container).addView(view);
        return view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
